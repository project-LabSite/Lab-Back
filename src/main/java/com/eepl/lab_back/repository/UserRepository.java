package com.eepl.lab_back.repository;

import com.eepl.lab_back.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUserId(String userId);

    boolean existsByUserEmail(String userEmail);

    UserEntity findByUserId(String userID);
    UserEntity findByUserNumber(Integer userNumber);

    @Query("SELECT u FROM UserEntity u WHERE u.userPass IN (1, 2) ORDER BY u.userPosition")
    List<UserEntity> findUsersByPassAndOrderByPosition();

    List<UserEntity> findByOrderByUserNumberDesc();
    List<UserEntity> findByOrderByUserPosition();

}
