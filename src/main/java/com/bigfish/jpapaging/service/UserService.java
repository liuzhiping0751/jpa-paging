package com.bigfish.jpapaging.service;

import com.bigfish.jpapaging.bean.PageUtil;
import com.bigfish.jpapaging.bean.UserBean;

public interface UserService {
    /**
     * 查询全部，并分页
     * @param pageNum 页数
     * @param pageSize 页数据大小
     * @return
     */
    PageUtil<UserBean> setectAll(int pageNum, int pageSize);

}
