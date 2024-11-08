package com.eepl.lab_back.dto.object;


import com.eepl.lab_back.entity.BoardEntity;
import com.eepl.lab_back.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsListItem {

    private String boardTitle;
    private String boardWriteDatetime;
    private int boardViewCount;

    public NewsListItem(BoardEntity boardEntity) {

        this.boardTitle = boardEntity.getBoardTitle();
        this.boardWriteDatetime = boardEntity.getBoardWriteDatetime();
        this.boardViewCount = boardEntity.getBoardViewCount();
    }

    public static List<NewsListItem> getList(List<BoardEntity> boardEntities) {
        List<NewsListItem> list = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntities) {
            if ("news".equals(boardEntity.getBoardCategory())){
                NewsListItem newsListItem = new NewsListItem(boardEntity);
                list.add(newsListItem);
            }
        }
        return list;
    }

}
