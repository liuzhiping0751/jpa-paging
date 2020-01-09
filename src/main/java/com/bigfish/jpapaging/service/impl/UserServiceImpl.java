package com.bigfish.jpapaging.service.impl;

import com.bigfish.jpapaging.bean.PageUtil;
import com.bigfish.jpapaging.bean.UserBean;
import com.bigfish.jpapaging.dao.UserDao;
import com.bigfish.jpapaging.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author lzp
 * @date 2020/1/6
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    /**
     *
     * @param pageNum 页数
     * @param pageSize 页数据大小
     * @return
     */
    @Override
    public PageUtil<UserBean> setectAll(int pageNum, int pageSize) {
        PageUtil<UserBean> pageUtil = new PageUtil<>();
        Pageable pageable = PageRequest.of(pageNum, pageSize,Sort.Direction.ASC,"userId");
        Page<UserBean> userBeans = userDao.findAll(pageable);

        //数据
        pageUtil.setContent(userBeans.getContent());

        //总页数
        pageUtil.setTotalPages(userBeans.getTotalPages());

        //总数据
        pageUtil.setTotalElements(Math.toIntExact(userBeans.getTotalElements()));

        //每页大小
        pageUtil.setPageSize(userBeans.getSize());

        //当前页码
        pageUtil.setNumber(userBeans.getNumber());

        return pageUtil;
    }
}
