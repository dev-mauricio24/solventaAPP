package com.solventa.user.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // not null primary key auto_increment
    private long id;
    private String name;

    @Column(name="last_name")
    private String lastName; // last_name
    private String email;
    private String password;
    private String role;
    private String status;

}
