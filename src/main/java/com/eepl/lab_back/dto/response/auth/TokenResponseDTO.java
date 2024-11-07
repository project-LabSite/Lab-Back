package com.eepl.lab_back.dto.response.auth;

import com.eepl.lab_back.common.ResponseCode;
import com.eepl.lab_back.common.ResponseMessage;
import com.eepl.lab_back.dto.response.ResponseDTO;
import lombok.Getter;

@Getter
public class TokenResponseDTO extends ResponseDTO {

    private String access;
    private String refresh;

    public TokenResponseDTO(String access, String refresh) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.access = access;
        this.refresh = refresh;

    }
}
