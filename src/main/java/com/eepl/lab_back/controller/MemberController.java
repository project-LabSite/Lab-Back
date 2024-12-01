package com.eepl.lab_back.controller;

import com.eepl.lab_back.dto.response.member.MemberListResponseDTO;
import com.eepl.lab_back.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequiredArgsConstructor
@RequestMapping("/api/{language}/member")
@Tag(name = "Member", description = "Member API")
public class MemberController {

    private final MemberService memberService;
    @GetMapping("")
    @Operation(summary = "연구실 구성원", description = "연구실 구성원을 조회하는 API")
    public ResponseEntity<? super MemberListResponseDTO> getMember() {
        ResponseEntity<? super MemberListResponseDTO> response = memberService.getMemberList();
        return response;
    }

}
