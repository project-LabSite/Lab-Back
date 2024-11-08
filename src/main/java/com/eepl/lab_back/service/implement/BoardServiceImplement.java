package com.eepl.lab_back.service.implement;

import com.eepl.lab_back.dto.request.board.PostBoardRequestDTO;
import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.dto.response.board.DeleteBoardResponseDTO;
import com.eepl.lab_back.dto.response.board.PostBoardResponseDTO;
import com.eepl.lab_back.entity.BoardEntity;
import com.eepl.lab_back.entity.ImageEntity;
import com.eepl.lab_back.repository.BoardRepository;
import com.eepl.lab_back.repository.ImageRepository;
import com.eepl.lab_back.repository.UserRepository;
import com.eepl.lab_back.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final BoardRepository boardRepository;

    @Override
    public ResponseEntity<? super PostBoardResponseDTO> postBoard(PostBoardRequestDTO dto, String userId) {
        try {

            boolean existedUserId = userRepository.existsByUserId(userId);
            if(!existedUserId) return PostBoardResponseDTO.notExistUser();

            BoardEntity boardEntity = new BoardEntity(dto, userId);
            boardRepository.save(boardEntity);

            int boardNumber = boardEntity.getBoardNumber();

            List<String> boardImageList = dto.getBoardImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for (String image : boardImageList) {
                ImageEntity imageEntity = new ImageEntity(boardNumber, image);
                imageEntities.add(imageEntity);
            }

            imageRepository.saveAll(imageEntities);

        } catch (Exception exception) {

            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return PostBoardResponseDTO.success();
    }

    @Override
    public ResponseEntity<? super DeleteBoardResponseDTO> deleteBoard(Integer boardNumber, String userId) {
        try {

            boolean existedUser = userRepository.existsByUserId(userId);
            if (!existedUser) {
                return DeleteBoardResponseDTO.noExistUser();
            }

            BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
            if (boardEntity == null) return DeleteBoardResponseDTO.noExistBoard();


            imageRepository.deleteByBoardNumber(boardNumber);

            boardRepository.delete(boardEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }

        return DeleteBoardResponseDTO.success();
    }


}
