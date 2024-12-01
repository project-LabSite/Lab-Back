package com.eepl.lab_back.dto.response.news;

import com.eepl.lab_back.common.ResponseCode;
import com.eepl.lab_back.common.ResponseMessage;
import com.eepl.lab_back.dto.response.ResponseDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Getter
public class PostNewsResponseDTO extends ResponseDTO {

    private int newsNumber;

    private PostNewsResponseDTO(int newsNumber) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.newsNumber = newsNumber;
    }


    public static ResponseEntity<PostNewsResponseDTO> success(int newsNumber) {
        PostNewsResponseDTO result = new PostNewsResponseDTO(newsNumber);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDTO> notExistUser() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    public static ResponseEntity<ResponseDTO> notExistBoard() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

}
