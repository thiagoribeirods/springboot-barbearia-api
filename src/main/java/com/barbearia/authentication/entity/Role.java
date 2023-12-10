package com.barbearia.authentication.entity;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_ROLE")
@Getter
@Setter
public class Role implements GrantedAuthority
{
    @Id
    private String role;

    @Override
    public String getAuthority() {
        return this.role;
    }
}
