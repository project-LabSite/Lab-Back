package com.eepl.lab_back.dto.object;


import com.eepl.lab_back.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberListItem {


    private String userName;
    private String userNameE;
    private String userPosition;
    private String userImageUrl;
    private String userEmail;

    public MemberListItem(UserEntity userEntity) {

        this.userName = userEntity.getUserName();
        this.userNameE = userEntity.getUserNameE();
        this.userPosition = userEntity.getUserPosition();
        this.userImageUrl = userEntity.getUserImageUrl();
        this.userEmail = userEntity.getUserEmail();
    }

    public static List<MemberListItem> getMemberList(List<UserEntity> userEntities) {
        List<MemberListItem> list = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            MemberListItem memberListItem = new MemberListItem(userEntity);
            list.add(memberListItem);
        }
        return list;
    }
}
