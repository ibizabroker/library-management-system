//package com.accolite.lms.service;
//
//import com.accolite.lms.dao.RoleRepository;
//import com.accolite.lms.dao.UsersRepository;
//import com.accolite.lms.entity.Role;
//import com.accolite.lms.entity.Users;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class UserService {
//
//    @Autowired
//    private UsersRepository userDao;
//
//    @Autowired
//    private RoleRepository roleDao;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
////    public void initRoleAndUser() {
////
////        Role adminRole = new Role();
////        adminRole.setRoleName("Admin");
////        roleDao.save(adminRole);
////
////        Role userRole = new Role();
////        userRole.setRoleName("User");
////        roleDao.save(userRole);
////
////        Users adminUser = new Users();
////        adminUser.setUsername("admin");
////        adminUser.setPassword(getEncodedPassword("password"));
////        adminUser.setName("admin");
////        Set<Role> adminRoles = new HashSet<>();
////        adminRoles.add(adminRole);
////        adminUser.setRole(adminRoles);
////        userDao.save(adminUser);
////
//////        User user = new User();
//////        user.setUserName("raj123");
//////        user.setUserPassword(getEncodedPassword("raj@123"));
//////        user.setUserFirstName("raj");
//////        user.setUserLastName("sharma");
//////        Set<Role> userRoles = new HashSet<>();
//////        userRoles.add(userRole);
//////        user.setRole(userRoles);
//////        userDao.save(user);
////    }
//
////    public Users registerNewUser(Users user) {
////        Role role = roleDao.findById("User").get();
////        Set<Role> userRoles = new HashSet<>();
////        userRoles.add(role);
////        user.setRole(userRoles);
////        user.setPassword(getEncodedPassword(user.getPassword()));
////
////        return userDao.save(user);
////    }
////
////    public String getEncodedPassword(String password) {
////        return passwordEncoder.encode(password);
////    }
//}