package com.emat.ematbackend.services;

import com.emat.ematbackend.dto.OrganisationDTO;
import com.emat.ematbackend.exception.ResourceNotFoundException;
import com.emat.ematbackend.models.Organisation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface OrganisationService {

    ResponseEntity<OrganisationDTO> saveOrganisation(OrganisationDTO organisationDTO) throws ResourceNotFoundException;

    ResponseEntity<OrganisationDTO> updateOrganisation(Long id,OrganisationDTO organisationDTO) throws ResourceNotFoundException;


    List<OrganisationDTO> getListOrganisation() throws ResourceNotFoundException;

    ResponseEntity<OrganisationDTO> getOrganisationByEntite(String entite) throws ResourceNotFoundException;

    ResponseEntity<OrganisationDTO> getOrganisationById(Long id) throws ResourceNotFoundException;

    void deleteOrganisation(Long id) throws ResourceNotFoundException;

    String findDescriptionByEntite(String entite) throws ResourceNotFoundException;

    boolean existsByUogorg(String uog);

}
