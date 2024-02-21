package com.notanull.SpringSecurityCourse.service.imp;

import com.notanull.SpringSecurityCourse.dto.request.SaveUserDto;
import com.notanull.SpringSecurityCourse.exceptions.InvalidPasswordException;
import com.notanull.SpringSecurityCourse.persistence.entity.User;
import com.notanull.SpringSecurityCourse.persistence.repository.IUserRepository;
import com.notanull.SpringSecurityCourse.service.IUserService;
import com.notanull.SpringSecurityCourse.util.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class UserServiceImp implements IUserService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerOneCustomer(SaveUserDto newUser) {

        validatePassword(newUser);

        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(this.passwordEncoder.encode(newUser.getPassword()));
        user.setName(newUser.getName());
        user.setRole(Role.ROLE_CUSTOMER);

        return this.userRepository.save(user);
    }

    @Override
    public Optional<User> findOneByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    private void validatePassword(SaveUserDto dto) {

        if (!StringUtils.hasText(dto.getPassword()) || !StringUtils.hasText(dto.getRepeatPassword())) {
            throw new InvalidPasswordException("Passwords dont match");
        }

        if (!dto.getPassword().equals(dto.getRepeatPassword())) {
            throw new InvalidPasswordException("Passwords dont match");
        }
    }
}
