package br.com.project.shopstyle.mscustomer.security.service;

import br.com.project.shopstyle.mscustomer.entity.Customer;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class TokenService {

    @Value("${security.jwt.expiration}")
    String expiration;
    @Value("${security.jwt.secret}")
    String secret;

    public String tokenGenerator(Authentication authentication){
        Customer customer = (Customer) authentication.getPrincipal();
        LocalDateTime dateNowPlusMinutes = LocalDateTime.now().plusMinutes(Long.parseLong(expiration));
        Date expiration = Date.from(dateNowPlusMinutes.atZone(ZoneId.systemDefault()).toInstant());

        return Jwts.builder().setIssuer("Customer MS").setSubject(customer.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Long getIdFromToken(String tokenFromHeader){
        Claims requisitionBody = Jwts.parser().setSigningKey(secret).parseClaimsJws(tokenFromHeader).getBody();
        return Long.valueOf(requisitionBody.getSubject());
    }

    public boolean tokenIsValid(String token){
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
