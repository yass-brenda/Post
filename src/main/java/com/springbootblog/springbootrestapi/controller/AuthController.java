package com.springbootblog.springbootrestapi.controller;

import com.springbootblog.springbootrestapi.dtos.LoginDto;
import com.springbootblog.springbootrestapi.dtos.SingUpDto;
import com.springbootblog.springbootrestapi.entity.Role;
import com.springbootblog.springbootrestapi.entity.User;
import com.springbootblog.springbootrestapi.repository.RoleRepository;
import com.springbootblog.springbootrestapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto){
         Authentication  authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SingUpDto singUpDto){
        // Checar si el usuario existe en la base de datos.
        if(userRepository.existsByUsername(singUpDto.getUsername())){
            return new ResponseEntity<>("Username is already taken", HttpStatus.BAD_REQUEST);
        }
        // checar si el email existe
        User user = new User();
        user.setName(singUpDto.getName());
        user.setUsername(singUpDto.getUsername());
        user.setEmail(singUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(singUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        userRepository.save(user);
        return  new ResponseEntity<>("Usuario registrado exitosamente",HttpStatus.OK);
    }

}
