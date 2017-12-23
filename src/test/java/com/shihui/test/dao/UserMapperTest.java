package com.shihui.test.dao;

import com.google.common.collect.Lists;
import com.shihui.dao.UserMapper;
import com.shihui.emodel.Sex;
import com.shihui.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * Created by hongxp on 2017/7/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectUser() throws Exception {
        User user = userMapper.queryUserById(10018);
        System.err.println("" + user);

        User u = userMapper.queryUserById(10018);
        System.err.println("" + (u == user));
    }

    @Test
    public void insertUser() throws Exception {
        User u = new User();
        u.setEmail("999@qq.com");
        u.setName("99");
        u.setSex(Sex.MALE);

        userMapper.insertUser(u);
        System.err.println("" + u);
    }

    @Test
    public void updateUser() throws Exception {
        User u = new User();
        u.setId(10019);
        u.setName("blue");
        u.setSex(Sex.MALE);
        u.setEmail("blue@shihui.com");

        userMapper.updateUser(u);
    }

    @Test
    public void deleteUser() throws Exception {
    }

    @Test
    public void selectUserList() throws Exception {
    }

    @Test
    public void queryUsersByName() throws Exception {
        List<User> users = userMapper.queryUsersByName("s");
        System.err.println("" + users);
    }

    @Test
    public void queryUsersByNameAndEmail() throws Exception {
        List<User> users = userMapper.queryUsersByNameAndEmail("e", "66");
        System.err.println("" + users);

        users = userMapper.queryUsersByNameAndEmail("", "66");
        System.err.println("" + users);
    }

    @Test
    public void queryUsersBySexes() throws Exception {
        List<Sex> sexes = Lists.newArrayList();
        sexes.add(Sex.MALE);
        sexes.add(Sex.UNKNOWN);
        List<User> users = userMapper.queryUsersBySex(sexes);
        System.err.println("" + users);
    }
}