package com.eepl.lab_back.dto.response.admin;

import com.eepl.lab_back.common.ResponseCode;
import com.eepl.lab_back.common.ResponseMessage;
import com.eepl.lab_back.dto.object.UserListItem;
import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.entity.UserEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;


@Getter
public class UserListResponseDTO extends ResponseDTO {

    private List<UserListItem> userList;

    private UserListResponseDTO(List<UserEntity> userEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.userList = UserListItem.getList(userEntities);
    }

    public static ResponseEntity<UserListResponseDTO> success(List<UserEntity> userEntities) {
        UserListResponseDTO result = new UserListResponseDTO(userEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    public static ResponseEntity<ResponseDTO> noPermission() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NO_PERMISSION, ResponseMessage.NO_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }
}
