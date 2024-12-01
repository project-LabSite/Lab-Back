package com.eepl.lab_back.service.implement;


import com.eepl.lab_back.dto.request.news.PatchNewsRequestDTO;
import com.eepl.lab_back.dto.request.news.PostNewsRequestDTO;
import com.eepl.lab_back.dto.response.ResponseDTO;
import com.eepl.lab_back.dto.response.news.*;
import com.eepl.lab_back.entity.ImageEntity;
import com.eepl.lab_back.entity.NewsContentEntity;
import com.eepl.lab_back.entity.NewsEntity;
import com.eepl.lab_back.repository.*;
import com.eepl.lab_back.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImplement implements NewsService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final NewsRepository newsRepository;
    private final NewsContentRepository newsContentRepository;

    @Override
    public ResponseEntity<? super PostNewsResponseDTO> postNews(PostNewsRequestDTO dto, String userId, String language) {

        Integer newsNumber = null;

        try {
            // 사용자 존재 여부 체크
            boolean existedUserId = userRepository.existsByUserId(userId);
            if (!existedUserId) return PostNewsResponseDTO.notExistUser();

            // 한국어(lang="ko") 요청 시 새로운 뉴스 저장
            if ("ko".equals(language)) {
                if (dto.getNewsNumber() == null) {
                    // 공통 내용 저장
                    NewsEntity newsEntity = new NewsEntity(dto, userId);
                    newsRepository.save(newsEntity);

                    // 방금 저장한 newsNumber 불러오기
                    newsNumber = newsEntity.getNewsNumber();

                    // 이미지 저장
                    List<String> newsImageList = dto.getNewsImageList();

                    if (newsImageList != null && !newsImageList.isEmpty()) {
                        List<ImageEntity> imageEntities = new ArrayList<>();
                        for (String image : newsImageList) {
                            imageEntities.add(new ImageEntity(newsNumber, image));
                        }
                        imageRepository.saveAll(imageEntities);
                    }

                } else {
                    // 기존 뉴스 번호 사용
                    newsNumber = dto.getNewsNumber();
                }
            }

            // 영어(lang="en") 요청 시 기존 newsNumber 확인
            if ("en".equals(language)) {
                newsNumber = dto.getNewsNumber();
                // newsNumber가 없으면 오류 반환
                if (newsNumber == null) {
                    return PostNewsResponseDTO.notExistBoard();
                }
                // 해당 뉴스가 존재하는지 확인
                boolean existsNews = newsRepository.existsById(newsNumber);
                if (!existsNews) {
                    return PostNewsResponseDTO.notExistBoard();
                }
            }

            // 뉴스 본문 저장
            NewsContentEntity newsContentEntity = new NewsContentEntity(dto, newsNumber, language);
            newsContentRepository.save(newsContentEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return PostNewsResponseDTO.success(newsNumber);
    }

    @Override
    public ResponseEntity<? super PatchNewsResponseDTO> patchNews(PatchNewsRequestDTO dto, String language, Integer newsNumber, String userId) {
        try {

            NewsEntity newsEntity = newsRepository.findByNewsNumber(newsNumber);
            if (newsEntity == null) return PatchNewsResponseDTO.noExistBoard();

            NewsContentEntity newsContentEntity = newsContentRepository.findByNewsNumberAndLanguageCode(newsNumber, language);
            if (newsContentEntity == null) return PatchNewsResponseDTO.noExistBoard();

            boolean existedUser = userRepository.existsByUserId(userId);
            if (!existedUser) return PatchNewsResponseDTO.noExistUser();

            newsEntity.patchNews();
            newsContentEntity.patchNewsContent(dto);
            newsRepository.save(newsEntity);
            newsContentRepository.save(newsContentEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }

        return PatchNewsResponseDTO.success();
    }

    @Override
    public ResponseEntity<? super DeleteNewsResponseDTO> deleteNews(Integer newsNumber, String userId) {
        try {

            boolean existedUser = userRepository.existsByUserId(userId);
            if (!existedUser) return PatchNewsResponseDTO.noExistUser();

            NewsEntity newsEntity = newsRepository.findByNewsNumber(newsNumber);
            if (newsEntity == null) return DeleteNewsResponseDTO.noExistBoard();

            List<NewsContentEntity> newsContentEntities = newsContentRepository.findByNewsNumber(newsNumber);
            if (newsContentEntities.isEmpty()) {
                return DeleteNewsResponseDTO.noExistBoard(); // 데이터가 없을 경우
            }

            imageRepository.deleteByNewsNumber(newsNumber);
            for (NewsContentEntity newsContentEntity : newsContentEntities) {
                newsContentRepository.delete(newsContentEntity); // 데이터 삭제
            }
            newsRepository.delete(newsEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return DeleteNewsResponseDTO.success();
    }

    @Override
    public ResponseEntity<? super GetNewsListResponseDTO> getNewsList(String lang) {

        List<NewsEntity> newsEntities = new ArrayList<>();
        List<NewsContentEntity> newsContentEntities = new ArrayList<>();

        try {
            newsEntities = newsRepository.findByOrderByNewsWriteDatetimeDesc();
            newsContentEntities = newsContentRepository.findAll();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetNewsListResponseDTO.success(newsEntities, newsContentEntities,lang);
    }

    @Override
    public ResponseEntity<? super GetNewsDetailResponseDTO> getNewsDetail(String language, Integer newsNumber) {

        NewsEntity newsEntity = null;
        NewsContentEntity newsContentEntity = null;
        List<ImageEntity> imageEntities = new ArrayList<>();

        try{
            newsEntity = newsRepository.findByNewsNumber(newsNumber);
            if (newsEntity == null) return GetNewsDetailResponseDTO.notExistNews();

            newsContentEntity = newsContentRepository.findByNewsNumberAndLanguageCode(newsNumber, language);

            if (newsContentEntity == null) {
                return GetNewsDetailResponseDTO.notExistNews();
            }

            imageEntities = imageRepository.findByNewsNumber(newsNumber);

            newsEntity.increaseNewsViewCount();
            newsRepository.save(newsEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDTO.databaseError();
        }
        return GetNewsDetailResponseDTO.success(newsEntity,newsContentEntity, imageEntities);
    }


}
