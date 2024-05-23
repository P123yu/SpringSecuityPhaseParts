package com.abc.Jwt;


import com.abc.model.UserModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component


public class JwtTokenGenerator {

    @Value("${SECRET}")
    private String secret;

    public String generateToken(UserDetails user){
        return Jwts.builder()
                .subject(user.getUsername())
                .claim("authorities",getAuthoritiesString(user.getAuthorities()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+86400000))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    //  [ROLE_ADMIN,ROLE_USER]  => List or Collection of GrantedAuthority
    public String getAuthoritiesString(Collection<? extends GrantedAuthority> authorities){
        Set<String> setAuth=new HashSet<>();
        for(GrantedAuthority grantAuth:authorities){
            setAuth.add(grantAuth.getAuthority());
        }    // [ROLE_ADMIN,ROLE_USER]   => it is collection of HashSet
        return String.join(",",setAuth);  // convert the HashSet to string separated by comma
    }


    public Key getKey(){
        // decode a secret string which is already in base 64 encoded format
        byte []SecretArray= Decoders.BASE64.decode(secret);
        // convert this decoded 64 byte [] Secret string format
        return Keys.hmacShaKeyFor(SecretArray);
    }



    // ---------------------------------------------------------------------------------







}
