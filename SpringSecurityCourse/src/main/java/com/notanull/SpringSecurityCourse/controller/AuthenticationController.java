package com.notanull.SpringSecurityCourse.controller;

import com.notanull.SpringSecurityCourse.dto.request.AuthenticationRequest;
import com.notanull.SpringSecurityCourse.dto.response.AuthenticationResponse;
import com.notanull.SpringSecurityCourse.service.auth.AuthenticateService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticateService authenticationService;

    public AuthenticationController(AuthenticateService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt) {
        boolean isTokenValid = authenticationService.validateToken(jwt);
        return ResponseEntity.ok(isTokenValid);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody @Valid AuthenticationRequest authenticationRequest) {

        AuthenticationResponse rsp = authenticationService.login(authenticationRequest);
        return ResponseEntity.ok(rsp);
    }
}
