package com.emat.ematbackend.mappers;


import com.emat.ematbackend.dto.OrganisationDTO;
import com.emat.ematbackend.models.Organisation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OrganisationMapper {
    public OrganisationDTO fromOrg(Organisation organisation){
        OrganisationDTO organisationDTO=new OrganisationDTO();
        BeanUtils.copyProperties(organisation,organisationDTO);
        return  organisationDTO;
    }

    public Organisation fromOrgDTO(OrganisationDTO organisationDTO){
        Organisation organisation=new Organisation();
        BeanUtils.copyProperties(organisationDTO,organisation);
        return  organisation;
    }

}
