package com.emat.ematbackend.servicesImpl;


import com.emat.ematbackend.dto.OrganisationDTO;
import com.emat.ematbackend.exception.ResourceNotFoundException;
import com.emat.ematbackend.mappers.OrganisationMapper;
import com.emat.ematbackend.models.Organisation;
import com.emat.ematbackend.repository.OrganisationRepository;
import com.emat.ematbackend.services.OrganisationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganisationServiceImpl implements OrganisationService {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private  ModelMapper modelMapper;

    @Autowired
    private OrganisationMapper dtoMapper;

    @Autowired
    public OrganisationServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public OrganisationDTO convertToDto(Organisation organisation) {
        return modelMapper.map(organisation, OrganisationDTO.class);
    }

    public Organisation convertToEntity(OrganisationDTO organisationDTO) {
        return modelMapper.map(organisationDTO, Organisation.class);
    }

    @Override
    public ResponseEntity<OrganisationDTO>  saveOrganisation(OrganisationDTO organisationDTO) throws ResourceNotFoundException {

        if(organisationRepository.existsByEntite(organisationDTO.getEntite()))
        {
            throw new ResourceNotFoundException("Error: L'entité existe déja");
        }
        Organisation organisation = modelMapper.map(organisationDTO, Organisation.class);
        organisationRepository.save(organisation);
        //return modelMapper.map(organisation,OrganisationDTO.class);
        return new ResponseEntity<>( modelMapper.map(organisation,OrganisationDTO.class), HttpStatus.OK);


    }

    @Override
    public ResponseEntity<OrganisationDTO> updateOrganisation(Long id, OrganisationDTO organisationDTO) throws ResourceNotFoundException {
        Optional<Organisation> organisationInfo = Optional.ofNullable(organisationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entité not found")));

        if(organisationInfo.isPresent()){
            Organisation organisation =organisationInfo.get();
            organisation.setEntite(organisationDTO.getEntite());
            organisation.setDescription(organisationDTO.getDescription());
            organisationRepository.save(organisation);
           // return modelMapper.map(organisation,OrganisationDTO.class);
            return new ResponseEntity<>( modelMapper.map(organisation,OrganisationDTO.class), HttpStatus.OK);

        }else {
            // Gérer le cas où l'entité n'est pas trouvée
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @Override
    public List<OrganisationDTO> getListOrganisation()  throws ResourceNotFoundException{

       List<Organisation> organisations = organisationRepository.findAll();
        List<OrganisationDTO> organisationDTOS =
                organisations.stream().map(organisation -> modelMapper.map(organisation,OrganisationDTO.class))
                .collect(Collectors.toList());
        return organisationDTOS;
    }

    @Override
    public ResponseEntity<OrganisationDTO>  getOrganisationByEntite(String entite) throws ResourceNotFoundException {
//
//        Organisation organisation = organisationRepository.findByEntite(entite);
//               // .orElseThrow(()-> new ResourceNotFoundException("Entité not found"));
//
//        return modelMapper.map(organisation, OrganisationDTO.class);
        return  null;
    }

    @Override
    public ResponseEntity<OrganisationDTO>  getOrganisationById(Long id) throws ResourceNotFoundException {

        Optional<Organisation> organisationInfo = organisationRepository.findById(id);
                //.orElseThrow(() -> new ResourceNotFoundException("Entité not found")));
        if (organisationInfo.isPresent()){
            Organisation organisation = organisationRepository.findById(id)
                    .orElseThrow(()-> new ResourceNotFoundException("Entité not found dd"));

            // return dtoMapper.fromOrg(organisation);
            //return modelMapper.map(organisation, OrganisationDTO.class);
            return  ResponseEntity.ok().body(modelMapper.map(organisation, OrganisationDTO.class));
        }
        else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public void deleteOrganisation(Long id) throws ResourceNotFoundException {
        Optional<Organisation> organisation = organisationRepository.findById(id);

        if(organisation.isPresent()){
            organisationRepository.deleteById(id);
        }
    }


}