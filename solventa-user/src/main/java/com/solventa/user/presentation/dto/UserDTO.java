package com.solventa.user.presentation.dto;

import com.solventa.user.persistence.entity.UserEntity;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String lastName; // last_name
    private String email;
    private String password;
    private String role;
    private String status;

    public static UserDTO convertToDTO(UserEntity entity) {
        return UserDTO.builder()
                .name(entity.getName())
                .lastName(entity.getLastName())
                .email(entity.getEmail())
                .role(entity.getRole())
                .status(entity.getStatus())
                .build();
    }

    public static UserEntity converToEntity(UserDTO dto) {
        return UserEntity.builder()
                .name(dto.getName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .role(dto.getRole())
                .status(dto.getStatus())
                .build();
    }
}
