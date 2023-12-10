package com.barbearia.authentication.service;

import com.barbearia.authentication.dto.RegisterDTO;
import com.barbearia.authentication.entity.User;
import com.barbearia.authentication.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService
{
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return this.repository.findById(username).orElse(null);
    }

    public User save(RegisterDTO registerDTO)
    {
        User user = new User(registerDTO.username(), registerDTO.name(), registerDTO.password());
        return this.repository.save(user);
    }
}
