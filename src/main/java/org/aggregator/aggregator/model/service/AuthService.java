package org.aggregator.aggregator.model.service;

import lombok.RequiredArgsConstructor;
import org.aggregator.aggregator.exception.AuthError;
import org.aggregator.aggregator.jwt.JwtTokenUtils;
import org.aggregator.aggregator.model.dao.user.UserService;
import org.aggregator.aggregator.model.dto.JwtRequest;
import org.aggregator.aggregator.model.dto.JwtResponse;
import org.aggregator.aggregator.model.dto.RegistrationUserDto;
import org.aggregator.aggregator.model.entities.Role;
import org.aggregator.aggregator.model.entities.User;
import org.aggregator.aggregator.security.PBFDK2Encoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private  final UserService service;
    private final JwtTokenUtils jwt;
    private final AuthenticationManager authenticationManager;
    private final PBFDK2Encoder encoder;

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

    public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto UserDto){
        if (!UserDto.getPassword().equals(UserDto.getConfirmPassword())){
            return new ResponseEntity<>(new AuthError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if(service.findUserByUsername(UserDto.getUsername()) != null){
            return new ResponseEntity<>(new AuthError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
        }
        User user = User.builder()
                .username(UserDto.getUsername())
                .password(encoder.encode(UserDto.getPassword()))
                .role(Role.valueOf(UserDto.getRole()))
                .build();
        service.save(user);
        return ResponseEntity.ok("Пользователь "+ UserDto.getUsername() +" создан");
    }
}
