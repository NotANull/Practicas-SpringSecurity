package com.notanull.SpringSecurityCourse.config.security;

import com.notanull.SpringSecurityCourse.exceptions.ObjectNotFoundException;
import com.notanull.SpringSecurityCourse.persistence.repository.IUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityBeansInjector {

    private final IUserRepository userRepository;

    public SecurityBeansInjector(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityBeansInjector(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;
    }*/

    //Inyecto el objeto a través de un parámetro. Es lo mismo que utilizar el constructor de la clase
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setPasswordEncoder(passwordEncoder());

        authenticationProvider.setUserDetailsService(userDetailsService());

        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return (username) -> {
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new ObjectNotFoundException("User not found with username " + username));
        };
    }
}
