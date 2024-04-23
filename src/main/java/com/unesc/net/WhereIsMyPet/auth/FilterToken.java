package com.unesc.net.WhereIsMyPet.auth;

import com.unesc.net.WhereIsMyPet.entity.user.Usuario;
import com.unesc.net.WhereIsMyPet.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization != null) {
            authorization = authorization.replace("Bearer ", "");

            String subject = this.tokenService.getSubject(authorization);
            if (this.userRepository.findUserByEmail(subject).isPresent()) {
                Usuario usuario = this.userRepository.findUserByEmail(subject).get();

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario.getNome(), usuario.getSenha());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
