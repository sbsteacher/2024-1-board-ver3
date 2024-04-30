package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import com.green.boardver3.comment.CommentMapper;
import com.green.boardver3.comment.model.CommentGetRes;
import com.green.boardver3.comment.model.CommentPaging;
import com.green.boardver3.common.GlobalConst;
import com.green.boardver3.common.model.Paging;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper mapper;
    private final CommentMapper commentMapper;

    public int postBoard(BoardPostReq p) {
        return mapper.postBoard(p);
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
