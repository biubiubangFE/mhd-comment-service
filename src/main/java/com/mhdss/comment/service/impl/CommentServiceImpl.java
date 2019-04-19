package com.mhdss.comment.service.impl;

import com.mhdss.comment.constant.CommentTopType;
import com.mhdss.comment.constant.CommentVO;
import com.mhdss.comment.constant.TenantUserVO;
import com.mhdss.comment.dao.CommentDAO;
import com.mhdss.comment.dao.UserDAO;
import com.mhdss.comment.dataobject.CommentDO;
import com.mhdss.comment.dataobject.UserDO;
import com.mhdss.comment.query.CommentQuery;
import com.mhdss.comment.service.CommentService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDAO commentDAO;
    @Autowired
    private UserDAO userDAO;

    @Override
    public CommentVO addComment(Long tenantId, UserDO userDO, Long resourceId, Long commentId, String commentValue) {

        CommentDO commentDO = new CommentDO();

        commentDO.setTenantResourceId(resourceId);
        if (null == commentId || 0 == commentId) {
            commentDO.setTopType(CommentTopType.TOP.getType());
            commentDO.setParentId(-1L);
        } else {
            commentDO.setTopType(CommentTopType.LOVER.getType());
            commentDO.setParentId(commentId);
        }

        commentDO.setUserId(userDO.getId());
        commentDO.setCommentValue(commentValue);
        commentDAO.insert(commentDO);
        CommentVO VO = commentDO2VO(commentDO);
        VO.setUserVO(UserDO2VO(userDO));
        VO.setChileComment(new ArrayList<>());
        return VO;
    }

    @Override
    public List<CommentVO> getComment(Long tenantId, Long resourceId) {

        CommentQuery query = new CommentQuery();
        query.setTenantId(tenantId);
        query.setTenantResourceId(resourceId);

        List<CommentDO> commentDOList = commentDAO.selectList(query);

        if (CollectionUtils.isEmpty(commentDOList)) {
            return null;
        }
        List<Long> userIds = commentDOList.stream().map(CommentDO::getUserId).collect(Collectors.toList());
        List<UserDO> userDOS = userDAO.selectByIds(userIds);

        Map<Long, UserDO> userDOMap = userDOS.stream().collect(Collectors.toMap(UserDO::getId, e -> e));

        Map<Long, List<CommentDO>> commentDOMap = commentDOList.stream().collect(Collectors.groupingBy(CommentDO::getParentId));
        List<CommentDO> topComments = commentDOMap.get(-1L);
        List<CommentVO> commentVOS = new ArrayList<>();
        for (CommentDO topComment : topComments) {
            commentVOS.add(getChileComment(topComment, commentDOMap, userDOMap));
        }
        return commentVOS;

    }

    @Override
    public void deleteComment(Long commentId, Long userId) {


        CommentQuery query = new CommentQuery();
        query.setId(commentId);
        query.setUserId(userId);
        commentDAO.deleteByQuery(query);
    }

    private TenantUserVO UserDO2VO(UserDO DO) {
        if (null == DO) {
            return null;
        }
        TenantUserVO VO = new TenantUserVO();
        VO.setAvatarUrl(DO.getAvatarUrl());
        VO.setNickName(DO.getNickName());
        VO.setTanantUserId(DO.getTanantUserId());

        return VO;
    }

    private CommentVO commentDO2VO(CommentDO DO) {
        if (null == DO) {
            return null;
        }
        CommentVO VO = new CommentVO();
        VO.setCommentId(DO.getId());
        VO.setUserId(DO.getUserId());
        VO.setCommentValue(DO.getCommentValue());
        return VO;
    }

    public CommentVO getChileComment(CommentDO DO, Map<Long, List<CommentDO>> commentDOMap, Map<Long, UserDO> userDOMap) {

        Long parentId = DO.getId();
        CommentVO VO = commentDO2VO(DO);
        if (commentDOMap.containsKey(parentId)) {
            List<CommentDO> commentDOS = commentDOMap.get(parentId);

            for (CommentDO commentDO : commentDOS) {
                return getChileComment(commentDO, commentDOMap, userDOMap);
            }

        } else {
            Long userId = DO.getUserId();
            UserDO userDO = userDOMap.get(userId);

            VO.setUserVO(UserDO2VO(userDO));
            VO.setChileComment(new ArrayList<>());
        }

        return VO;
    }
}
