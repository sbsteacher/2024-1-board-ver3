package com.green.boardver3.user;

import com.green.boardver3.common.model.ResultDto;
import com.green.boardver3.user.model.ChangePasswordPatchReq;
import com.green.boardver3.user.model.SignInPostReq;
import com.green.boardver3.user.model.UserEntity;
import com.green.boardver3.user.model.UserPostReq;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    public int postUser(UserPostReq p) {
        //비밀번호 암호화
        String hashedPassword = BCrypt.hashpw(p.getUpw(), BCrypt.gensalt());
        p.setUpw(hashedPassword);
        return mapper.postUser(p);
    }

    //1: 로그인 성공, 2: 아이디 없음, 3: 비밀번호 다름
    public ResultDto<Long> postSignIn(SignInPostReq p) {
        UserEntity entity = mapper.getUserById(p.getUid());
        if(entity == null) { //아이디 없음
            return ResultDto.<Long>builder()
                .statusCode(HttpStatus.NOT_FOUND)
                .resultMsg("아이디를 확인해 주세요.")
                .resultData(0L)
                .build();
        } else if(!BCrypt.checkpw(p.getUpw(), entity.getUpw())) {
            return ResultDto.<Long>builder()
                    .statusCode(HttpStatus.NOT_FOUND)
                    .resultMsg("비밀번호를 확인해 주세요.")
                    .resultData(0L)
                    .build();
        }
        return ResultDto.<Long>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("로그인 성공")
                .resultData(entity.getUserId())
                .build();
    }

    //1: 비밀번호 변경 성공, 2: 아이디를 확인해 주세요. 3: 기존 비밀번호를 확인해 주세요.
    public int patchPassword(ChangePasswordPatchReq p) {
        // 현재 비밀번호,
        UserEntity entity = mapper.getUserById(p.getUid());
        if(entity == null) {
            return 2;
        } else if(!BCrypt.checkpw(p.getCurrentPw(), entity.getUpw())) {
            return 3;
        }
        //비밀번호 변경
        String hashedPassword = BCrypt.hashpw(p.getNewPw(), BCrypt.gensalt());
        p.setNewPw(hashedPassword);
        p.setUserId(entity.getUserId());
        return mapper.patchPassword(p);
    }
}
