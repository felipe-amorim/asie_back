package com.mkyong.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.LinkedHashMap;
import java.util.List;

public class AuthRepositoryC {
    private static LinkedHashMap<String, String> users = populateUsers();
    private static LinkedHashMap<String, String> populateUsers(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("admin", "123456");
        map.put("dev01", "123456");
        return map;
    }

    private static LinkedHashMap<String, List<GrantedAuthority>> usersAccess = populateUsersAccess();
    private static LinkedHashMap<String, List<GrantedAuthority>> populateUsersAccess(){
        LinkedHashMap<String, List<GrantedAuthority>> users = new LinkedHashMap<>();
        List<GrantedAuthority> grantedAuthorities;
        grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("planning_create, planning_read, planning_update, planning_delete");
        users.put("admin", grantedAuthorities);
        grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("planning_read, planning_update");
        users.put("dev01", grantedAuthorities);
        return users;
    }

    /*private static LinkedHashMap<String, LinkedHashMap<String, Boolean>> populateUsersAccess(){
        LinkedHashMap<String, LinkedHashMap<String, Boolean>> map = new LinkedHashMap<>();
        LinkedHashMap<String, Boolean> access;
        access = new LinkedHashMap<>();
        access.put("planning_create", true);
        access.put("planning_read", true);
        access.put("planning_update", true);
        access.put("planning_delete", true);
        map.put("admin", access);
        access = new LinkedHashMap<>();
        access.put("planning_create", false);
        access.put("planning_read", true);
        access.put("planning_update", true);
        access.put("planning_delete", false);
        map.put("dev01", access);
        return map;
    }*/
}
