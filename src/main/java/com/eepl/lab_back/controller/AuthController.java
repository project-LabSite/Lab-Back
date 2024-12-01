package com.eepl.lab_back.controller;


import com.eepl.lab_back.dto.request.auth.ModifyRequestDTO;
import com.eepl.lab_back.dto.request.auth.SignInRequestDTO;
import com.eepl.lab_back.dto.request.auth.SignUpRequestDTO;
import com.eepl.lab_back.dto.response.auth.ModifyResponseDTO;
import com.eepl.lab_back.dto.response.auth.MypageResponseDTO;
import com.eepl.lab_back.dto.response.auth.SignInResponseDTO;
import com.eepl.lab_back.dto.response.auth.SignUpResponseDTO;
import com.eepl.lab_back.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Auth API")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원가입 API")
    public ResponseEntity<? super SignUpResponseDTO> signUp(
            @RequestBody @Valid SignUpRequestDTO requestBody
    ) {
        ResponseEntity<? super SignUpResponseDTO> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인 API")
    public ResponseEntity<? super SignInResponseDTO> signIn(
            @RequestBody @Valid SignInRequestDTO requestBody,
            HttpServletResponse response
    ) {
        ResponseEntity<? super SignInResponseDTO> signInResponse = authService.signIn(requestBody, response);
        return signInResponse;
    }

    @PatchMapping
    @Operation(summary = "비밀번호 변경", description = "회원 비밀번호를 새로 변경하는 API")
    public ResponseEntity<? super ModifyResponseDTO> modifyMember(
            @AuthenticationPrincipal String userID,
            @RequestBody @Valid ModifyRequestDTO requestBody
    ) {
        ResponseEntity<? super ModifyResponseDTO> response = authService.modifyMember(userID, requestBody);
        return response;
    }

    @GetMapping("/mypage")
    @Operation(summary = "마이페이지", description = "마이페이지 API")
    public ResponseEntity<? super MypageResponseDTO> mypage(
            @AuthenticationPrincipal String userID
    ) {
        ResponseEntity<? super MypageResponseDTO> response = authService.mypage(userID);
        return response;
    }

}
