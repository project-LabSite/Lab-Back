package com.eepl.lab_back.service;

import com.eepl.lab_back.dto.request.news.PatchNewsRequestDTO;
import com.eepl.lab_back.dto.request.news.PostNewsRequestDTO;
import com.eepl.lab_back.dto.response.news.*;
import org.springframework.http.ResponseEntity;

public interface NewsService {


    ResponseEntity<? super PostNewsResponseDTO> postNews(PostNewsRequestDTO dto, String userId, String lang);

    ResponseEntity<? super PatchNewsResponseDTO> patchNews(PatchNewsRequestDTO dto, String lang, Integer newsNumber, String userId);
    ResponseEntity<? super DeleteNewsResponseDTO> deleteNews(Integer newsNumber, String userId);

    ResponseEntity<? super GetNewsListResponseDTO> getNewsList(String lang);

    ResponseEntity<? super GetNewsDetailResponseDTO> getNewsDetail(String language, Integer newsNumber);

}
