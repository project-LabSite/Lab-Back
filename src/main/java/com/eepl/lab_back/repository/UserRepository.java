package com.eepl.lab_back.repository;

import com.eepl.lab_back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUserId(String userId);
    boolean existsByUserPhone(String userPhone);

    boolean existsByUserEmail(String userEmail);
    boolean existsByUserNumber(Integer userNumber);

    UserEntity findByUserId(String userID);
    UserEntity findByUserNumber(Integer userNumber);


    List<UserEntity> findByOrderByUserNumberDesc();
}
