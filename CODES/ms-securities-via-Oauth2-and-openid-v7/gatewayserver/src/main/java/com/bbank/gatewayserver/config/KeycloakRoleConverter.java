package com.bbank.gatewayserver.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collection;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>>{

    @Override
    public Collection<GrantedAuthority> convert(Jwt arg0) {
        @SuppressWarnings("unchecked")
        Map<String, Object> realmAcces = (Map<String, Object>) arg0.getClaims().get("realm_access");
        if(realmAcces == null || realmAcces.isEmpty()) {
            return new ArrayList<>();
        }
        @SuppressWarnings("unchecked")
        Collection<GrantedAuthority> returnValue = ((List<String>) realmAcces.get("roles"))
            .stream().map(roleName -> "ROLE_" + roleName)
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
        
        return returnValue;
    }


}