package com.green.boardver3.comment.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.green.boardver3.common.model.Paging;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.bind.annotation.BindParam;

import java.beans.ConstructorProperties;


@Getter
@ToString(callSuper = true)
public class CommentPaging extends Paging {
    @Schema(name = "board_id", example = "1", description = "게시글PK", requiredMode = Schema.RequiredMode.REQUIRED)
    //@Parameter(name = "board_id", example = "1", description = "게시글PK",  required = true)
    private long boardId;

    //@ConstructorProperties({"page", "size", "board_id"})
    public CommentPaging(Integer page, Integer size, @BindParam("board_id") long boardId) {
        super(page, size);
        this.boardId = boardId;
    }
}
