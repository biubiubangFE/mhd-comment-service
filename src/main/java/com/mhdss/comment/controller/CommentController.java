package com.mhdss.comment.controller;

import com.mhdss.comment.constant.CommentVO;
import com.mhdss.comment.dataobject.UserDO;
import com.mhdss.comment.dto.AuthAgent;
import com.mhdss.comment.para.AddUserPara;
import com.mhdss.comment.service.CommentService;
import com.mhdss.comment.service.UserService;
import com.mhdss.comment.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {


    @Autowired
    private AuthAgent authAgent;

    @Autowired
    private UserService userService;

    @Autowired
    CommentService commentService;


    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData<?> addComment(@RequestParam(value = "resourceId") Long resourceId,
                                      @RequestParam(value = "commentId", required = false) Long commentId,
                                      @RequestParam(value = "commentValue") String commentValue) {

        //添加用户
        AddUserPara addUserPara = agent2UserPara();

        UserDO userDO = userService.addOrUpdateUser(addUserPara);


        CommentVO VO = commentService.addComment(authAgent.getTenantId(), userDO, resourceId, commentId, commentValue);

        return ResponseData.success(VO);
    }

    @RequestMapping(value = "/get", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData<?> getComment(@RequestParam(value = "resourceId") Long resourceId) {

        //添加用户
        AddUserPara addUserPara = agent2UserPara();
        //更新头像
        userService.addOrUpdateUser(addUserPara);
        // 查找resourceId 的

        List<CommentVO> commentVOS = commentService.getComment(authAgent.getTenantId(), resourceId);

        return ResponseData.success(commentVOS);
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseData<?> deleteComment(@RequestParam(value = "commentId") Long commentId) {

        //添加用户
        commentService.deleteComment(commentId, authAgent.getUserId());

        return ResponseData.success();
    }


    private AddUserPara agent2UserPara() {
        AddUserPara para = new AddUserPara();
        para.setUserId(authAgent.getUserId());
        para.setAvatarUrl(authAgent.getAvatarUrl());
        para.setNickName(authAgent.getNickName());
        para.setTenantId(authAgent.getTenantId());
        return para;
    }

}
