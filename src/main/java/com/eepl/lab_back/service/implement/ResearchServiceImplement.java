package com.eepl.lab_back.service.implement;

import com.eepl.lab_back.dto.request.research.PatchResearchRequestDTO;
import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.dto.response.research.GetResearchDetailResponseDTO;
import com.eepl.lab_back.dto.response.research.GetResearchDetailResponseDTO;
import com.eepl.lab_back.dto.response.research.GetResearchListResponseDTO;
import com.eepl.lab_back.dto.response.research.PatchResearchResponseDTO;
import com.eepl.lab_back.entity.*;
import com.eepl.lab_back.repository.*;
import com.eepl.lab_back.service.ResearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResearchServiceImplement implements ResearchService {

    private final UserRepository userRepository;
    private final ResearchRepository researchRepository;
    private final ResearchContentRepository researchContentRepository;

    @Override
    public ResponseEntity<? super GetResearchListResponseDTO> getResearchList(String language) {
        
        List<ResearchEntity> researchEntities = new ArrayList<>();
        List<ResearchContentEntity> researchContentEntities = new ArrayList<>();

        try {
            researchEntities = researchRepository.findAll();
            researchContentEntities = researchContentRepository.findAll();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetResearchListResponseDTO.success(researchEntities, researchContentEntities,language);
    }

    @Override
    public ResponseEntity<? super GetResearchDetailResponseDTO> getResearchDetail(String language, Integer researchNumber) {

        ResearchEntity researchEntity = null;
        ResearchContentEntity researchContentEntity = null;

        try{
            researchEntity = researchRepository.findByResearchNumber(researchNumber);
            if (researchEntity == null) return GetResearchDetailResponseDTO.notExistResearch();

            researchContentEntity = researchContentRepository.findByResearchNumberAndLanguageCode(researchNumber, language);

            if (researchContentEntity == null) {
                return GetResearchDetailResponseDTO.notExistResearch();
            }

            researchRepository.save(researchEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetResearchDetailResponseDTO.success(researchEntity,researchContentEntity);
    }

    @Override
    public ResponseEntity<? super PatchResearchResponseDTO> patchResearch(PatchResearchRequestDTO dto, String language, Integer researchNumber, String userId) {
        try {

            ResearchEntity researchEntity = researchRepository.findByResearchNumber(researchNumber);
            if (researchEntity == null) return PatchResearchResponseDTO.noExistBoard();

            ResearchContentEntity researchContentEntity = researchContentRepository.findByResearchNumberAndLanguageCode(researchNumber, language);
            if (researchContentEntity == null) return PatchResearchResponseDTO.noExistBoard();

            boolean existedUser = userRepository.existsByUserId(userId);
            if (!existedUser) return PatchResearchResponseDTO.noExistUser();

            researchEntity.setResearchImageUrl(dto.getResearchImageUrl());
            researchContentEntity.patchResearchContent(dto);
            researchRepository.save(researchEntity);
            researchContentRepository.save(researchContentEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }

        return PatchResearchResponseDTO.success();
    }
}
