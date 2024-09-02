package com.example.springboottest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class Users {

    @Id
    private String id;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String email;
    @Column(name = "position_id")
    private String positionID;
    @Column(name = "supervisor_id")
    private String supervisorID;
    @Column(name = "role_id")
    private String roleID;

}
