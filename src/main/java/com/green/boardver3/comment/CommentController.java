package com.green.boardver3.comment;

import com.green.boardver3.comment.model.*;
import com.green.boardver3.common.model.ResultDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;

    @PostMapping
    public ResultDto<Integer> postComment(@RequestBody CommentPostReq p) {
        int result = service.postComment(p);
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result)
                .build();
    }

    @GetMapping
    public ResultDto<List<CommentGetRes>> getComments(@ParameterObject @ModelAttribute CommentPaging p) {
        log.info("CommentPaging: {}", p);
        List<CommentGetRes> result = service.getComments(p);
        String resultMsg = String.format("row: %d", result.size());
        if(result.size() > 0 && p.getSize() > result.size()) {
            resultMsg += String.format(" totalRows: %d", (p.getPage() - 1) * p.getSize() + result.size());
        }
        return ResultDto.<List<CommentGetRes>>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(resultMsg)
                .resultData(result)
                .build();
    }

    @PutMapping
    public ResultDto<Integer> putComment(@RequestBody CommentPutReq p) {
        int result = service.putComment(p);
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result)
                .build();
    }

    @DeleteMapping
    public ResultDto<Integer> deleteComment(@ModelAttribute CommentDeleteReq p) {
        int result = service.deleteComment(p);
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result)
                .build();
    }
}
