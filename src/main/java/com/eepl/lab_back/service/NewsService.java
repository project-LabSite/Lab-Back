package com.eepl.lab_back.service;

import com.eepl.lab_back.dto.response.news.GetNewsListResponseDTO;
import com.eepl.lab_back.dto.response.news.GetNewsResponseDTO;
import org.springframework.http.ResponseEntity;

public interface NewsService {

    ResponseEntity<? super GetNewsListResponseDTO> getNewsList();

    ResponseEntity<? super GetNewsResponseDTO> getNews(Integer boardNumber);
}
