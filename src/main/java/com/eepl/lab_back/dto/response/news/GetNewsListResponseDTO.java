package com.eepl.lab_back.dto.response.news;

import com.eepl.lab_back.common.ResponseCode;
import com.eepl.lab_back.common.ResponseMessage;
import com.eepl.lab_back.dto.object.NewsListItem;
import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.entity.BoardEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetNewsListResponseDTO extends ResponseDTO {

    private List<NewsListItem> newsList;

    private GetNewsListResponseDTO(List<BoardEntity> boardEntities){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.newsList = NewsListItem.getList(boardEntities);
    }

    public static ResponseEntity<GetNewsListResponseDTO> success(List<BoardEntity> boardEntities) {
        GetNewsListResponseDTO result = new GetNewsListResponseDTO(boardEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
