package com.shihui.dao;

import com.shihui.model.UserCard;

public interface UserCardMapper {
    UserCard queryByUserId(int userId);
}
