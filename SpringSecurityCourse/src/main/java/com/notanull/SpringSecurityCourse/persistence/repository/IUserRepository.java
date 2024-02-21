package com.notanull.SpringSecurityCourse.persistence.repository;

import com.notanull.SpringSecurityCourse.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);
}
