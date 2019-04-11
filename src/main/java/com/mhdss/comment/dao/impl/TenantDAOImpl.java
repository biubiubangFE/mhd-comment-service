package com.mhdss.comment.dao.impl;

import com.mhdss.comment.dao.TenantDAO;
import com.mhdss.comment.dataobject.TenantDO;
import com.mhdss.comment.query.TenantQuery;
import org.springframework.stereotype.Repository;

@Repository
public class TenantDAOImpl extends BaseDAOImpl<TenantDO, TenantQuery> implements TenantDAO {

    @Override
    protected String getNameSpace() {
        return TenantDAO.class.getName();
    }
}