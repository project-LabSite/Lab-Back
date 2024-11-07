package com.eepl.lab_back.dto.response.auth;

import com.eepl.lab_back.common.ResponseCode;
import com.eepl.lab_back.common.ResponseMessage;
import com.eepl.lab_back.dto.response.ResponseDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponseDTO extends ResponseDTO {

    private String access;
    private String refresh;

    public SignInResponseDTO(String access, String refresh) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.access = access;
        this.refresh = refresh;
    }


    public static ResponseEntity<SignInResponseDTO> success(String access,String refresh, HttpServletResponse response) {
        SignInResponseDTO result = new SignInResponseDTO(access, refresh);

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    public static ResponseEntity<ResponseDTO> signInFail() {
        ResponseDTO result = new ResponseDTO(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }
    public static ResponseEntity<ResponseDTO> accountPending() {
        ResponseDTO result = new ResponseDTO(ResponseCode.ACCOUNT_PENDING, ResponseMessage.ACCOUNT_PENDING);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }
}
