package ru.inbox.savinov_vu.controller.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.common.interfaces.CRUD.CRUDController;
import ru.inbox.savinov_vu.model.tasks.Comment;
import ru.inbox.savinov_vu.service.tasks.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;



@RestController
@RequestMapping(value = "/tasks/comment", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController implements CRUDController<Comment> {

    @Autowired
    CommentService commentService;


    @Override
    public ResponseEntity add(HttpServletRequest request, Comment comment, Principal principal) {
        return new ResponseEntity(commentService.add(comment), HttpStatus.OK);
    }


    @Override
    public ResponseEntity getAll(HttpServletRequest request, Principal principal) {
        return new ResponseEntity(commentService.getAll(), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<Comment> getById(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        return new ResponseEntity(commentService.getById(id), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<Boolean> delete(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        commentService.delete(id);
        return new ResponseEntity(Boolean.TRUE, HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<Comment> update(HttpServletRequest request, Comment comment, Principal principal) {
        return new ResponseEntity(commentService.update(comment), HttpStatus.ACCEPTED.OK);
    }
}
