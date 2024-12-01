package com.eepl.lab_back.dto.response.research;

import com.eepl.lab_back.common.ResponseCode;
import com.eepl.lab_back.common.ResponseMessage;
import com.eepl.lab_back.dto.object.ResearchListItem;
import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.dto.response.research.GetResearchListResponseDTO;
import com.eepl.lab_back.entity.ResearchContentEntity;
import com.eepl.lab_back.entity.ResearchEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetResearchListResponseDTO extends ResponseDTO {
    private List<ResearchListItem> researchListItems;

    private GetResearchListResponseDTO(List<ResearchEntity> researchEntities, List<ResearchContentEntity> researchContentEntities, String lang){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.researchListItems = ResearchListItem.getList(researchEntities,researchContentEntities, lang);
    }

    public static ResponseEntity<GetResearchListResponseDTO> success(List<ResearchEntity> researchEntities, List<ResearchContentEntity> researchContentEntities, String lang) {
        GetResearchListResponseDTO result = new GetResearchListResponseDTO(researchEntities, researchContentEntities, lang);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
