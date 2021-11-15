package com.vlad.tms.diploma.model.entity;

import org.springframework.security.core.GrantedAuthority;

public enum RoleUser implements GrantedAuthority {
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
    }
