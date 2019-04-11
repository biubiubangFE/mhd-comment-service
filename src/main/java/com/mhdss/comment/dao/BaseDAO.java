package com.mhdss.comment.dao;

import com.mhdss.comment.dataobject.BaseDO;
import com.mhdss.comment.query.AbstractQuery;
import com.mhdss.comment.query.PagedList;
import java.util.List;

public interface BaseDAO<T extends BaseDO, QUERY extends AbstractQuery> extends MapperDAO<T, QUERY> {
    int insertUnique(T t);

    int updateByQuery(T t, QUERY query);

    T selectByQuery(QUERY query);

    PagedList<T> selectPage(QUERY query);
}