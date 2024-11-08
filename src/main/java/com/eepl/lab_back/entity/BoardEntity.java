package com.eepl.lab_back.entity;


import com.eepl.lab_back.dto.request.board.PostBoardRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardNumber;

    private String boardCategory;

    private String boardTitle;

    private String boardContent;

    private String boardWriteDatetime;

    private String boardModifyDatetime;

    private int boardViewCount;

    private String boardImageUrl;

    private int writerNumber;

    private String writerName;

    private String writerId;

    public BoardEntity(PostBoardRequestDTO dto, String userId) {

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String boardWriteDatetime = simpleDateFormat.format(now);

        this.boardTitle = dto.getBoardTitle();
        this.boardCategory = dto.getBoardCategory();
        this.boardContent = dto.getBoardContent();
        this.boardWriteDatetime = boardWriteDatetime;
        this.boardModifyDatetime = boardModifyDatetime;
        this.boardViewCount = 0;
        this.writerId = userId;
    }

    public void increaseBoardViewCount() {
        this.boardViewCount++;
    }

}
