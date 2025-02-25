package com.example.newspring_securityoauth2.controller;

import com.example.newspring_securityoauth2.entity.User;
import com.example.newspring_securityoauth2.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    private final UserRepository repository;

    public UserController(UserRepository repository){
        this.repository = repository;
    }

    @GetMapping("/user")
    public Map<String ,Object> user(@AuthenticationPrincipal OAuth2User oAuth2User){
        HashMap<String,Object> details = new HashMap<>();
        details.put("name",oAuth2User.getAttribute("name"));
        details.put("login", oAuth2User.getAttribute("login"));
        details.put("email",oAuth2User.getAttribute("email"));
        return details;
    }

    @GetMapping("/admin")
    public String adminService(){
        return "Здесь могут находится только админы";
    }

    @GetMapping("/get/users")
    public List<User> getAllUsers(){
        return repository.findAll();
    }
}
