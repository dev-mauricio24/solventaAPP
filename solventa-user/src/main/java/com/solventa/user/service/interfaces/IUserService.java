package com.solventa.user.service.interfaces;

import com.solventa.user.persistence.entity.UserEntity;
import com.solventa.user.presentation.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<UserDTO> getAll();
    UserDTO getById(Long id);
    UserDTO save(UserDTO UserDTO);
    void delete(Long id);
    UserDTO update(Long id, UserDTO UserDTO);
}
