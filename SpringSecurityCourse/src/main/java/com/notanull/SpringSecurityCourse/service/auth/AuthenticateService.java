package com.notanull.SpringSecurityCourse.service.auth;

import com.notanull.SpringSecurityCourse.dto.request.AuthenticationRequest;
import com.notanull.SpringSecurityCourse.dto.request.RegisteredUser;
import com.notanull.SpringSecurityCourse.dto.request.SaveUserDto;
import com.notanull.SpringSecurityCourse.dto.response.AuthenticationResponse;
import com.notanull.SpringSecurityCourse.persistence.entity.User;
import com.notanull.SpringSecurityCourse.service.IUserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticateService {

    private final IUserService userService;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticateService(IUserService userService, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public RegisteredUser registerOneCustomer(SaveUserDto newUser) {

        User user = userService.registerOneCustomer(newUser);

        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setId(user.getId());
        registeredUser.setUsername(user.getUsername());
        registeredUser.setName(user.getName());
        registeredUser.setRole(user.getRole().name()); //.name() por el nombre de la enumeración

        String jwt = jwtService.generateToken(user, generateExtraClaims(user)); //Utilizando la implementación de UserDetails para que me devuelva el token con JWT
        registeredUser.setJwt(jwt);

        return registeredUser;
    }

    private Map<String, Object> generateExtraClaims(User user) {

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        this.authenticationManager.authenticate(authentication);

        UserDetails user = this.userService.findOneByUsername(authenticationRequest.getUsername()).get();

        String jwt = jwtService.generateToken(user, generateExtraClaims((User) user));

        AuthenticationResponse authRsp = new AuthenticationResponse();
        authRsp.setJwt(jwt);
        return authRsp;
    }

    public boolean validateToken(String jwt) {

        try{
            this.jwtService.extractUsername(jwt);
                    return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        //HEADER
        //PAYLOAD
        //FIRMA
        //QUE NO HAYA EXPIRADO
    }
}
