package ru.inbox.savinov_vu.controller.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.interfaces.CRUDController;
import ru.inbox.savinov_vu.model.tasks.Comment;
import ru.inbox.savinov_vu.service.tasks.CommentService;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks/comment", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController implements CRUDController<Comment> {
    @Autowired
    CommentService commentService;


    @Override
    public void add(Comment comment) {
        commentService.add(comment);
    }

    @Override
    public List<Comment> getAll() {
        return commentService.getAll();
    }

    @Override
    public Comment getById(Integer id) {
        return commentService.getById(id);
    }

    @Override
    public boolean delete(Integer id) {
        commentService.delete(id);
        return true;
    }

    @Override
    public Comment update(Comment comment) {
        return commentService.update(comment);
    }
}
