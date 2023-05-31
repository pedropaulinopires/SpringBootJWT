package com.spring.SpringBootJWT.service;

import com.spring.SpringBootJWT.model.Usuario;
import com.spring.SpringBootJWT.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioSevice {

    private final UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    public Usuario save(Usuario usuario){
        usuario.setPassword(passwordEncoder().encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario not found by id"));
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public void delete(Long id){
        usuarioRepository.delete(findById(id));
    }

}
