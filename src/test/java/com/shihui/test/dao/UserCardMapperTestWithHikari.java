package com.shihui.test.dao;

import com.shihui.dao.UserCardMapper;
import com.shihui.model.UserCard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-HikariCP.xml")
public class UserCardMapperTestWithHikari {
    @Autowired
    private UserCardMapper userCardMapper;

    @Test
    public void queryByUserId() throws Exception {
        UserCard uc = userCardMapper.queryByUserId(10018);
        System.err.println("" + uc);

    }

}