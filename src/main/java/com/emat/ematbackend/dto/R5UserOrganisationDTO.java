package com.emat.ematbackend.dto;


import com.emat.ematbackend.models.Organisation;

import lombok.Data;

@Data
public class R5UserOrganisationDTO {


    private  Long id;

    private Organisation organisation;

    private  String uogorg;

    private  String uogdesc;


    private String username;

    private String uoggroup;

    private boolean uogcommun;

    private boolean uogdefaut;

}
