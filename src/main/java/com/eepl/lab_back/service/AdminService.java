package com.eepl.lab_back.service;


import com.eepl.lab_back.dto.response.admin.SetUserStatusResponseDTO;
import com.eepl.lab_back.dto.response.admin.UserListResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    ResponseEntity<? super UserListResponseDTO> getUserList();
    ResponseEntity<? super SetUserStatusResponseDTO> approveUser(String userId);
    ResponseEntity<? super SetUserStatusResponseDTO> rejectUser(String userId);
    ResponseEntity<? super SetUserStatusResponseDTO> setHead(String userId);

}
