package com.tg.oauth.server.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Administrator
 * Created on 2020/3/26
 */
@Data
public class Permission implements GrantedAuthority {

    private String name;


    @Override
    public String getAuthority() {
        return name;
    }
}
