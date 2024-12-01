package com.eepl.lab_back.service.implement;

import com.eepl.lab_back.dto.request.contact.ContactRequestDTO;
import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.dto.response.contact.ContactResponseDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ContactService {

    private final JavaMailSender mailSender;

    public ResponseEntity<? super ContactResponseDTO> sendMessage(ContactRequestDTO dto) {

        try {
            String fromEmail = dto.getEmail();
            // 이메일 유효성 검증
            if (!isValidEmail(fromEmail)) {
                return ContactResponseDTO.invalidEmail();
            }

            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo("qkrskgus0327@gmail.com");
            message.setReplyTo(fromEmail); // 보내는 사람의 이메일(프론트단에서 이메일 입력으로 받음)
            message.setText(dto.getContent()); // 이메일 내용
            message.setSubject("이메일 문의");
            mailSender.send(message);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return ContactResponseDTO.success();
    }

    // 이메일 유효성 검증 메서드
    private boolean isValidEmail(String email) {
        // Apache Commons Validator를 사용하여 이메일 형식 확인
        return EmailValidator.getInstance().isValid(email);
    }
}
