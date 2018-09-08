package ru.inbox.savinov_vu.service.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.common.interfaces.CRUD.CRUDService;
import ru.inbox.savinov_vu.common.interfaces.OperationResulter;
import ru.inbox.savinov_vu.model.tasks.Comment;
import ru.inbox.savinov_vu.repository.tasks.CommentRepository;

import java.util.List;



@Service
public class CommentService implements CRUDService<Comment> {

    @Autowired
    CommentRepository commentRepository;


    @Override
    public OperationResulter<String> add(Comment comment) {
        commentRepository.saveAndFlush(comment);
        return () -> "successfully added";
    }


    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }


    @Override
    public Comment getById(Integer id) {
        return commentRepository.findById(id).get();
    }


    @Override
    public boolean delete(Integer id) {
        commentRepository.deleteById(id);
        return true;
    }


    @Override
    public Comment update(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }
}
