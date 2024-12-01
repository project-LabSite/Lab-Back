package com.eepl.lab_back.entity;

import com.eepl.lab_back.dto.request.news.PatchNewsRequestDTO;
import com.eepl.lab_back.dto.request.news.PostNewsRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "news_content")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsContentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ncNumber;
    private int newsNumber;
    private String languageCode;
    private String newsTitle;
    private String newsContent;

    public NewsContentEntity(PostNewsRequestDTO dto, Integer newsNumber, String languageCode) {
        this.newsNumber = newsNumber;
        this.languageCode = languageCode;
        this.newsTitle = dto.getNewsTitle();
        this.newsContent = dto.getNewsContent();
    }

    public void patchNewsContent(PatchNewsRequestDTO dto) {
        this.newsTitle = dto.getNewsTitle();
        this.newsContent = dto.getNewsContent();
    }

}
