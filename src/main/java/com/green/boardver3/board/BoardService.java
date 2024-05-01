package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import com.green.boardver3.comment.CommentMapper;
import com.green.boardver3.comment.model.CommentGetRes;
import com.green.boardver3.comment.model.CommentPaging;
import com.green.boardver3.common.GlobalConst;
import com.green.boardver3.common.model.Paging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper mapper;
    private final CommentMapper commentMapper;

    public long postBoard(BoardPostReq p) {
        log.info("(1) p.boardId: {}", p.getBoardId());
        int result = mapper.postBoard(p);
        log.info("(2) p.boardId: {}", p.getBoardId());
        if(result == 0) { return 0L; }
        return p.getBoardId();
    }

    public List<BoardGetRes> getBoardList(Paging p) {
        return mapper.getBoardList(p);
    }

    public BoardDetailGetRes getBoardOne(long boardId) {
        BoardDetailGetRes result = mapper.getBoardOne(boardId);
        if(result != null) { // Record가 있다면 조회수 + 1
            mapper.patchBoardHits(boardId);
        }

        CommentPaging paging = new CommentPaging(1, GlobalConst.COMMENT_PAGING_SIZE, boardId);
        List<CommentGetRes> comments = commentMapper.getComments(paging);
        result.setComments(comments);

        if(comments.size() < GlobalConst.COMMENT_PAGING_SIZE) {
            result.setTotalCommentPage(1);
        } else {
            int totalCommentPage = commentMapper.getTotalCommentPage(paging);
            result.setTotalCommentPage(totalCommentPage);
        }
        //result.getComments().addAll(comments);
        return result;
    }

    public int putBoard(BoardPutReq p) {
        return mapper.putBoard(p);
    }

    public int deleteBoard(long boardId) {
        return mapper.deleteBoard(boardId);
    }
}
