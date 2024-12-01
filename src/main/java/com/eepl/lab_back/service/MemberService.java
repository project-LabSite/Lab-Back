package com.eepl.lab_back.service;

import com.eepl.lab_back.dto.response.member.MemberListResponseDTO;
import org.springframework.http.ResponseEntity;

public interface MemberService {

    ResponseEntity<? super MemberListResponseDTO> getMemberList();

}
