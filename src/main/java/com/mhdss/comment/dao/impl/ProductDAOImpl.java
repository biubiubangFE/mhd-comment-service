package com.mhdss.comment.dao.impl;

import com.mhdss.comment.dao.ProductDAO;
import com.mhdss.comment.dataobject.ProductDO;
import com.mhdss.comment.query.ProductQuery;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDAOImpl extends BaseDAOImpl<ProductDO, ProductQuery> implements ProductDAO {

    @Override
    protected String getNameSpace() {
        return ProductDAO.class.getName();
    }
}