package com.spring.SpringBootJWT.service;

import com.spring.SpringBootJWT.data.UsuarioData;
import com.spring.SpringBootJWT.model.Usuario;
import com.spring.SpringBootJWT.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findUsuarioByUsername(username);
        if(usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuário com username ["+username+"] não encontrado! ");
        }
        return new UsuarioData(usuario);
    }
}
