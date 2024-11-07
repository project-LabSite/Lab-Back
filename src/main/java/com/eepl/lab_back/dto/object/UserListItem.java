package com.eepl.lab_back.dto.object;


import com.eepl.lab_back.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserListItem {

    private String userId;
    private String userName;
    private int userPass;

    public UserListItem(UserEntity userEntity) {

        this.userId = userEntity.getUserId();
        this.userName = userEntity.getUserName();
        this.userPass = userEntity.getUserPass();
    }

    public static List<UserListItem> getList(List<UserEntity> userEntities) {
        List<UserListItem> list = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserListItem userListItem = new UserListItem(userEntity);
            list.add(userListItem);
        }
        return list;
    }

}
