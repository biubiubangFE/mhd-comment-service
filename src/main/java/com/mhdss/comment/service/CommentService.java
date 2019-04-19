package com.mhdss.comment.service;


import com.mhdss.comment.constant.CommentVO;
import com.mhdss.comment.dataobject.UserDO;

import java.util.List;

public interface CommentService {

    CommentVO addComment(Long tenantId, UserDO user, Long resourceId, Long commentId, String commentValue);

    List<CommentVO> getComment(Long tenantId, Long resourceId);

    void deleteComment(Long commentId, Long userId);

}
