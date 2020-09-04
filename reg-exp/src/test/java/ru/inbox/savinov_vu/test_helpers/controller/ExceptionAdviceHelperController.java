package ru.inbox.savinov_vu.test_helpers.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionAdviceHelperController {

  @GetMapping("/error/invoke")
  public ResponseEntity<String> make() {
    return new ResponseEntity<>("good way", HttpStatus.OK);
  }
}
