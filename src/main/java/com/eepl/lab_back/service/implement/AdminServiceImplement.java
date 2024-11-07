package com.eepl.lab_back.service.implement;

import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.dto.response.admin.ApproveUserResponseDTO;
import com.eepl.lab_back.dto.response.admin.RejectUserResponseDTO;
import com.eepl.lab_back.dto.response.admin.UserListResponseDTO;
import com.eepl.lab_back.entity.UserEntity;
import com.eepl.lab_back.repository.UserRepository;
import com.eepl.lab_back.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImplement implements AdminService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super UserListResponseDTO> getUserList() {
        List<UserEntity> userEntities = new ArrayList<>();
        try {
            userEntities = userRepository.findByOrderByUserNumberDesc();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return UserListResponseDTO.success(userEntities);
    }

    @Override
    public ResponseEntity<? super ApproveUserResponseDTO> approveUser(String userId) {

        try {
            UserEntity user = userRepository.findByUserId(userId);

            if (user != null) {
                user.setUserPass(1);
                userRepository.save(user);
            }
            else {
                return ApproveUserResponseDTO.notExistedUser(); // 사용자가 존재하지 않을 때 처리
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return ApproveUserResponseDTO.success();
    }
    @Override
    public ResponseEntity<? super RejectUserResponseDTO> rejectUser(String userId) {

        try {
            UserEntity user = userRepository.findByUserId(userId);

            if (user != null) {
                userRepository.delete(user);
            }
            else {
                return RejectUserResponseDTO.notExistedUser(); // 사용자가 존재하지 않을 때 처리
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return RejectUserResponseDTO.success();
    }
}
