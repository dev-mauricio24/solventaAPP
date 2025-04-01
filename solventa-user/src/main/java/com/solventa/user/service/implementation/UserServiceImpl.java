package com.solventa.user.service.implementation;

import com.solventa.user.persistence.entity.UserEntity;
import com.solventa.user.persistence.repository.UserRepository;
import com.solventa.user.presentation.dto.UserDTO;
import com.solventa.user.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;

    @Override
    public List<UserDTO> getAll() {
        try {
            List<UserEntity> entityList = (List<UserEntity>) repository.findAll();

            return entityList.stream()
                    .map(UserDTO::convertToDTO).collect(Collectors.toList());

        } catch (QueryTimeoutException e) {
            log.error("Error: La consulta tardó más de 30 segundos en responder", e);
            return Collections.emptyList();
        }catch (Exception e) {
            String error = "Error inesperado al obtener la lista de usuarios";
            log.error(error, e);
            throw new RuntimeException(error, e);
        }
    }

    @Override
    public UserDTO getById(Long id) {
        try {
            UserEntity entity = repository.findById(id).orElseThrow();
            return UserDTO.convertToDTO(entity);

        } catch (QueryTimeoutException e) {
            log.error("Error: La consulta ha superado el tiempo límite", e);
            throw new RuntimeException("La consulta ha excedido el tiempo límite de respuesta.", e);
        }
        catch (Exception e) {
            log.error("Error inesperado al obtener el usuario con ID: " + id, e);
            throw new RuntimeException("Error al obtener el usuario con ID: " + id, e);
        }
    }

    @Transactional
    @Override
    public UserDTO save(UserDTO dto) {

        try {
            UserEntity savedUser = repository.save(UserDTO.converToEntity(dto));
            return UserDTO.convertToDTO(savedUser);

        } catch (QueryTimeoutException e) {
            log.error("Error: La consulta ha superado el tiempo límite", e);
            throw new RuntimeException("La consulta ha excedido el tiempo límite de respuesta.", e);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        try {
            if (!repository.existsById(id)) {
                log.error("Error: user not found");
                throw new Exception();
            }

            repository.deleteById(id);

        } catch (QueryTimeoutException e) {
            log.error("Error: The query has exceeded the time limit.", e);
            throw new RuntimeException("The query has exceeded the time limit.", e);

        } catch (Exception e) {
            log.error("Error: An expected error has occurred!");
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public UserDTO update(Long id, UserDTO dto) {

       try {
           UserEntity userEntity = repository.findById(id).orElseThrow();

           userEntity.setName(dto.getName());
           userEntity.setLastName(dto.getLastName());
           userEntity.setEmail(dto.getEmail());
           userEntity.setRole(dto.getRole());
           userEntity.setStatus(dto.getStatus());

           UserEntity updatedUser = repository.save(userEntity);

           return UserDTO.convertToDTO(updatedUser);
       } catch (QueryTimeoutException e) {
           log.error("Error: The query has exceeded the time limit.", e);
           throw new RuntimeException("The query has exceeded the time limit.", e);

       } catch (Exception e) {
           log.error("Error: An expected error has occurred!");
           throw new RuntimeException(e);
       }
    }
}
