package com.barbearia.authentication.controller;

import com.barbearia.authentication.dto.RegisterDTO;
import com.barbearia.authentication.entity.User;
import com.barbearia.authentication.service.UserService;
import jakarta.persistence.EntityExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController
{

    private final UserService userService;

    public RegisterController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> postUser(@RequestBody RegisterDTO registerDTO) throws EntityExistsException
    {
        User user = this.userService.save(registerDTO);
        return ResponseEntity.ok(user);
    }

}
