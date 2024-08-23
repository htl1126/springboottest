package com.example.springboottest.model;

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
@Table(name = "user_info")
public class UserInfo {

    @Id
    private String id;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private String email;
    private String positionId;
    private String supervisorId;

}
