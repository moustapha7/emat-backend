package com.emat.ematbackend.controller;

import com.emat.ematbackend.dto.R5UserOrganisationDTO;
import com.emat.ematbackend.exception.ResourceNotFoundException;
import com.emat.ematbackend.services.R5UserOrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api")
public class UserOrganisationController {

    @Autowired
    private R5UserOrganisationService r5UserOrganisationService;

    @GetMapping("/emat/userOrganisations/{username}")
    public List<R5UserOrganisationDTO> getAllUserOrganisation(@PathVariable String username) throws ResourceNotFoundException {
        return r5UserOrganisationService.getAllUserOrganisationByUser(username);
    }

    @PostMapping("/emat/saveUserOrganisation")
    public ResponseEntity<R5UserOrganisationDTO> saveUserOrganisation(@RequestBody R5UserOrganisationDTO r5UserOrganisationDTO) throws ResourceNotFoundException {
        return r5UserOrganisationService.saveUserOrganisation(r5UserOrganisationDTO);
    }

    @GetMapping("/emat/username")
    public String get() throws ResourceNotFoundException {

        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
            System.out.println(username);
        } else {
            username = principal.toString();
            System.out.println("else "+username);

        }
        return  username;
    }

    @DeleteMapping("/emat/deleteR5userOrg/{id}")
    public void deleteR5UserOrganisation(@PathVariable Long id) throws ResourceNotFoundException {
         r5UserOrganisationService.deleteR5UserOrg(id);
    }
}
