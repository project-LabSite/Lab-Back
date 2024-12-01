package com.eepl.lab_back.controller;


import com.eepl.lab_back.dto.request.news.PatchNewsRequestDTO;
import com.eepl.lab_back.dto.request.research.PatchResearchRequestDTO;
import com.eepl.lab_back.dto.response.news.GetNewsDetailResponseDTO;
import com.eepl.lab_back.dto.response.news.PatchNewsResponseDTO;
import com.eepl.lab_back.dto.response.research.GetResearchDetailResponseDTO;
import com.eepl.lab_back.dto.response.research.GetResearchListResponseDTO;
import com.eepl.lab_back.dto.response.research.PatchResearchResponseDTO;
import com.eepl.lab_back.service.ResearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{language}/research")
@RequiredArgsConstructor
@Tag(name = "Research", description = "Research API")
public class ResearchController {

    private final ResearchService researchService;

    @GetMapping("")
    @Operation(summary = "연구분야 목록", description = "연구분야 목록을 조회하는 API")
    public ResponseEntity<? super GetResearchListResponseDTO> getResearchList(
            @PathVariable("language") String language
    ) {
        ResponseEntity<? super GetResearchListResponseDTO> response = researchService.getResearchList(language);
        return response;
    }

    @GetMapping("/{researchNumber}")
    @Operation(summary = "연구분야 상세", description = "특정 연구분야의 상세페이지를 조회하는 API")
    public ResponseEntity<? super GetResearchDetailResponseDTO> getNews(
            @PathVariable("language") String language,
            @PathVariable("researchNumber") Integer researchNumber
    ) {
        ResponseEntity<? super GetResearchDetailResponseDTO> response = researchService.getResearchDetail(language,researchNumber);
        return response;
    }

    @PatchMapping("/{researchNumber}")
    @Operation(summary = "연구분야 수정", description = "특정 연구분야를 수정하는 API")
    public ResponseEntity<? super PatchResearchResponseDTO> patchResearch(
            @RequestBody @Valid PatchResearchRequestDTO requestBody,
            @PathVariable("language") String language,
            @PathVariable("researchNumber") Integer researchNumber,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PatchResearchResponseDTO> response = researchService.patchResearch(requestBody,language,researchNumber, userId);
        return response;
    }
}
