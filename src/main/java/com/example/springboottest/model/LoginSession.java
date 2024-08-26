package com.example.springboottest.model;

import java.time.OffsetDateTime;

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
@Table(name = "login_session")
public class LoginSession {
    
    @Id
    private String ID;
    @Column(name = "user_id")
    private String userID;
    private OffsetDateTime createdAt;
    private OffsetDateTime expireAt;
}
