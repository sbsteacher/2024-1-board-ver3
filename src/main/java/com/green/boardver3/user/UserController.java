package com.green.boardver3.user;

import com.green.boardver3.common.model.ResultDto;
import com.green.boardver3.user.model.ChangePasswordPatchReq;
import com.green.boardver3.user.model.SignInPostReq;
import com.green.boardver3.user.model.UserPostReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService service;

    @PostMapping("signup")
    public ResponseEntity<ResultDto<Integer>> postUser(@RequestBody UserPostReq p) {
        int result = service.postUser(p);

        return ResponseEntity.ok(ResultDto.<Integer>builder()
                                        .statusCode(HttpStatus.OK)
                                        .resultMsg(HttpStatus.OK.toString())
                                        .resultData(result)
                                        .build()
        );
    }

    @PostMapping("signin")
    public ResultDto<Long> postSignin(@RequestBody SignInPostReq p) {
        return service.postSignIn(p);
    }

    @PatchMapping("password")
    public ResultDto<Integer> patchPassword(@Valid @RequestBody ChangePasswordPatchReq p) {
        int result = service.patchPassword(p);

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("")
                .resultData(result).build();
    }


}
