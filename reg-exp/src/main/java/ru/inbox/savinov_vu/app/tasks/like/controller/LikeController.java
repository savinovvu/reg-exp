package ru.inbox.savinov_vu.app.tasks.like.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;
import ru.inbox.savinov_vu.app.tasks.like.model.Like;
import ru.inbox.savinov_vu.app.tasks.like.service.LikeService;

import javax.annotation.Resource;
import java.util.List;



@RestController
public class LikeController {

    @Resource
    private LikeService likeService;

    @PostMapping("/v1/tasks/like")
    public ResponseEntity<OperationResulter<String>> add(Like like) {
        return new ResponseEntity(likeService.add(like), HttpStatus.ACCEPTED.OK);
    }

    @GetMapping("/v1/tasks/like")
    public ResponseEntity<List<Like>> getAll() {
        return new ResponseEntity(likeService.getAll(), HttpStatus.ACCEPTED.OK);
    }

    @GetMapping("/v1/tasks/like/{id}")
    public ResponseEntity<Like> getById(@PathVariable("id") Integer id) {
        return new ResponseEntity(likeService.getById(id), HttpStatus.ACCEPTED.OK);
    }

    @DeleteMapping("/v1/tasks/like/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        likeService.delete(id);
        return new ResponseEntity(Boolean.TRUE, HttpStatus.ACCEPTED.OK);
    }

    @PutMapping("/v1/tasks/like")
    public ResponseEntity<Like> update(Like like) {
        return new ResponseEntity(likeService.update(like), HttpStatus.ACCEPTED.OK);
    }
}
