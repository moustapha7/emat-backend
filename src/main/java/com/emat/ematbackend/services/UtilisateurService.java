package com.emat.ematbackend.services;



import com.emat.ematbackend.dto.UserDTO;
import com.emat.ematbackend.exception.ResourceNotFoundException;

import java.util.List;

public interface UtilisateurService {

    List<UserDTO> getListUser() throws ResourceNotFoundException;

}
