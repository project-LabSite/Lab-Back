package com.eepl.lab_back.dto.request.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostBoardRequestDTO {

    @NotBlank
    private String boardTitle;

    @NotBlank
    private String boardCategory;

    @NotBlank
    private String boardContent;

    @NotNull
    private List<String> boardImageList;

}
