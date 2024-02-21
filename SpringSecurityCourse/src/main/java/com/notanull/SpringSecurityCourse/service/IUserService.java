package com.notanull.SpringSecurityCourse.service;

import com.notanull.SpringSecurityCourse.dto.request.SaveUserDto;
import com.notanull.SpringSecurityCourse.persistence.entity.User;

import java.util.Optional;

public interface IUserService {
    User registerOneCustomer(SaveUserDto newUser);

    Optional<User> findOneByUsername(String username);
}
