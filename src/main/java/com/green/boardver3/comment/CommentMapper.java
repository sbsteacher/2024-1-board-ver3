package com.green.boardver3.comment;

import com.green.boardver3.comment.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int postComment(CommentPostReq p);
    List<CommentGetRes> getComments(CommentPaging p);
    int putComment(CommentPutReq p);
    int deleteComment(CommentDeleteReq p);
}
