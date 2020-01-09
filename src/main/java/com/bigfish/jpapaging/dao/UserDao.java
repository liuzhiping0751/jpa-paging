package com.bigfish.jpapaging.dao;

import com.bigfish.jpapaging.bean.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserDao extends JpaRepository<UserBean,Integer> {
    /**
     *
     * @param userName
     * @return
     */
    UserBean findByUserName(String userName);

}
