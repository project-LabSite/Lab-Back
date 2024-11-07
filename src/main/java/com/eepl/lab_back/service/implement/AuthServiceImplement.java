package com.eepl.lab_back.service.implement;

import com.eepl.lab_back.dto.request.auth.ModifyRequestDTO;
import com.eepl.lab_back.dto.request.auth.SignInRequestDTO;
import com.eepl.lab_back.dto.request.auth.SignUpRequestDTO;
import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.dto.response.auth.ModifyResponseDTO;
import com.eepl.lab_back.dto.response.auth.SignInResponseDTO;
import com.eepl.lab_back.dto.response.auth.SignUpResponseDTO;
import com.eepl.lab_back.entity.RefreshEntity;
import com.eepl.lab_back.entity.UserEntity;
import com.eepl.lab_back.filter.JWTUtil;
import com.eepl.lab_back.repository.RefreshRepository;
import com.eepl.lab_back.repository.UserRepository;
import com.eepl.lab_back.service.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.patterns.IToken;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Date;


@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final RefreshRepository refreshRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public ResponseEntity<? super SignUpResponseDTO> signUp(SignUpRequestDTO dto) {

        try {

            String userId = dto.getUserID();
            boolean existedUserId = userRepository.existsByUserId(userId);
            if (existedUserId) return SignUpResponseDTO.duplicateId();

            String userEmail = dto.getUserEmail();
            boolean existedUserEmail = userRepository.existsByUserEmail(userEmail);
            if (existedUserEmail) return SignUpResponseDTO.duplicateEmail();

            String userPhone = dto.getUserPhone();
            boolean existedUserPhone = userRepository.existsByUserPhone(userPhone);
            if (existedUserPhone) return SignUpResponseDTO.duplicatePhone();

            String userPW = dto.getUserPW();

            String encodedUserPW = passwordEncoder.encode(userPW);
            dto.setUserPW(encodedUserPW);

            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return SignUpResponseDTO.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDTO> signIn(SignInRequestDTO dto, HttpServletResponse response) {

        String access = null;
        String refresh = null;

        try {

            String userID = dto.getUserID();
            UserEntity userEntity = userRepository.findByUserId(userID);
            if (userEntity == null) return SignInResponseDTO.signInFail();

            int userPassStatus = userEntity.getUserPass();

            if (userPassStatus == 0) {
                return SignInResponseDTO.accountPending();
            }

            String userPW = dto.getUserPW();
            String encodedUserPW = userEntity.getUserPw();
            boolean isMatched = passwordEncoder.matches(userPW, encodedUserPW);
            if (!isMatched) return SignInResponseDTO.signInFail();

            Integer userPass = userEntity.getUserPass();
            String userRole;

            if (userPass == 2) {
                userRole = "ROLE_ADMIN";
            } else { userRole = "ROLE_USER";}


            access = jwtUtil.createJwt("access", userID, userRole, 600000L);
            refresh = jwtUtil.createJwt("refresh", userID, userRole,86400000L);

            addRefreshEntity(userID, refresh, 86400000L);
            response.addCookie(createCookie("refresh", refresh));

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return SignInResponseDTO.success(access, refresh, response);
    }


    public ResponseEntity<? super ModifyResponseDTO> modifyMember(
            String userID,
            ModifyRequestDTO dto) {

        try {

            UserEntity userEntity = userRepository.findByUserId(userID);
            System.out.println(userID);

            //입력받은 originPW
            String originPW = dto.getOriginPW();

            //DB에 저장되어 있는 originPW
            String encodedOriginPW = userEntity.getUserPw();

            boolean isMatched = passwordEncoder.matches(originPW, encodedOriginPW);
            if (!isMatched) return ModifyResponseDTO.fail();

            String newPW = dto.getNewPW();
            String encodedNewPW = passwordEncoder.encode(newPW);

            // 암호화된 새 비밀번호를 엔티티에 저장
            userEntity.setUserPw(encodedNewPW);

            userRepository.save(userEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return ModifyResponseDTO.success();
    }

    private void addRefreshEntity(String userID, String refresh, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setUserId(userID);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());
        refreshRepository.save(refreshEntity);
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }

}
