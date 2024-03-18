package com.emat.ematbackend.controller;


import com.emat.ematbackend.dto.UserDTO;
import com.emat.ematbackend.exception.ResourceNotFoundException;
import com.emat.ematbackend.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/emat/utilisateurs")
    public List<UserDTO> getUsers() throws ResourceNotFoundException {
        return  utilisateurService.getListUser();
    }
}
