package com.eepl.lab_back.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ModifyRequestDTO {


    @NotBlank
    @Size(min=8, max=20)
    private String originPW;

    @NotBlank
    @Size(min=8, max=20)
    private String newPW;
}
