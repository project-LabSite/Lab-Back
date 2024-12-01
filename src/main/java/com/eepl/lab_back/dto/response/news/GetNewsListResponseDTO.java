package com.eepl.lab_back.dto.response.news;

import com.eepl.lab_back.common.ResponseCode;
import com.eepl.lab_back.common.ResponseMessage;
import com.eepl.lab_back.dto.object.NewsListItem;
import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.entity.NewsContentEntity;
import com.eepl.lab_back.entity.NewsEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class GetNewsListResponseDTO extends ResponseDTO {

    private List<NewsListItem> newsListItems;

    private GetNewsListResponseDTO(List<NewsEntity> newsEntities, List<NewsContentEntity> newsContentEntities, String lang){
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.newsListItems = NewsListItem.getList(newsEntities,newsContentEntities, lang);
    }

    public static ResponseEntity<GetNewsListResponseDTO> success(List<NewsEntity> newsEntities, List<NewsContentEntity> newsContentEntities, String lang) {
        GetNewsListResponseDTO result = new GetNewsListResponseDTO(newsEntities, newsContentEntities, lang);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
