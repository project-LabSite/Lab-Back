package com.eepl.lab_back.controller;

import com.eepl.lab_back.dto.request.contact.ContactRequestDTO;
import com.eepl.lab_back.dto.response.contact.ContactResponseDTO;
import com.eepl.lab_back.service.implement.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/{language}/contact")
@Tag(name = "Contact", description = "Contact API")
public class ContactController {

    private final ContactService contactService;

    @PostMapping("")
    @Operation(summary = "Contact Us", description = "문의 내용을 작성하여 메일을 전송하는 API")
    public ResponseEntity<? super ContactResponseDTO> sendContactMail(
            @RequestBody @Valid ContactRequestDTO requestBody
    ) {
        ResponseEntity<? super ContactResponseDTO> response = contactService.sendMessage(requestBody);
        return response;
    }

}
