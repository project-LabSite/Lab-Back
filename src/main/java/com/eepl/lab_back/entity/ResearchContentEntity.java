package com.eepl.lab_back.entity;

import com.eepl.lab_back.dto.request.news.PatchNewsRequestDTO;
import com.eepl.lab_back.dto.request.research.PatchResearchRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "research_content")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResearchContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rcNumber;
    private int researchNumber;
    private String languageCode;
    private String researchTitle;
    private String researchContent;

    public void patchResearchContent(PatchResearchRequestDTO dto) {
        this.researchTitle = dto.getResearchTitle();
        this.researchContent = dto.getResearchContent();
    }
}
