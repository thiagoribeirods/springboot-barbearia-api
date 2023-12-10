package com.barbearia.authentication.controller;

import com.barbearia.authentication.dto.LoginDTO;
import com.barbearia.authentication.dto.LoginResponseDTO;
import com.barbearia.authentication.entity.User;
import com.barbearia.authentication.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController
{
    final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService)
    {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO)
    {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());

        Authentication authentication = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var user = (User) authentication.getPrincipal();

        String token = this.tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginResponseDTO(user.getUsername(), token));
    }
}
