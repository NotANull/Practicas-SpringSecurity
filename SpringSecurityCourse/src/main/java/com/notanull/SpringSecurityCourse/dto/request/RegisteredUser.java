package com.notanull.SpringSecurityCourse.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredUser implements Serializable {

    private Long id;

    private String username;

    private String name;

    private String role;

    private String jwt;
}
