package com.eepl.lab_back.repository;

import com.eepl.lab_back.entity.NewsContentEntity;
import com.eepl.lab_back.entity.ResearchContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResearchContentRepository extends JpaRepository<ResearchContentEntity, Integer> {
    List<ResearchContentEntity>  findByResearchNumber(Integer researchNumber);
    ResearchContentEntity findByLanguageCode(String languageCode);
    ResearchContentEntity findByResearchNumberAndLanguageCode(Integer researchNumber, String languageCode);

}
