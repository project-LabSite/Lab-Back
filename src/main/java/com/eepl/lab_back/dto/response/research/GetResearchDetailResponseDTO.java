package com.eepl.lab_back.dto.response.research;

import com.eepl.lab_back.common.ResponseCode;
import com.eepl.lab_back.common.ResponseMessage;
import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.entity.ResearchContentEntity;
import com.eepl.lab_back.entity.ResearchEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



@Getter
public class GetResearchDetailResponseDTO extends ResponseDTO{

    private int researchNumber;
    private String researchTitle;
    private String researchContent;
    private String researchImageUrl;

    private GetResearchDetailResponseDTO(ResearchEntity researchEntity, ResearchContentEntity researchContentEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        this.researchNumber = researchEntity.getResearchNumber();
        this.researchTitle = researchContentEntity.getResearchTitle();
        this.researchContent = researchContentEntity.getResearchContent();
        this.researchImageUrl = researchEntity.getResearchImageUrl();
    }

    public static ResponseEntity<GetResearchDetailResponseDTO> success(ResearchEntity researchEntity, ResearchContentEntity researchContentEntity) {
        GetResearchDetailResponseDTO result = new GetResearchDetailResponseDTO(researchEntity, researchContentEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDTO> notExistResearch() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
