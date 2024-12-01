package com.eepl.lab_back.repository;

import com.eepl.lab_back.entity.ResearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ResearchRepository extends JpaRepository<ResearchEntity, Integer>  {

    ResearchEntity findByResearchNumber(Integer researchNumber);

}
