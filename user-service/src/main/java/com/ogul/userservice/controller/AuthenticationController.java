package com.ogul.userservice.controller;


import com.ogul.userservice.configuration.JwtService;
import com.ogul.userservice.dto.AuthenticationRequest;
import com.ogul.userservice.model.User;
import com.ogul.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    private final JwtService jwtService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
        @RequestBody AuthenticationRequest authenticationRequest
    ) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );
        final User user = userService.loadUserByUsername(authenticationRequest.getUsername());
        if (user != null) {
            return ResponseEntity.ok(jwtService.generateToken(user));
        }
        return ResponseEntity.status(401).body("Unauthorized");
    }
}
