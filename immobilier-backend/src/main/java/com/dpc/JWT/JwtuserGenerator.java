package com.dpc.JWT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dpc.domain.Utilisateur;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtuserGenerator {

    public String SECRET;
	
	public String generate(Utilisateur user) {


        Claims claims = Jwts.claims()
                .setSubject(user.getUsername());
        claims.put("userId", String.valueOf(user.getId()));
        claims.put("email", user.getUsername());

     
        claims.put("lastpasswordresetdate", user.getLastPasswordResetDate());
        claims.put("password", user.getPassword());
       
        claims.put("authorities", user.getAuthorities());
     
        
        
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

}
