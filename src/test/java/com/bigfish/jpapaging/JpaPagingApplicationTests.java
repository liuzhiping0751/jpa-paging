package com.bigfish.jpapaging;

import com.bigfish.jpapaging.bean.UserBean;
import com.bigfish.jpapaging.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class JpaPagingApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {

        List<UserBean> userBeans = userDao.findAll();
        for (UserBean u :
                userBeans) {
            System.out.println(u.toString());
        }
    }

}
