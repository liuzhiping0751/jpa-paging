package com.bigfish.jpapaging.controller;

import com.bigfish.jpapaging.bean.MsgData;
import com.bigfish.jpapaging.bean.PageUtil;
import com.bigfish.jpapaging.bean.UserBean;
import com.bigfish.jpapaging.dao.UserDao;
import com.bigfish.jpapaging.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @date 2019/12/31
 * @author lzp
 */
@Controller
public class IndexController {

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    @GetMapping("/index")
    public String indexPage() {

        return "index";
    }

    @GetMapping("/index/hou")
    public String indexHou() {
        return "indexHou";
    }

    /**
     * Bootstrap前端分页
     * @return
     */
    @ResponseBody
    @GetMapping("/user/queryUser")
    public MsgData queryUser() {
        return new MsgData(userDao.findAll());
    }

    /**
     *Bootstrap分页
     * @param pageNum 当前页面
     * @param pageRow 页面数据
     * @return 数据
     */
    @ResponseBody
    @GetMapping("/user/queryList")
    public PageUtil<UserBean> queryList(Integer pageNum, Integer pageRow) {
        return  userService.setectAll(pageNum, pageRow);
    }

    /**
     *Layui分页
     * @param page 当前页面
     * @param limit 页面数据
     * @return 数据
     */
    @ResponseBody
    @GetMapping("/user/queryLay")
    public PageUtil<UserBean> queryLay(Integer page, Integer limit) {
        return userService.setectAll(page - 1, limit);
    }

    @GetMapping("/layuiIndex")
    public String layuiIndex() {
        return "layuiIndex";
    }

    @GetMapping("/laypage")
    public String layPage() {
        return "laypage";
    }

    @GetMapping("/laypageHou")
    public String layPageHou() {
        return "laypage_hou";
    }

}
