package com.eepl.lab_back.repository;

import com.eepl.lab_back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUserId(String userId);
    boolean existsByUserPhone(String userPhone);

    boolean existsByUserEmail(String userEmail);

    UserEntity findByUserId(String userID);

    List<UserEntity> findByOrderByUserJoinDateDesc();
}
