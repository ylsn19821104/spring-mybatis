package com.shihui.dao;

import com.shihui.emodel.Sex;
import com.shihui.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hongxp on 2017/7/20.
 */
public interface UserMapper {
    User queryUser(User user);

    User queryUserById(Integer userId);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(Integer userId);

    List<User> queryUserList();

    List<User> queryUsersByName(String name);

    List<User> queryUsersByNameAndEmail(@Param("name") String name, @Param("email") String email);

    List<User> queryUsersBySex(@Param("sexes") List<Sex> sexes);
}

