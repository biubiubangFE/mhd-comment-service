package com.mhdss.comment.service.impl;

import com.mhdss.comment.constant.BaseStatus;
import com.mhdss.comment.dao.UserDAO;
import com.mhdss.comment.dataobject.UserDO;
import com.mhdss.comment.para.AddUserPara;
import com.mhdss.comment.service.UserService;
import com.mhdss.comment.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDO addOrUpdateUser(AddUserPara para) {

        UserDO userDO = new UserDO();
        userDO.setTanantId(para.getTenantId());
        userDO.setAvatarUrl(para.getAvatarUrl());
        userDO.setNickName(para.getNickName());
        userDO.setTanantUserId(para.getUserId());
        userDO.setStatus(BaseStatus.NORMAL.getStatus());
        Long nowTime = DateUtil.getCurrentTimeStamp();
        userDO.setCreateTime(nowTime);
        userDO.setUpdateTime(nowTime);
        userDAO.insertUpdateOnDuplicate(userDO);
        return userDO;
    }
}
