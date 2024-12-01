package com.eepl.lab_back.controller;


import com.eepl.lab_back.dto.request.news.PatchNewsRequestDTO;
import com.eepl.lab_back.dto.request.news.PostNewsRequestDTO;
import com.eepl.lab_back.dto.response.news.*;
import com.eepl.lab_back.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{language}/news")
@RequiredArgsConstructor
@Tag(name = "News", description = "News API")
public class NewsController {

    private final NewsService newsService;

    @GetMapping("")
    @Operation(summary = "소식 목록", description = "소식 목록을 조회하는 API")
    public ResponseEntity<? super GetNewsListResponseDTO> getNewsList(
            @PathVariable("language") String language
    ) {
        System.out.println("Received language: " + language);
        ResponseEntity<? super GetNewsListResponseDTO> response = newsService.getNewsList(language);
        return response;
    }

    @PostMapping("/post")
    @Operation(summary = "소식 작성", description = "소식 게시물을 작성하는 API")
    public ResponseEntity<? super PostNewsResponseDTO> postNews(
            @PathVariable("language") String language,
            @RequestBody @Valid PostNewsRequestDTO requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostNewsResponseDTO> response = newsService.postNews(requestBody, userId, language);
        return response;
    }

    @GetMapping("/{newsNumber}")
    @Operation(summary = "소식 상세 조회", description = "특정 소식 게시물의 상세페이지를 조회하는 API")
    public ResponseEntity<? super GetNewsDetailResponseDTO> getNews(
            @PathVariable("language") String language,
            @PathVariable("newsNumber") Integer newsNumber
    ) {
        ResponseEntity<? super GetNewsDetailResponseDTO> response = newsService.getNewsDetail(language,newsNumber);
        return response;
    }

    @PatchMapping("/{newsNumber}")
    @Operation(summary = "소식 수정", description = "소식 게시물을 수정하는 API")
    public ResponseEntity<? super PatchNewsResponseDTO> patchNews(
            @RequestBody @Valid PatchNewsRequestDTO requestBody,
            @PathVariable("language") String language,
            @PathVariable("newsNumber") Integer newsNumber,
            @AuthenticationPrincipal String userId
    ) {
        // "ko"를 명시적으로 전달하여 한국어 게시물을 처리
        ResponseEntity<? super PatchNewsResponseDTO> response = newsService.patchNews(requestBody,language,newsNumber, userId);
        return response;
    }

    @DeleteMapping("/{newsNumber}")
    @Operation(summary = "소식 삭제", description = "소식 게시물을 삭제하는 API")
    public ResponseEntity<? super DeleteNewsResponseDTO> deleteNews(
            @PathVariable("newsNumber") Integer newsNumber,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super DeleteNewsResponseDTO> response = newsService.deleteNews(newsNumber, userId);
        return response;
    }



}
