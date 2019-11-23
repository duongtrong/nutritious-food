package com.spring.dev2chuc.nutritious_food.service.user;

import com.spring.dev2chuc.nutritious_food.exception.AppException;
import com.spring.dev2chuc.nutritious_food.model.Role;
import com.spring.dev2chuc.nutritious_food.model.RoleName;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.SignUpRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.OnlyUserResponse;
import com.spring.dev2chuc.nutritious_food.repository.RoleRepository;
import com.spring.dev2chuc.nutritious_food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public Optional<User> findByUsernameOrPhone(String username, String phone) {
        Optional<User> user = userRepository.findByUsernameOrPhone(username, phone);
        return user;
    }

    @Override
    public User merge(User user, SignUpRequest signUpRequest) {
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setPhone(signUpRequest.getPhone());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER);

        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }

    @Override
    public User mergeAdmin(User user, SignUpRequest signUpRequest) {
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setPhone(signUpRequest.getPhone());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN);

        user.setRoles(Collections.singleton(userRole));
        return userRepository.save(user);
    }

    @Override
    public List<OnlyUserResponse> findAllByRoles(RoleName name) {
        Role demo = roleRepository.findByName(name);

        List<User> list = userRepository.queryAllByRolesIsContaining(demo);
        return list.stream().map(x -> new OnlyUserResponse(x)).collect(Collectors.toList());
    }


    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User getUserAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            User user = userRepository.findByUsername(username);
            if (user == null) {
                return null;
            }
            return user;
        }
        return null;
    }

    @Override
    public User getById(Long id) {
        if (CollectionUtils.isEmpty (Collections.singleton (id))) {
            throw new RuntimeException ("{user.id.not.found}");
        }
        return userRepository.findById (id).orElseThrow (null);
    }

//    @Override
//    public Observable<Integer> changePassword(PasswordEncoder passwordEncoder, String email, String password, String oldPassword) {
//        return Observable.fromCallable(() -> {
//            userRepository.changePassword(email, passwordEncoder.encode(password), passwordEncoder.encode(oldPassword));
//            return Integer.MAX_VALUE;
//        });
//    }
//
//    @Override
//    public Observable<User> findUserWith(PasswordEncoder passwordEncoder, String email, String password) {
//        return Observable.fromCallable(() -> userRepository.findUserWith(email, passwordEncoder.encode(password))
//                .orElseThrow(() -> new AppException("{user.id.not.found}")));
//    }

    @Override
    public boolean checkPassword(String oldPassword, User user) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public boolean updatePassword(String password, User user) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean checkRoleByUser(User user, RoleName roleName) {
        Set<Role> roles = user.getRoles();
        Role role = roleRepository.findByName(roleName);
        if (roles.contains(role)) {
            return true;
        } else {
            return false;
        }

    }
}
