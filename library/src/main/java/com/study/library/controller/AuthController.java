package com.study.library.controller;

import com.study.library.aop.annotation.ValidAspect;
import com.study.library.dto.OAuth2MergeReqDto;
import com.study.library.dto.OAuth2SignupReqDto;
import com.study.library.dto.SigninReqDto;
import com.study.library.dto.SignupReqDto;
import com.study.library.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ValidAspect
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupReqDto signupReqDto, BindingResult bindingResult) {
        authService.signup(signupReqDto);
        return ResponseEntity.created(null).body(true);
    }
    @ValidAspect
    @PostMapping("/oauth2/signup")
    public ResponseEntity<?> oAuth2Signup(@Valid @RequestBody OAuth2SignupReqDto oAuth2SignupReqDto, BindingResult bindingResult) {
        authService.oAuth2Signup(oAuth2SignupReqDto);
        return ResponseEntity.created(null).body(true);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SigninReqDto signinReqDto) {

        return ResponseEntity.ok(authService.signin(signinReqDto));
    }
    @PostMapping("/oauth2/signin")
    public ResponseEntity<?> oAuth2Merge() {
        return ResponseEntity.ok(null);
    }

    @PostMapping("oauth2/merge")
    public ResponseEntity<?> oAuth2Merge(@RequestBody OAuth2MergeReqDto oAuth2MergeREqDto) {
        authService.oAuth2Merge(oAuth2MergeREqDto);
        return ResponseEntity.ok(true);
    }
}
