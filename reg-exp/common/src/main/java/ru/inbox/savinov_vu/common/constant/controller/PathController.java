package ru.inbox.savinov_vu.common.constant.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static ru.inbox.savinov_vu.common.constant.PathConstant.BACK_END_PATHS;
import static ru.inbox.savinov_vu.common.constant.PathConstant.COMMENT;
import static ru.inbox.savinov_vu.common.constant.PathConstant.LIKE;
import static ru.inbox.savinov_vu.common.constant.PathConstant.NUMBERED;
import static ru.inbox.savinov_vu.common.constant.PathConstant.PARENT;
import static ru.inbox.savinov_vu.common.constant.PathConstant.REGEXP_LEVEL;
import static ru.inbox.savinov_vu.common.constant.PathConstant.REGEXP_TASK;
import static ru.inbox.savinov_vu.common.constant.PathConstant.SIGN_IN;
import static ru.inbox.savinov_vu.common.constant.PathConstant.SIGN_UP;
import static ru.inbox.savinov_vu.common.constant.PathConstant.USER;



@RestController
public class PathController {
    private static Map<String, String> frontEndPaths = new HashMap();

    static {
        frontEndPaths.put("user", USER);
        frontEndPaths.put("regexpTask", REGEXP_TASK);
        frontEndPaths.put("regexpLevel", REGEXP_LEVEL);
        frontEndPaths.put("like", LIKE);
        frontEndPaths.put("comment", COMMENT);
        frontEndPaths.put("numbered", NUMBERED);
        frontEndPaths.put("signin", SIGN_IN);
        frontEndPaths.put("signup", SIGN_UP);
        frontEndPaths.put("parent", PARENT);
    }

    @GetMapping(BACK_END_PATHS)
    @CrossOrigin
    public ResponseEntity<Map<String,String>> getPaths() {
        return new ResponseEntity<>(frontEndPaths, HttpStatus.OK);
    }

}
