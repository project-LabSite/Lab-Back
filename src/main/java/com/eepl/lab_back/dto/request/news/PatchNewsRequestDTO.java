package com.eepl.lab_back.dto.request.news;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PatchNewsRequestDTO {

    private String newsTitle;

    private String newsContent;

}
