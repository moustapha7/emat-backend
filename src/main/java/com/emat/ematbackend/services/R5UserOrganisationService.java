package com.emat.ematbackend.services;


import com.emat.ematbackend.dto.OrganisationDTO;
import com.emat.ematbackend.dto.R5UserOrganisationDTO;
import com.emat.ematbackend.exception.ResourceNotFoundException;
import com.emat.ematbackend.models.R5UserOrganisation;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface R5UserOrganisationService {

    List<R5UserOrganisationDTO> getAllUserOrganisationByUser(String username) throws ResourceNotFoundException;

    ResponseEntity<R5UserOrganisationDTO> saveUserOrganisation(R5UserOrganisationDTO r5UserOrganisationDTO) throws ResourceNotFoundException;

    boolean existsByUsernameAndUogorg(String username, String uogorg);

    void  deleteR5UserOrg(Long id) throws ResourceNotFoundException;

}
