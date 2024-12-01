package com.eepl.lab_back.entity;


import com.eepl.lab_back.dto.request.news.PostNewsRequestDTO;
import com.eepl.lab_back.dto.response.research.PatchResearchResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "research")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResearchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int researchNumber;
    private String researchImageUrl;


}
