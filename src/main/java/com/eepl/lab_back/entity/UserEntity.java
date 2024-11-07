package com.eepl.lab_back.entity;

import com.eepl.lab_back.dto.request.auth.ModifyRequestDTO;
import com.eepl.lab_back.dto.request.auth.SignUpRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userNumber;

    private String userId;

    private String userPw;

    private String userName;

    private int userPass;

    private String userEmail;

    private String userPhone;

    private String userPosition;

    public UserEntity(SignUpRequestDTO dto) {
        this.userId = dto.getUserID();
        this.userPw = dto.getUserPW();
        this.userName = dto.getUserName();
        this.userPhone = dto.getUserPhone();
        this.userEmail = dto.getUserEmail();
        this.userPosition = dto.getUserPosition();
    }

}
