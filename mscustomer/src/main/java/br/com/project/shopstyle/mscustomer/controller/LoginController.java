package br.com.project.shopstyle.mscustomer.controller;

import br.com.project.shopstyle.mscustomer.dto.LoginFORM;
import br.com.project.shopstyle.mscustomer.dto.TokenDTO;
import br.com.project.shopstyle.mscustomer.security.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid LoginFORM loginFORM){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginFORM.getEmail(), loginFORM.getPassword())
        );
        String token = tokenService.tokenGenerator(authentication);
        return ResponseEntity.ok(new TokenDTO(token, "Bearer "));
    }
}
