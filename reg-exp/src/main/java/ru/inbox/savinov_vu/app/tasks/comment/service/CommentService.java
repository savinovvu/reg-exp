package ru.inbox.savinov_vu.app.tasks.comment.service;

import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;
import ru.inbox.savinov_vu.app.tasks.comment.model.Comment;
import ru.inbox.savinov_vu.app.tasks.comment.repository.CommentRepository;

import javax.annotation.Resource;
import java.util.List;



@Service
public class CommentService {

    @Resource
    private CommentRepository commentRepository;


    public OperationResulter<String> add(Comment comment) {
        commentRepository.saveAndFlush(comment);
        return () -> "successfully added";
    }


    public List<Comment> getAll() {
        return commentRepository.findAll();
    }


    public Comment getById(Integer id) {
        return commentRepository.findById(id).get();
    }


    public boolean delete(Integer id) {
        commentRepository.deleteById(id);
        return true;
    }


    public Comment update(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }
}
