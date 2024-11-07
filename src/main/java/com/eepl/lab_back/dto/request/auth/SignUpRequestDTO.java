package com.eepl.lab_back.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class SignUpRequestDTO {

    @NotBlank
    private String userID;

    @NotBlank @Size(min=8, max=20)
    private String userPW;

    @NotBlank
    private String userName;

    @NotBlank
    private String userPosition;

    @NotBlank @Email
    private String userEmail;

    @NotBlank
    private String userPhone;

}
