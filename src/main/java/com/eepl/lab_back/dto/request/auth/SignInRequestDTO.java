package com.eepl.lab_back.dto.request.auth;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInRequestDTO {
    private String userID;
    private String userPW;
}
