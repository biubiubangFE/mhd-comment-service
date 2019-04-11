package com.mhdss.comment.dao.impl;

import com.mhdss.comment.dao.CommentDAO;
import com.mhdss.comment.dataobject.CommentDO;
import com.mhdss.comment.query.CommentQuery;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDAOImpl extends BaseDAOImpl<CommentDO, CommentQuery> implements CommentDAO {

    @Override
    protected String getNameSpace() {
        return CommentDAO.class.getName();
    }
}