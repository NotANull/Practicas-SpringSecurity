package com.notanull.SpringSecurityCourse.controller;

import com.notanull.SpringSecurityCourse.dto.request.RegisteredUser;
import com.notanull.SpringSecurityCourse.dto.request.SaveUserDto;
import com.notanull.SpringSecurityCourse.service.auth.AuthenticateService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final AuthenticateService authenticateService;

    public CustomerController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @PostMapping
    public ResponseEntity<RegisteredUser> registerOne(@RequestBody @Valid SaveUserDto newUser) {

        RegisteredUser registeredUser = authenticateService.registerOneCustomer(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);

    }
}
