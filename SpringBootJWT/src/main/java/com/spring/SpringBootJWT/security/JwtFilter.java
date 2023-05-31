package com.spring.SpringBootJWT.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;


public class JwtFilter extends BasicAuthenticationFilter {

    public JwtFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    private final String HEADER_ATTRIBUTE = "Authorization";
    private final String ATTRIBUTE_PREFIX = "Bearer ";

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String token){
        String usuario = JWT.require(Algorithm.HMAC512(JwtAuthentication.TOKEN_PASSWORD))
                .build()
                .verify(token)
                .getSubject();

        if(usuario == null){
            return null;
        }

        return new UsernamePasswordAuthenticationToken(usuario,null,new ArrayList<>());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String attribute = request.getHeader(HEADER_ATTRIBUTE);
        if(attribute == null || !attribute.startsWith(ATTRIBUTE_PREFIX)){
            chain.doFilter(request,response);
            return;
        }
        String token = attribute.replace(ATTRIBUTE_PREFIX,"");
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request,response);
    }
}
