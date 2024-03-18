package com.emat.ematbackend.repository;


import com.emat.ematbackend.models.R5UserOrganisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface R5UserOrganisationRepository extends JpaRepository<R5UserOrganisation, Long> {

    List<R5UserOrganisation> findByUsername(String username);

    boolean existsByUsernameAndUogorg(String username, String uogorg);

    boolean existsByUogorg(String uogorg);


}
