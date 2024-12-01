package com.eepl.lab_back.controller;

import com.eepl.lab_back.dto.response.admin.SetUserStatusResponseDTO;
import com.eepl.lab_back.dto.response.admin.UserListResponseDTO;
import com.eepl.lab_back.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name = "Admin", description = "Admin API")
public class AdminController {

    private final AdminService adminService;
    @GetMapping("/user-list")
    @Operation(summary = "가입 회원 목록", description = "관리자가 회원 목록을 조회하는 API")
    public ResponseEntity<? super UserListResponseDTO> getUserList() {
        ResponseEntity<? super UserListResponseDTO> response = adminService.getUserList();
        return response;
    }
    @PostMapping("/approve/{userId}")
    public ResponseEntity<? super SetUserStatusResponseDTO> approveUser(@PathVariable String userId) {
        ResponseEntity<? super SetUserStatusResponseDTO> response = adminService.approveUser(userId);
        return response;
    }
    @PostMapping("/reject/{userId}")
    public ResponseEntity<? super SetUserStatusResponseDTO> rejectUser(@PathVariable String userId) {
        ResponseEntity<? super SetUserStatusResponseDTO> response = adminService.rejectUser(userId);
        return response;
    }
    @PostMapping("/head/{userId}")
    public ResponseEntity<? super SetUserStatusResponseDTO> setHead(@PathVariable String userId) {
        ResponseEntity<? super SetUserStatusResponseDTO> response = adminService.setHead(userId);
        return response;
    }
}
