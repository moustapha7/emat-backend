package com.emat.ematbackend.servicesImpl;


import com.emat.ematbackend.dto.R5UserOrganisationDTO;
import com.emat.ematbackend.exception.ResourceNotFoundException;
import com.emat.ematbackend.models.R5UserOrganisation;
import com.emat.ematbackend.repository.OrganisationRepository;
import com.emat.ematbackend.repository.R5UserOrganisationRepository;
import com.emat.ematbackend.repository.UserRepository;
import com.emat.ematbackend.services.OrganisationService;
import com.emat.ematbackend.services.R5UserOrganisationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class R5UserOrganisationServiceImpl implements R5UserOrganisationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private R5UserOrganisationRepository r5UserOrganisationRepository;

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private OrganisationService organisationService;

    public R5UserOrganisationDTO convertToDTO(R5UserOrganisation r5UserOrganisation){
        return  modelMapper.map(r5UserOrganisation, R5UserOrganisationDTO.class);
    }

    public R5UserOrganisation convertToEntity(R5UserOrganisationDTO r5UserOrganisationDTO){
        return modelMapper.map(r5UserOrganisationDTO,R5UserOrganisation.class);
    }

    @Override
    public List<R5UserOrganisationDTO> getAllUserOrganisationByUser(String username) throws ResourceNotFoundException {

        List<R5UserOrganisation> r5UserOrganisations = r5UserOrganisationRepository.findByUsername(username);

        List<R5UserOrganisationDTO>  r5UserOrganisationDTOS =
                r5UserOrganisations.stream().map(r5UserOrganisation -> modelMapper.map(r5UserOrganisation,R5UserOrganisationDTO.class))
                        .collect(Collectors.toList());
        return r5UserOrganisationDTOS;
    }

    @Override
    public ResponseEntity<R5UserOrganisationDTO> saveUserOrganisation(R5UserOrganisationDTO r5UserOrganisationDTO) throws ResourceNotFoundException {
        UserDetails userDetails =
                (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!organisationRepository.existsByEntite(r5UserOrganisationDTO.getUogorg())){
            throw new ResourceNotFoundException("L'entité n'existe pas");
        }
        if(this.existsByUsernameAndUogorg(r5UserOrganisationDTO.getUsername(),r5UserOrganisationDTO.getUogorg())){
            throw new ResourceNotFoundException("L'entité existe déja pour cet utilisateur");
        }
        if(userDetails.getUsername()== null){
            throw new ResourceNotFoundException("L'utilisateur n'est pas connecté");
        }
        String descEntite = organisationService.findDescriptionByEntite(r5UserOrganisationDTO.getUogorg());
        R5UserOrganisation r5UserOrganisation = modelMapper.map(r5UserOrganisationDTO,R5UserOrganisation.class);
        r5UserOrganisation.setUogorg(r5UserOrganisationDTO.getUogorg());
        r5UserOrganisation.setUogdesc(descEntite);
        r5UserOrganisationRepository.save(r5UserOrganisation);
        return new ResponseEntity<>( modelMapper.map(r5UserOrganisation,R5UserOrganisationDTO.class), HttpStatus.OK);

    }

    @Override
    public boolean existsByUsernameAndUogorg(String username, String uogorg) {
        return r5UserOrganisationRepository.existsByUsernameAndUogorg(username, uogorg);
    }

    @Override
    public void deleteR5UserOrg(Long id) throws ResourceNotFoundException {
        Optional<R5UserOrganisation> r5UserOrganisation = r5UserOrganisationRepository.findById(id);
        if(r5UserOrganisation.isPresent()){
            r5UserOrganisationRepository.deleteById(id);
        }
    }

}
