package com.eepl.lab_back.repository;

import com.eepl.lab_back.entity.NewsContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsContentRepository extends JpaRepository<NewsContentEntity, Integer> {

    List<NewsContentEntity>  findByNewsNumber(Integer newsNumber);

    NewsContentEntity findByLanguageCode(String languageCode);
    NewsContentEntity findByNewsNumberAndLanguageCode(Integer newsNumber, String languageCode);

}
