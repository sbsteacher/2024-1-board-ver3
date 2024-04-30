package com.green.boardver3.comment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.green.boardver3.common.model.Paging;
import lombok.Getter;
import org.springframework.web.bind.annotation.BindParam;

import java.beans.ConstructorProperties;

@Getter
public class CommentPaging extends Paging {
    private long boardId;

    public CommentPaging(Integer page, Integer size, @BindParam("board_id") long boardId) {
        super(page, size);
        this.boardId = boardId;
    }
}
