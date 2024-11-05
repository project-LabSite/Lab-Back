package com.eepl.lab_back.controller;


import com.eepl.lab_back.dto.request.auth.ModifyRequestDTO;
import com.eepl.lab_back.dto.request.auth.SignInRequestDTO;
import com.eepl.lab_back.dto.request.auth.SignUpRequestDTO;
import com.eepl.lab_back.dto.response.auth.ModifyResponseDTO;
import com.eepl.lab_back.dto.response.auth.SignInResponseDTO;
import com.eepl.lab_back.dto.response.auth.SignUpResponseDTO;
import com.eepl.lab_back.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<? super SignUpResponseDTO> signUp(
            @RequestBody @Valid SignUpRequestDTO requestBody
    ) {
        ResponseEntity<? super SignUpResponseDTO> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<? super SignInResponseDTO> signIn(
            @RequestBody @Valid SignInRequestDTO requestBody,
            HttpServletResponse response
    ) {
        ResponseEntity<? super SignInResponseDTO> signInResponse = authService.signIn(requestBody, response);
        return signInResponse;
    }

    @PatchMapping
    public ResponseEntity<? super ModifyResponseDTO> modifyMember(
            @AuthenticationPrincipal String userID,
            @RequestBody @Valid ModifyRequestDTO requestBody
    ) {
        ResponseEntity<? super ModifyResponseDTO> response = authService.modifyMember(userID, requestBody);
        return response;
    }


}
