package com.eepl.lab_back.service;

import com.eepl.lab_back.dto.request.news.PatchNewsRequestDTO;
import com.eepl.lab_back.dto.request.research.PatchResearchRequestDTO;
import com.eepl.lab_back.dto.response.news.GetNewsDetailResponseDTO;
import com.eepl.lab_back.dto.response.news.GetNewsListResponseDTO;
import com.eepl.lab_back.dto.response.news.PatchNewsResponseDTO;
import com.eepl.lab_back.dto.response.research.GetResearchDetailResponseDTO;
import com.eepl.lab_back.dto.response.research.GetResearchListResponseDTO;
import com.eepl.lab_back.dto.response.research.PatchResearchResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ResearchService {

    ResponseEntity<? super GetResearchListResponseDTO> getResearchList(String language);
    ResponseEntity<? super GetResearchDetailResponseDTO> getResearchDetail(String language, Integer researchNumber);



    ResponseEntity<? super PatchResearchResponseDTO> patchResearch(PatchResearchRequestDTO dto, String lang, Integer researchNumber, String userId);

}
