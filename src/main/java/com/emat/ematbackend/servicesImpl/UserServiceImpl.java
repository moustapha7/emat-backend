package com.emat.ematbackend.servicesImpl;

import com.emat.ematbackend.dto.OrganisationDTO;
import com.emat.ematbackend.dto.UserDTO;
import com.emat.ematbackend.exception.ResourceNotFoundException;
import com.emat.ematbackend.models.User;
import com.emat.ematbackend.repository.UserRepository;
import com.emat.ematbackend.services.UtilisateurService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl  implements UtilisateurService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDTO> getListUser() throws ResourceNotFoundException {
        List<User> users =  userRepository.findAll();
        List<UserDTO> userDTOS =
                users.stream().map(user -> modelMapper.map(user, UserDTO.class))
                        .collect(Collectors.toList());
        return userDTOS;
    }
}
