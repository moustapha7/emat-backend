package com.emat.ematbackend.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class R5UserOrganisation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne
    @JoinColumn(name = "ORGANISATION_ID", nullable = true)
    private Organisation organisation;

    @NotBlank
    private  String uogorg;

    @NotBlank
    private  String uogdesc;

    @NotBlank
    private  String username;

    @NotBlank
    private String uoggroup;

    private boolean uogcommun;

    private boolean uogdefaut;

}
