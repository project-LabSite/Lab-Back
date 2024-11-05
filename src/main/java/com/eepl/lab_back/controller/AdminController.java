package com.eepl.lab_back.controller;

import com.eepl.lab_back.dto.response.admin.ApproveUserResponseDTO;
import com.eepl.lab_back.dto.response.admin.RejectUserResponseDTO;
import com.eepl.lab_back.dto.response.admin.UserListResponseDTO;
import com.eepl.lab_back.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;
    @GetMapping("/user-list")
    public ResponseEntity<? super UserListResponseDTO> getUserList() {
        ResponseEntity<? super UserListResponseDTO> response = adminService.getUserList();
        return response;
    }

    @PostMapping("/approve/{userId}")
    public ResponseEntity<? super ApproveUserResponseDTO> approveUser(@PathVariable String userId) {
        ResponseEntity<? super ApproveUserResponseDTO> response = adminService.approveUser(userId);
        return response;
    }

    @PostMapping("/reject/{userId}")
    public ResponseEntity<? super RejectUserResponseDTO> rejectUser(@PathVariable String userId) {
        ResponseEntity<? super RejectUserResponseDTO> response = adminService.rejectUser(userId);
        return response;
    }
}
