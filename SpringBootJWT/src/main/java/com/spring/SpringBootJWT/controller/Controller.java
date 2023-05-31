package com.spring.SpringBootJWT.controller;

import com.spring.SpringBootJWT.model.Usuario;
import com.spring.SpringBootJWT.service.UsuarioSevice;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginContext;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {

    private final UsuarioSevice usuarioSevice;


    @GetMapping
    public ResponseEntity<List<Usuario>> findAll(){
        return new ResponseEntity<>( usuarioSevice.findAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id){
        return new ResponseEntity<>( usuarioSevice.findById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario){
        return new ResponseEntity<>( usuarioSevice.save(usuario),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        usuarioSevice.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
