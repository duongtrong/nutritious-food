package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.exception.AppException;
import com.spring.dev2chuc.nutritious_food.model.Role;
import com.spring.dev2chuc.nutritious_food.model.RoleName;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.*;
import com.spring.dev2chuc.nutritious_food.repository.RoleRepository;
import com.spring.dev2chuc.nutritious_food.repository.UserRepository;
import com.spring.dev2chuc.nutritious_food.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Optional<User> user = userRepository.findByUsernameOrPhone (loginRequest.getAccount (), loginRequest.getAccount ());
        if (!user.isPresent ()) {
            return new ResponseEntity (
                    new ApiResponseError (HttpStatus.UNAUTHORIZED.value (),
                            "Account or password is incorrect"),
                    HttpStatus.UNAUTHORIZED);
        }

        Optional<User> currentUser = userRepository.findByPassword (loginRequest.getPassword ());
        if (!currentUser.isPresent ()) {
            return new ResponseEntity (
                    new ApiResponseError (HttpStatus.UNAUTHORIZED.value (),
                            "Account or password is incorrect"),
                    HttpStatus.UNAUTHORIZED);
        }

        Authentication authentication = authenticationManager.authenticate (
                new UsernamePasswordAuthenticationToken (
                        loginRequest.getAccount (),
                        loginRequest.getPassword ()
                )
        );

        SecurityContextHolder.getContext ().setAuthentication (authentication);

        String jwt = tokenProvider.generateToken (authentication);
        return ResponseEntity.ok (new JwtAuthenticationResponse (HttpStatus.OK.value (), "Success", jwt));
//        return new ResponseEntity(new ApiResponse(true, "User create", new JwtAuthenticationResponse(jwt)), HttpStatus.CREATED);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByUsername (signUpRequest.getUsername ())) {
            return new ResponseEntity (new ApiResponse (false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByPhone (signUpRequest.getPhone ())) {
            return new ResponseEntity (new ApiResponse (false, "Phone Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

//        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
//                    HttpStatus.BAD_REQUEST);
//        }

        // Creating user's account
        User user = new User (signUpRequest.getName (), signUpRequest.getUsername (),
                signUpRequest.getEmail (), signUpRequest.getPassword (), signUpRequest.getPhone ());

        user.setPassword (passwordEncoder.encode (user.getPassword ()));

        Role userRole = roleRepository.findByName (RoleName.ROLE_USER)
                .orElseThrow (() -> new AppException ("User Role not set."));

        user.setRoles (Collections.singleton (userRole));

        User result = userRepository.save (user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath ().path ("/users/{username}")
                .buildAndExpand (result.getUsername ()).toUri ();

        return ResponseEntity.created (location).body (new ApiResponse (true, "User registered successfully"));
    }
}
