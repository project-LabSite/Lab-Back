package com.eepl.lab_back.dto.response.news;//package com.eepl.lab_back.dto.response.news;

import com.eepl.lab_back.common.ResponseCode;
import com.eepl.lab_back.common.ResponseMessage;
import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.entity.NewsContentEntity;
import com.eepl.lab_back.entity.NewsEntity;
import com.eepl.lab_back.entity.ImageEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetNewsDetailResponseDTO extends ResponseDTO {

    private int newsNumber;
    private String newsTitle;
    private String newsContent;
    private List<String> newsImageList;
    private String newsWriteDatetime;
    private String newsModifyDatetime;


    private GetNewsDetailResponseDTO(NewsEntity newsEntity, NewsContentEntity newsContentEntity, List<ImageEntity> imageEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);

        List<String> newsImageList = new ArrayList<>();
        for (ImageEntity imageEntity : imageEntities) {
            String newsImage = imageEntity.getImageUrl();
            newsImageList.add(newsImage);
        }

        this.newsNumber = newsEntity.getNewsNumber();
        this.newsTitle = newsContentEntity.getNewsTitle();
        this.newsContent = newsContentEntity.getNewsContent();
        this.newsImageList = newsImageList;
        this.newsWriteDatetime = newsEntity.getNewsWriteDatetime();
        this.newsModifyDatetime = newsEntity.getNewsModifyDatetime();
    }

    public static ResponseEntity<GetNewsDetailResponseDTO> success(NewsEntity newsEntity, NewsContentEntity newsContentEntity, List<ImageEntity> imageEntities) {
        GetNewsDetailResponseDTO result = new GetNewsDetailResponseDTO(newsEntity, newsContentEntity, imageEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDTO> notExistNews() {
        ResponseDTO result = new ResponseDTO(ResponseCode.NOT_EXISTED_BOARD, ResponseMessage.NOT_EXISTED_BOARD);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }
}
