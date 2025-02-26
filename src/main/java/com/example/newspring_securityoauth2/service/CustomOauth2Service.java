package com.example.newspring_securityoauth2.service;

import com.example.newspring_securityoauth2.entity.User;
import com.example.newspring_securityoauth2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class CustomOauth2Service extends DefaultOAuth2UserService {

    private static final Logger logger = LoggerFactory.getLogger(CustomOauth2Service.class);
    private static final Long adminId = 181048447L;
    private final UserRepository repository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String,Object> attributes = oAuth2User.getAttributes();
        attributes.forEach((key,value) -> logger.info(key + " : " + value));
        String email = (String) attributes.get("email");
        Long id = Long.valueOf((Integer)attributes.get("id"));
        Set<GrantedAuthority> authorities = createAuthorities(id);
        Optional<User> opt = repository.findAllByEmail(email);
        if (opt.isPresent()){
            logger.info("User sing in " + attributes.get("name"));
        }
        else {
            User user = new User();
            user.setName((String)attributes.get("name"));
            user.setEmail((String) attributes.get("email"));
            String role = authorities.toString();
            user.setRole(role);
            logger.info("User " + user.getName() + " and role " + user.getRole() + " register!");
            repository.save(user);
        }
        return new DefaultOAuth2User(
                authorities, attributes, "login");
    }

    private Set<GrantedAuthority> createAuthorities(Long id) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if(Objects.equals(id, adminId))
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return authorities;
    }
}
