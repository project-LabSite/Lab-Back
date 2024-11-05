package com.eepl.lab_back.service;

import com.eepl.lab_back.dto.CustomUserDetails;
import com.eepl.lab_back.entity.UserEntity;
import com.eepl.lab_back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userID) throws UsernameNotFoundException {
        UserEntity userData = userRepository.findByUserId(userID);

        if (userData != null) {
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
