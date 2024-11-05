package com.eepl.lab_back.service;

import com.eepl.lab_back.dto.request.auth.ModifyRequestDTO;
import com.eepl.lab_back.dto.request.auth.SignInRequestDTO;
import com.eepl.lab_back.dto.request.auth.SignUpRequestDTO;
import com.eepl.lab_back.dto.response.auth.ModifyResponseDTO;
import com.eepl.lab_back.dto.response.auth.SignInResponseDTO;
import com.eepl.lab_back.dto.response.auth.SignUpResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;

public interface AuthService {

    ResponseEntity<? super SignUpResponseDTO> signUp(SignUpRequestDTO dto);

    ResponseEntity<? super SignInResponseDTO> signIn(SignInRequestDTO dto, HttpServletResponse response);

    ResponseEntity<? super ModifyResponseDTO> modifyMember(String userID, ModifyRequestDTO dto);
}
