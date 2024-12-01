package com.eepl.lab_back.dto.request.news;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class PostNewsRequestDTO {

    private Integer newsNumber;

    @NotBlank
    private String newsTitle;

    @NotBlank
    private String newsContent;

    private List<String> newsImageList;
}
