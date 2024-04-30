package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import com.green.boardver3.common.model.Paging;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int postBoard(BoardPostReq p);
    List<BoardGetRes> getBoardList(Paging p);
    BoardDetailGetRes getBoardOne(long boardId);
    int putBoard(BoardPutReq p);
    int patchBoardHits(long boardId);
    int deleteBoard(long boardId);
}
