package com.emat.ematbackend.repository;


import com.emat.ematbackend.models.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    Organisation findByEntite(String entite);

   // Organisation findOrganisationById(long id);

    boolean existsByEntite(String entite);


}
