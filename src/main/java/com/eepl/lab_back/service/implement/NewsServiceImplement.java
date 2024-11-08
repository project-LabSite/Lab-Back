package com.eepl.lab_back.service.implement;

import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.dto.response.news.GetNewsListResponseDTO;
import com.eepl.lab_back.dto.response.news.GetNewsResponseDTO;
import com.eepl.lab_back.entity.BoardEntity;
import com.eepl.lab_back.entity.ImageEntity;
import com.eepl.lab_back.repository.BoardRepository;
import com.eepl.lab_back.repository.ImageRepository;
import com.eepl.lab_back.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImplement implements NewsService {

    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;

    @Override
    public ResponseEntity<? super GetNewsListResponseDTO> getNewsList() {

        List<BoardEntity> boardEntities = new ArrayList<>();

        try {
            boardEntities = boardRepository.findByOrderByBoardWriteDatetimeDesc();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetNewsListResponseDTO.success(boardEntities);
    }

    @Override
    public ResponseEntity<? super GetNewsResponseDTO> getNews(Integer boardNumber) {

        BoardEntity boardEntity = null;
        List<ImageEntity> imageEntities = new ArrayList<>();

        try{
            boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return GetNewsResponseDTO.notExistBoard();

            imageEntities = imageRepository.findByBoardNumber(boardNumber);

            boardEntity.increaseBoardViewCount();
            boardRepository.save(boardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }

        return GetNewsResponseDTO.success(boardEntity, imageEntities);
    }
}
