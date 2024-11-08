package com.eepl.lab_back.dto.response.news;

import com.eepl.lab_back.common.ResponseCode;
import com.eepl.lab_back.common.ResponseMessage;
import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.entity.BoardEntity;
import com.eepl.lab_back.entity.ImageEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetNewsResponseDTO extends ResponseDTO {

    private int boardNumber;
    private String boardTitle;
    private String board0Content;
    private List<String> boardImageList;
    private String boardWriteDatetime;
    private String boardModifyDatetime;


    private GetNewsResponseDTO(BoardEntity boardEntity, List<ImageEntity> imageEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        List<String> boardImageList = new ArrayList<>();
        for (ImageEntity imageEntity : imageEntities) {
            String boardImage = imageEntity.getImage();
            boardImageList.add(boardImage);
        }

        this.boardNumber = boardEntity.getBoardNumber();
        this.boardTitle = boardEntity.getBoardTitle();
        this.board0Content = boardEntity.getBoardContent();
        this.boardImageList = boardImageList;
        this.boardWriteDatetime = boardEntity.getBoardWriteDatetime();
        this.boardModifyDatetime = boardEntity.getBoardModifyDatetime();
    }

    public static ResponseEntity<GetNewsResponseDTO> success(BoardEntity boardEntity, List<ImageEntity> imageEntities) {
        GetNewsResponseDTO result = new GetNewsResponseDTO(boardEntity, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDTO> notExistBoard() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
