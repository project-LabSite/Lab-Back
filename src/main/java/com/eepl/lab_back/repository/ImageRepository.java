package com.eepl.lab_back.repository;

import com.eepl.lab_back.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {


    List<ImageEntity> findByNewsNumber(Integer newsNumber);

    @Transactional
    void deleteByNewsNumber(Integer newsNumber);

}
