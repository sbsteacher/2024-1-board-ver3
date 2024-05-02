package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import com.green.boardver3.common.model.ResultDto;
import com.green.boardver3.common.model.Paging;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("board")
@Tag(name = "Board (게시판)", description = "게시판 CRUD")
public class BoardController {
    private final BoardService service;

    @PostMapping
    @Operation(summary = "게시글 등록", description = "게시글 등록을 할 수 있습니다.")
    public ResultDto<Long> postBoard(@RequestBody BoardPostReq p) {
        log.info("p의 파라미터: {}", p);

        long result = service.postBoard(p);

        return ResultDto.<Long>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("")
                .resultData(result)
                .build();
    }

    @GetMapping
    public ResultDto<List<BoardGetRes>> getBoardList(@ModelAttribute Paging p) {
        List<BoardGetRes> list = service.getBoardList(p);

        return ResultDto.<List<BoardGetRes>>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(String.format("rowCount: %d", list.size()))
                .resultData(list)
                .build();
    }

    //http://localhost:8080/board/203

    @GetMapping("{boardId}")
    public ResultDto<BoardDetailGetRes> getBoardOne(@PathVariable long boardId) {
        BoardDetailGetRes result = service.getBoardOne(boardId);

        return ResultDto.<BoardDetailGetRes>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(result == null ? "내용을 찾을 수 없습니다." : HttpStatus.OK.toString())
                .resultData(result).build();
    }

    @PutMapping
    public ResultDto<Integer> putBoard(@RequestBody BoardPutReq p) {
        int result = service.putBoard(p);
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result).build();
    }

    @DeleteMapping
    public ResultDto<Integer> deleteBoard(@RequestParam(name = "board_id") long boardId
    , @RequestParam(name="test", required = false) String test) {
        System.out.println("boardId: " + boardId);
        System.out.println("test: " + test);
//        int result = service.deleteBoard(boardId);
//        return ResultDto.<Integer>builder()
//                .statusCode(HttpStatus.OK)
//                .resultMsg(HttpStatus.OK.toString())
//                .resultData(result).build();
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(1).build();
    }
}
