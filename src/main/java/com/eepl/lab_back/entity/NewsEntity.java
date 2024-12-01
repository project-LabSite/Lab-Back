package com.eepl.lab_back.entity;


import com.eepl.lab_back.dto.request.news.PostNewsRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;


@Entity
@Table(name = "news")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NewsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int newsNumber;

    private String newsCategory;

    private String newsWriteDatetime;

    private String newsModifyDatetime;

    private int newsViewCount;

    private String writerId;

    private String writerName;


    public NewsEntity(PostNewsRequestDTO dto, String userId) {

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newsWriteDatetime = simpleDateFormat.format(now);

        this.newsWriteDatetime = newsWriteDatetime;
        this.newsViewCount = 0;
        this.writerId = userId;
    }

    public void patchNews() {

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String newsModifyDatetime = simpleDateFormat.format(now);

        this.newsModifyDatetime = newsModifyDatetime;
    }

    public void increaseNewsViewCount() {
        this.newsViewCount++;
    }

}
