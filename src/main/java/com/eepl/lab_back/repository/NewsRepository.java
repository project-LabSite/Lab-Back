package com.eepl.lab_back.repository;

import com.eepl.lab_back.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NewsRepository extends JpaRepository<NewsEntity, Integer>  {


    NewsEntity findByNewsNumber(Integer newsNumber);
    List<NewsEntity> findByOrderByNewsWriteDatetimeDesc();

}
