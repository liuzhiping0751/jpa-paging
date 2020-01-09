package com.bigfish.jpapaging.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class MsgData implements Serializable {

    private List<UserBean> userBeanList;

    private int data;

    public MsgData(List<UserBean> list) {
        this.userBeanList = list;
        this.data = list.size();
    }



}
