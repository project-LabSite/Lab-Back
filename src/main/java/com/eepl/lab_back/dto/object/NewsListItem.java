package com.eepl.lab_back.dto.object;

import com.eepl.lab_back.entity.NewsContentEntity;
import com.eepl.lab_back.entity.NewsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NewsListItem {

    private String newsTitle;
    private String newsWriteDatetime;
    private int newsViewCount;

    public NewsListItem(NewsEntity newsEntity, NewsContentEntity newsContentEntity) {

        this.newsTitle = newsContentEntity.getNewsTitle();
        this.newsWriteDatetime = newsEntity.getNewsWriteDatetime();
        this.newsViewCount = newsEntity.getNewsViewCount();
    }

    public static List<NewsListItem> getList(List<NewsEntity> newsEntities, List<NewsContentEntity> newsContentEntities,String lang) {

        List<NewsListItem> list = new ArrayList<>();

        // newsContentEntities를 Map으로 변환 (key: newsNumber, value: NewsContentEntity)
        Map<Integer, NewsContentEntity> contentMap = new HashMap<>();
        for (NewsContentEntity newsContentEntity : newsContentEntities) {
            if (lang.equals(newsContentEntity.getLanguageCode())) {
                contentMap.put(newsContentEntity.getNewsNumber(), newsContentEntity);
            }
        }

        // newsEntities 순서를 유지하며 NewsListItem 생성
        for (NewsEntity newsEntity : newsEntities) {
            NewsContentEntity matchedContent = contentMap.get(newsEntity.getNewsNumber());
            if (matchedContent != null) {
                list.add(new NewsListItem(newsEntity, matchedContent));
            } else {
                // 매칭 실패 로그
                System.out.println("No matching NewsContentEntity for newsNumber: " + newsEntity.getNewsNumber());
            }
        }

        return list;
    }
}
