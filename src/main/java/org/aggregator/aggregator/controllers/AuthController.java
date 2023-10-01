package org.aggregator.aggregator.controllers;

import lombok.RequiredArgsConstructor;
import org.aggregator.aggregator.exception.AuthError;
import org.aggregator.aggregator.jwt.JwtTokenUtils;
import org.aggregator.aggregator.model.dao.user.UserService;
import org.aggregator.aggregator.model.dto.JwtRequest;
import org.aggregator.aggregator.model.dto.JwtResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private  final UserService service;
    private final JwtTokenUtils jwt;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(new AuthError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или пароль"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = service.findUserByUsername(authRequest.getUsername());
        String token = jwt.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
