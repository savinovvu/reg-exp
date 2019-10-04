package ru.inbox.savinov_vu.app.tasks.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.app.tasks.comment.model.Comment;
import ru.inbox.savinov_vu.app.tasks.comment.service.CommentService;

import javax.annotation.Resource;
import java.security.Principal;



@RestController
@RequiredArgsConstructor
public class CommentController {

  @Resource
  private final CommentService commentService;


  @GetMapping("/v1/tasks/comment")
  public ResponseEntity getAll() {
    return new ResponseEntity(commentService.getAll(), HttpStatus.ACCEPTED.OK);
  }


  @GetMapping("/v1/tasks/comment/{id}")
  public ResponseEntity<Comment> getById(@PathVariable("id") Integer id) {
    return new ResponseEntity(commentService.getById(id), HttpStatus.ACCEPTED.OK);
  }


  @PostMapping("/v1/tasks/comment")
  public ResponseEntity add(Comment comment) {
    return new ResponseEntity(commentService.add(comment), HttpStatus.OK);
  }


  @PutMapping("/v1/tasks/comment")
  public ResponseEntity<Comment> update(Comment comment, Principal principal) {
    return new ResponseEntity(commentService.update(comment), HttpStatus.ACCEPTED.OK);
  }


  @DeleteMapping("/v1/tasks/comment/{id}")
  public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
    commentService.delete(id);
    return new ResponseEntity(Boolean.TRUE, HttpStatus.ACCEPTED.OK);
  }


}
