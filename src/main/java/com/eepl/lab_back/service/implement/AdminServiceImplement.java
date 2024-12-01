package com.eepl.lab_back.service.implement;

import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.dto.response.admin.SetUserStatusResponseDTO;
import com.eepl.lab_back.dto.response.admin.UserListResponseDTO;
import com.eepl.lab_back.entity.UserEntity;
import com.eepl.lab_back.repository.UserRepository;
import com.eepl.lab_back.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public ResponseEntity<? super SetUserStatusResponseDTO> approveUser(String userId) {

        try {
            UserEntity user = userRepository.findByUserId(userId);

            if (user != null) {
                user.setUserPass(1);
                userRepository.save(user);
            }
            else {
                return SetUserStatusResponseDTO.notExistedUser(); // 사용자가 존재하지 않을 때 처리
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return SetUserStatusResponseDTO.success();
    }
    @Override
    public ResponseEntity<? super SetUserStatusResponseDTO> rejectUser(String userId) {

        try {
            UserEntity user = userRepository.findByUserId(userId);

            if (user != null) {
                userRepository.delete(user);
            }
            else {
                return SetUserStatusResponseDTO.notExistedUser(); // 사용자가 존재하지 않을 때 처리
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return SetUserStatusResponseDTO.success();
    }

    @Override
    public ResponseEntity<? super SetUserStatusResponseDTO> setHead(String userId) {

        try {
            UserEntity user = userRepository.findByUserId(userId);

            if (user != null) {
                user.setUserPass(2);
                userRepository.save(user);
            }
            else {
                return SetUserStatusResponseDTO.notExistedUser(); // 사용자가 존재하지 않을 때 처리
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return SetUserStatusResponseDTO.success();
    }
}
