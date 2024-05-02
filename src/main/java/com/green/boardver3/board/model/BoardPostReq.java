package com.green.boardver3.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardPostReq {
    @JsonIgnore
    //@Schema(hidden = true)
    private long boardId;
    @Schema(example = "제목 테스트", description = "글 제목", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;
    @Schema(example = "내용 테스트", description = "글 내용", requiredMode = Schema.RequiredMode.REQUIRED)
    private String contents;

    @JsonProperty("writer_id")
    @Schema(example = "1", description = "유저PK", requiredMode = Schema.RequiredMode.REQUIRED)
    private long writerId;
}
