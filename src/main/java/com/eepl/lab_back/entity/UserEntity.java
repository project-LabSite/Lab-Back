package com.eepl.lab_back.entity;

import com.eepl.lab_back.dto.request.auth.SignUpRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


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

    @Column(name = "user_name_e")
    private String userNameE;

    private String userImageUrl;


    private int userPass;

    private String userEmail;


    private String userPosition;

    public UserEntity(SignUpRequestDTO dto) {
        this.userId = dto.getUserID();
        this.userPw = dto.getUserPW();
        this.userName = dto.getUserName();
        this.userNameE = dto.getUserNameE();
        this.userImageUrl = dto.getUserImageUrl();
        this.userEmail = dto.getUserEmail();
        this.userPosition = dto.getUserPosition();
    }

}
