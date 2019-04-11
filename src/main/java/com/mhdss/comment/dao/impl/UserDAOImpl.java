package com.mhdss.comment.dao.impl;

import com.mhdss.comment.dao.UserDAO;
import com.mhdss.comment.dataobject.UserDO;
import com.mhdss.comment.query.UserQuery;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends BaseDAOImpl<UserDO, UserQuery> implements UserDAO {

    @Override
    protected String getNameSpace() {
        return UserDAO.class.getName();
    }
}