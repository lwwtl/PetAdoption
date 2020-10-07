package com.pet.demo;

import com.pet.demo.dao.UserDao;
import com.pet.demo.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private UserDao userDao;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession();
        //4.获取dao的代理对象
        userDao = sqlSession.getMapper(UserDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        //提交事务
         sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }
    @Test
    public void findAlltest() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("-----每个用户的信息------");
            System.out.println(user);
        }
    }

    @Test
    public void savetest() {

        User user=new User();
        user.setUserId("test");
        user.setUserName("付狗妮");
        user.setUserAccount("test");
        user.setUserName("test");
        user.setUserAge("22");
        user.setUserSex("test");
        user.setUserTelephone("test");
        user.setUserEmail("test");
        user.setUserAddress("test");
        user.setUserState("1111");
        userDao.save(user);
    }


}
