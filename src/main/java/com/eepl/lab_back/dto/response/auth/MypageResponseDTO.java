package com.eepl.lab_back.dto.response.auth;

import com.eepl.lab_back.common.ResponseCode;
import com.eepl.lab_back.common.ResponseMessage;
import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.dto.response.news.GetNewsDetailResponseDTO;
import com.eepl.lab_back.entity.ImageEntity;
import com.eepl.lab_back.entity.NewsContentEntity;
import com.eepl.lab_back.entity.NewsEntity;
import com.eepl.lab_back.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class MypageResponseDTO extends ResponseDTO {

    private String userName;
    private String userNameE;
    private String userPosition;
    private String userImageUrl;
    private String userEmail;

    public MypageResponseDTO(UserEntity userEntity) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userName=userEntity.getUserName();
        this.userNameE=userEntity.getUserNameE();
        this.userPosition=userEntity.getUserPosition();
        this.userImageUrl=userEntity.getUserImageUrl();
        this.userEmail=userEntity.getUserEmail();
    }

    public static ResponseEntity<MypageResponseDTO> success(UserEntity userEntity) {
        MypageResponseDTO result = new MypageResponseDTO(userEntity);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDTO> notExistUser() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
