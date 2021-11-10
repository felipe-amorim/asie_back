package com.mkyong.auth;

import com.mkyong.session.SessionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class AuthController {

    //@CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/auth")
    ResponseEntity authenticateUser(@Valid @RequestBody Auth auth) {

        //String sessionId = "User or password incorrect";
        //if (SessionRepository.validateUser(auth)){
        //    sessionId = SessionRepository.addNewUserSession(auth.getUser());
        //}
        //if(sessionId.equals("User or password incorrect")) {
        //    throw new ResponseStatusException(NOT_FOUND, sessionId);
        //}else {
            HashMap<String, String> map = new HashMap<>();
            //map.put("session", sessionId);
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(auth.getUser());
            map.put("token", getJWTToken(auth.getUser(), grantedAuthorities));
            return ResponseEntity.status(HttpStatus.OK).body(map);
        //}
    }



    private String getJWTToken(String username, List<GrantedAuthority> grantedAuthorities) {
        String secretKey = "mySecretKey";
        //List<GrantedAuthority> grantedAuthorities = AuthorityUtils
        //        .commaSeparatedStringToAuthorityList(username);

        String token = Jwts
                .builder()
                .setId("ASIE")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }


}
