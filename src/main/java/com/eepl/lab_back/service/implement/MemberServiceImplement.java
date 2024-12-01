package com.eepl.lab_back.service.implement;

import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.dto.response.member.MemberListResponseDTO;
import com.eepl.lab_back.entity.UserEntity;
import com.eepl.lab_back.repository.UserRepository;
import com.eepl.lab_back.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class MemberServiceImplement implements MemberService {

    private final UserRepository userRepository;
    @Override
    public ResponseEntity<? super MemberListResponseDTO> getMemberList() {

        List<UserEntity> userEntities = new ArrayList<>();
        try {
            userEntities = userRepository.findUsersByPassAndOrderByPosition();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return MemberListResponseDTO.success(userEntities);
    }
}
