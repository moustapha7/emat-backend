package com.emat.ematbackend.controller;


import com.emat.ematbackend.dto.OrganisationDTO;
import com.emat.ematbackend.exception.ResourceNotFoundException;
import com.emat.ematbackend.services.OrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping("/api")
public class OrganisationController {

    @Autowired
    private OrganisationService organisationService;

    @PostMapping("/emat/saveOrganisations")
    public ResponseEntity<OrganisationDTO> saveOrganisation(@RequestBody OrganisationDTO organisationDTO) throws ResourceNotFoundException {
       return organisationService.saveOrganisation(organisationDTO);
    }

    @PutMapping("/emat/updateOrganisations/{id}")
    public ResponseEntity<OrganisationDTO>  updateOrganisation(@PathVariable Long id,@RequestBody OrganisationDTO organisationDTO) throws ResourceNotFoundException {
        return organisationService.updateOrganisation(id,organisationDTO);
    }

    @GetMapping("/emat/organisations")
    public List<OrganisationDTO> getOrganisations() throws ResourceNotFoundException {
        return organisationService.getListOrganisation();
    }

    @GetMapping("/emat/organisations/{id}")
    public ResponseEntity<OrganisationDTO>  getOrganisationsById(@PathVariable(name="id") Long orgId) throws ResourceNotFoundException {
        return organisationService.getOrganisationById(orgId);
    }

//    @GetMapping("/emat/organisations/{entite}")
//    public OrganisationDTO getOrganisationsByEntite(@PathVariable String entite) throws ResourceNotFoundException {
//        OrganisationDTO organisationDTO = organisationService.getOrganisationByEntite(entite);
//        return organisationDTO;
//    }

    @DeleteMapping("/emat/organisations/{id}")
    public void deleteOrganisationsById(@PathVariable Long id) throws ResourceNotFoundException {
         organisationService.deleteOrganisation(id);
    }

}
