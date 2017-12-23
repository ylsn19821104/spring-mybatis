package com.shihui.test.dao;

import com.shihui.dao.PaymentMapper;
import com.shihui.dao.UserCardMapper;
import com.shihui.dao.UserMapper;
import com.shihui.intercept.ExampleInterceptor;
import com.shihui.intercept.SQLStatsInterceptor;
import com.shihui.intercept.SqlCostInterceptor;
import com.shihui.model.Payment;
import com.shihui.model.User;
import com.shihui.model.UserCard;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class SqlMapperTest {

    @Test
    public void test() {
        PooledDataSource dataSource = new PooledDataSource();
        dataSource.setDriver("net.sf.log4jdbc.DriverSpy");
        dataSource.setUrl("jdbc:log4jdbc:mysql://localhost:3306/spring");
        dataSource.setUsername("root");
        dataSource.setPassword("rshi.cn");

        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment env = new Environment("development", transactionFactory, dataSource);
        Configuration conf = new Configuration(env);
        conf.setCacheEnabled(true);
        conf.getTypeAliasRegistry().registerAliases("com.shihui.model");
        conf.addMappers("com.shihui.dao");
        conf.addInterceptor(new ExampleInterceptor());
        conf.addInterceptor(new SQLStatsInterceptor());
        conf.addInterceptor(new SqlCostInterceptor());

        SqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(conf);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User u = userMapper.queryUserById(1015);
        System.err.println("" + u);

        User user = userMapper.queryUserById(1015);
        System.err.println("" + (u == user));


        UserCardMapper userCardMapper = sqlSession.getMapper(UserCardMapper.class);
        UserCard card1 = userCardMapper.queryByUserId(10018);
        sqlSession.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserCardMapper ucm = sqlSession2.getMapper(UserCardMapper.class);
        UserCard card2 = ucm.queryByUserId(10018);
        System.err.println("" + (card1 == card2));

    }

    @Test
    public void buildWithResource() {
        try {
            InputStream resource = Resources.getResourceAsStream("mybatis-config-no-spring.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(resource);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            System.err.println("" + userMapper.queryUserById(10018));

            PaymentMapper paymentMapper = sqlSession.getMapper(PaymentMapper.class);
            /*Payment payment = new Payment();
            payment.setPrice(22);
            payment.setPaymentType(2);
            payment.setUserId(301);
            paymentMapper.insertPayment(payment);
            System.err.println("" + payment);
            Payment payment = new Payment();
            payment.setTransId(99818520297779120L);
            payment.setPrice(33);
            paymentMapper.updatePayment(payment);
            Payment payment = paymentMapper.queryByTransId(99818520297779120L);
            System.err.println("" + payment);*/

            Payment payment2 = new Payment();
            payment2.setPrice(44);
            payment2.setPaymentType(2);
            payment2.setUserId(304);
            payment2.setFromAccount("444@qq.com");
            paymentMapper.insert(payment2);
            System.err.println("" + payment2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
