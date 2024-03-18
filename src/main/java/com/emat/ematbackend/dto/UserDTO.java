package com.emat.ematbackend.dto;

import com.emat.ematbackend.models.Role;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;

    private String username;

    private String email;

    private String password;

    private Set<Role> roles = new HashSet<>();

    private String groupeUser;


}
