package com.tg.oauth.server.service;

import com.tg.oauth.server.entity.Permission;
import com.tg.oauth.server.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * Created on 2020/3/26
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        user.setRoles(roles);
        List<GrantedAuthority> authorities = new ArrayList<>();
        Permission permission = new Permission();
        // sys:user:view
        permission.setName("admin");
        authorities.add(permission);
        user.setAuthorities(authorities);

        return user;
    }
}
