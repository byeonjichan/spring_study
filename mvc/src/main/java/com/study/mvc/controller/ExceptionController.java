package com.study.mvc.controller;

import com.study.mvc.exception.DuplicatedException;
import com.study.mvc.service.DBStudyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ExceptionController {
    private DBStudyService dbStudyService;

    @GetMapping("/ex")
    public ResponseEntity<?> duplication(@RequestBody Map<String, String> map) {

        dbStudyService.checkDuplicatedByName(map.get("name"));
        return ResponseEntity.ok("중복되지 않은 이름입니다.");
    }
}
