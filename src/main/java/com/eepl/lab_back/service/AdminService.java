package com.eepl.lab_back.service;

import com.eepl.lab_back.dto.request.auth.ModifyRequestDTO;
import com.eepl.lab_back.dto.response.admin.ApproveUserResponseDTO;
import com.eepl.lab_back.dto.response.admin.RejectUserResponseDTO;
import com.eepl.lab_back.dto.response.admin.UserListResponseDTO;
import com.eepl.lab_back.dto.response.auth.ModifyResponseDTO;
import org.springframework.http.ResponseEntity;

public interface AdminService {

    ResponseEntity<? super UserListResponseDTO> getUserList();

    ResponseEntity<? super ApproveUserResponseDTO> approveUser(String userId);

    ResponseEntity<? super RejectUserResponseDTO> rejectUser(String userId);
}
