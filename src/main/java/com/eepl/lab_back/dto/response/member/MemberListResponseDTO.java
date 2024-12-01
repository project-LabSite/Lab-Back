package com.eepl.lab_back.dto.response.member;

import com.eepl.lab_back.common.ResponseCode;
import com.eepl.lab_back.common.ResponseMessage;
import com.eepl.lab_back.dto.object.MemberListItem;
import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.entity.UserEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class MemberListResponseDTO extends ResponseDTO {

    private List<MemberListItem> memberList;

    private MemberListResponseDTO(List<UserEntity> userEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.memberList = MemberListItem.getMemberList(userEntities);
    }

    public static ResponseEntity<MemberListResponseDTO> success(List<UserEntity> userEntities) {
        MemberListResponseDTO result = new MemberListResponseDTO(userEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
