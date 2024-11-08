package com.eepl.lab_back.controller;


import com.eepl.lab_back.dto.request.board.PostBoardRequestDTO;
import com.eepl.lab_back.dto.response.board.DeleteBoardResponseDTO;
import com.eepl.lab_back.dto.response.board.PostBoardResponseDTO;
import com.eepl.lab_back.dto.response.news.GetNewsListResponseDTO;
import com.eepl.lab_back.dto.response.news.GetNewsResponseDTO;
import com.eepl.lab_back.service.BoardService;
import com.eepl.lab_back.service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {

    private final BoardService boardService;
    private final NewsService newsService;

    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDTO> postBoard(
            @RequestBody @Valid PostBoardRequestDTO requestBody,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super PostBoardResponseDTO> response = boardService.postBoard(requestBody, userId);
        return response;
    }

    @GetMapping("/latest-list")
    public ResponseEntity<? super GetNewsListResponseDTO> getNewsList() {
        ResponseEntity<? super GetNewsListResponseDTO> response = newsService.getNewsList();
        return response;
    }

    @GetMapping("/{boardNumber}")
    public ResponseEntity<? super GetNewsResponseDTO> getNews(
            @PathVariable("boardNumber") Integer boardNumber
    ) {
        ResponseEntity<? super GetNewsResponseDTO> response = newsService.getNews(boardNumber);
        return response;
    }

    @DeleteMapping("/{boardNumber}")
    public ResponseEntity<? super DeleteBoardResponseDTO> deleteBoard(
            @PathVariable("boardNumber") Integer boardNumber,
            @AuthenticationPrincipal String userId
    ) {
        ResponseEntity<? super DeleteBoardResponseDTO> response = boardService.deleteBoard(boardNumber, userId);
        return response;
    }



}
