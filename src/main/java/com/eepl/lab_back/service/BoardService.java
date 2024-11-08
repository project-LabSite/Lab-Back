package com.eepl.lab_back.service;


import com.eepl.lab_back.dto.request.board.PostBoardRequestDTO;
import com.eepl.lab_back.dto.response.board.DeleteBoardResponseDTO;
import com.eepl.lab_back.dto.response.board.PostBoardResponseDTO;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super PostBoardResponseDTO> postBoard(PostBoardRequestDTO dto, String userId);

    ResponseEntity<? super DeleteBoardResponseDTO> deleteBoard(Integer boardNumber, String userId);
}
