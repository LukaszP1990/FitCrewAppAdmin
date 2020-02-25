package com.fitcrew.FitCrewAppAdmin.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fitcrew.FitCrewAppAdmin.resolver.ErrorMsg;
import com.fitcrew.FitCrewAppAdmin.services.AdminSignInService;
import com.fitcrew.FitCrewAppModel.domain.model.AdminDto;
import com.fitcrew.FitCrewAppModel.domain.model.LoginDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.vavr.control.Either;

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
        Either<ErrorMsg, AdminDto> adminDetailsByEmail = adminSignInService.getAdminDetailsByEmail(email);

        if (adminDetailsByEmail.isRight()) {
            String token = createJwtToken(adminDetailsByEmail.get());
            setHeaderResponse(res, adminDetailsByEmail.get(), token);
        }
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
