package com.eepl.lab_back.dto.request.research;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PatchResearchRequestDTO {

    private String researchTitle;

    private String researchContent;

    private String researchImageUrl;
}
