package ru.inbox.savinov_vu.app.tasks.comment.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;
import ru.inbox.savinov_vu.app.tasks.comment.model.Comment;
import ru.inbox.savinov_vu.app.tasks.comment.repository.CommentRepository;

import javax.annotation.Resource;
import java.util.List;

import static ru.inbox.savinov_vu.common.constant.StringConstants.SUCCESSFULLY_ADDED;



@Service
@AllArgsConstructor
public class CommentService {

  @Resource
  private final CommentRepository commentRepository;


  @Transactional(readOnly = true)
  public List<Comment> getAll() {
    return commentRepository.findAll();
  }


  @Transactional(readOnly = true)
  public Comment getById(Integer id) {
    return commentRepository.findById(id).orElse(null);
  }


  @Transactional
  public OperationResulter<String> add(Comment comment) {
    commentRepository.saveAndFlush(comment);
    return () -> SUCCESSFULLY_ADDED;
  }


  @Transactional
  public Comment update(Comment comment) {
    return commentRepository.saveAndFlush(comment);
  }


  @Transactional
  public boolean delete(Integer id) {
    commentRepository.deleteById(id);
    return true;
  }


}
