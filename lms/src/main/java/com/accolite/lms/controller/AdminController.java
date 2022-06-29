package com.accolite.lms.controller;

import com.accolite.lms.dao.UsersRepository;
import com.accolite.lms.entity.JwtRequest;
import com.accolite.lms.entity.JwtResponse;
import com.accolite.lms.entity.Role;
import com.accolite.lms.entity.Users;
import com.accolite.lms.exceptions.NotFoundException;
import com.accolite.lms.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin("http://localhost:4200/")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/users")
//    @PreAuthorize("hasRole('Admin')")
    public Users addUserByAdmin(@RequestBody Users user) {
//        Role role = new Role();
////        role.setRoleName(UserConstant.DEFAULT_ROLE);
//        role.setRoleName(role.getRoleName());
//        Set<Role> setRole = new HashSet<>();
//        setRole.add(role);
//        user.setRole(setRole);
        String password = user.getPassword();
        String encryptPassword = passwordEncoder.encode(password);
        user.setPassword(encryptPassword);
        usersRepository.save(user);
        return user;
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('Admin')")
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/users/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable Integer id) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id "+ id +" does not exist."));
        return ResponseEntity.ok(user);
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/users/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable Integer id, @RequestBody Users userDetails) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id "+ id +" does not exist."));

        user.setName(userDetails.getName());
        user.setRole(userDetails.getRole());
        user.setUsername(userDetails.getUsername());

        Users updatedUser = usersRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }
}
