package com.eepl.lab_back.repository;

import com.eepl.lab_back.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {


    BoardEntity findByBoardNumber(Integer boardNumber);

    List<BoardEntity> findByOrderByBoardWriteDatetimeDesc();



}
