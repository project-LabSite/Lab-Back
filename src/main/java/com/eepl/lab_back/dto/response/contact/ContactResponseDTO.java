package com.eepl.lab_back.dto.response.contact;

import com.eepl.lab_back.common.ResponseCode;
import com.eepl.lab_back.common.ResponseMessage;
import com.eepl.lab_back.dto.response.ResponseDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ContactResponseDTO extends ResponseDTO {
    private ContactResponseDTO() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<ContactResponseDTO> success() {
        ContactResponseDTO result = new ContactResponseDTO();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDTO> invalidEmail() {
        ResponseDTO result = new ResponseDTO(ResponseCode.INVALID_EMAIL, ResponseMessage.INVALID_EMAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
}
