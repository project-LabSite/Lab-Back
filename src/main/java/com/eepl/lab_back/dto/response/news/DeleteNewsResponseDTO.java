package com.eepl.lab_back.dto.response.news;

import com.eepl.lab_back.common.ResponseCode;
import com.eepl.lab_back.common.ResponseMessage;
import com.eepl.lab_back.dto.response.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DeleteNewsResponseDTO extends ResponseDTO {

    private DeleteNewsResponseDTO() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<DeleteNewsResponseDTO> success() {
        DeleteNewsResponseDTO result = new DeleteNewsResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDTO> noExistBoard() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDTO> noExistUser() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}