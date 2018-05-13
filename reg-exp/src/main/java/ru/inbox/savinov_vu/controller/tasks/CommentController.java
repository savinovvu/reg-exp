package ru.inbox.savinov_vu.controller.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.interfaces.CRUD.CRUDController;
import ru.inbox.savinov_vu.interfaces.OperationResulter;
import ru.inbox.savinov_vu.model.tasks.Comment;
import ru.inbox.savinov_vu.service.tasks.CommentService;

import java.util.List;



@RestController
@RequestMapping(value = "/tasks/comment", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController implements CRUDController<Comment> {

    @Autowired
    CommentService commentService;


    @Override
    public OperationResulter<String> add(Comment comment) {
       return commentService.add(comment);
    }


    @Override
    public List<Comment> getAll() {
        return commentService.getAll();
    }


    @Override
    public Comment getById(@PathVariable("id") Integer id) {
        return commentService.getById(id);
    }


    @Override
    public boolean delete(@PathVariable("id") Integer id) {
        commentService.delete(id);
        return true;
    }


    @Override
    public Comment update(Comment comment) {
        return commentService.update(comment);
    }
}
