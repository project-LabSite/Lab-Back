package com.eepl.lab_back.dto.object;


import com.eepl.lab_back.entity.ResearchContentEntity;
import com.eepl.lab_back.entity.ResearchEntity;
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
public class ResearchListItem {

    private String researchTitle;
    private String researchImageUrl;
    public ResearchListItem(ResearchEntity researchEntity, ResearchContentEntity researchContentEntity) {

        this.researchTitle = researchContentEntity.getResearchTitle();
        this.researchImageUrl = researchEntity.getResearchImageUrl();

    }

    public static List<ResearchListItem> getList(List<ResearchEntity> researchEntities, List<ResearchContentEntity> researchContentEntities, String language) {

        List<ResearchListItem> list = new ArrayList<>();

        // researchContentEntities를 Map으로 변환 (key: researchNumber, value: ResearchContentEntity)
        Map<Integer, ResearchContentEntity> contentMap = new HashMap<>();
        for (ResearchContentEntity researchContentEntity : researchContentEntities) {
            if (language.equals(researchContentEntity.getLanguageCode())) {
                contentMap.put(researchContentEntity.getResearchNumber(), researchContentEntity);
            }
        }

        for (ResearchEntity researchEntity : researchEntities) {
            ResearchContentEntity matchedContent = contentMap.get(researchEntity.getResearchNumber());
            if (matchedContent != null) {
                list.add(new ResearchListItem(researchEntity, matchedContent));
            } else {
                System.out.println("No matching ResearchContentEntity for researchNumber: " + researchEntity.getResearchNumber());
            }
        }
        return list;
    }
}
