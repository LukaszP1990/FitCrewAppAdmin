package com.fitcrew.FitCrewAppAdmin.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitcrew.FitCrewAppAdmin.dto.AdminDto;
import com.fitcrew.FitCrewAppAdmin.dto.LoginDto;
import com.fitcrew.FitCrewAppAdmin.services.AdminSignInService;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AdminSignInService adminSignInService;
    private final Environment environment;

    AuthenticationFilter(Environment environment, AuthenticationManager authenticationManager, AdminSignInService adminSignInService) {
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
                                            Authentication auth) throws IOException, ServletException {

        String email = ((User) auth.getPrincipal()).getUsername();
        AdminDto adminDetailsByEmail = adminSignInService.getAdminDetailsByEmail(email);

        String token = createJwtToken(adminDetailsByEmail);

        setHeaderResponse(res, adminDetailsByEmail, token);
    }

    private void setHeaderResponse(HttpServletResponse res, AdminDto adminDetailsByEmail, String token) {
        res.addHeader("token", token);
        res.addHeader("userId", adminDetailsByEmail.getAdminId());
    }

    private String createJwtToken(AdminDto adminDetailsByEmail) {
        return Jwts.builder()
                .setSubject(adminDetailsByEmail.getAdminId())
                .setExpiration(new Date(
                        System.currentTimeMillis() + Long.parseLong(
                                Objects.requireNonNull(
                                        environment.getProperty("token.expiration_time")
                                )
                        )
                ))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
                .compact();
    }
}