package com.fitcrew.FitCrewAppAdmin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitcrew.FitCrewAppAdmin.dto.LoginDto;
import com.fitcrew.FitCrewAppAdmin.services.AdminSignInService;
import com.fitcrew.FitCrewAppModel.domain.model.AdminModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AdminSignInService adminSignInService;
    private final Environment environment;

    AuthenticationFilter(Environment environment,
                         AuthenticationManager authenticationManager,
                         AdminSignInService adminSignInService) {
        this.environment = environment;
        this.adminSignInService = adminSignInService;
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            LoginDto cred = new ObjectMapper()
                    .readValue(request.getInputStream(), LoginDto.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            cred.getEmail(),
                            cred.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) {
        var email = ((User) auth.getPrincipal()).getUsername();
        var adminDetailsByEmail = adminSignInService.getAdminDetailsByEmail(email);

        adminDetailsByEmail
                .map(this::createJwtToken)
                .forEach(token -> setHeaderResponse(res, adminDetailsByEmail.get(), token));
    }

    private void setHeaderResponse(HttpServletResponse res, AdminModel adminDetailsByEmail, String token) {
        res.addHeader("token", token);
        res.addHeader("userId", adminDetailsByEmail.getAdminId());
    }

    private String createJwtToken(AdminModel adminDetailsByEmail) {
        return Jwts.builder()
                .setSubject(adminDetailsByEmail.getAdminId())
                .setExpiration(new Date(getExpiration()))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
                .compact();
    }

    private long getExpiration() {
        return System.currentTimeMillis() + Long.parseLong(
                Objects.requireNonNull(environment.getProperty("token.expiration_time"))
        );
    }
}
