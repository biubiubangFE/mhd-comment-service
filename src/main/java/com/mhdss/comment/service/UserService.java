package com.mhdss.comment.service;

import com.mhdss.comment.dataobject.UserDO;
import com.mhdss.comment.para.AddUserPara;

public interface UserService {

    UserDO addOrUpdateUser(AddUserPara para);
}
