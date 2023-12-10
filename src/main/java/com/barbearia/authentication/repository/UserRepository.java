package com.barbearia.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barbearia.authentication.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
    
}
