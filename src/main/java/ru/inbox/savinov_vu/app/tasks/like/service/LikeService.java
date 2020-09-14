package ru.inbox.savinov_vu.app.tasks.like.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;
import ru.inbox.savinov_vu.app.tasks.like.model.Like;
import ru.inbox.savinov_vu.app.tasks.like.repository.LikeRepository;

import javax.annotation.Resource;
import java.util.List;

import static ru.inbox.savinov_vu.common.constant.StringConstants.SUCCESSFULLY_ADDED;



@Service
@AllArgsConstructor
public class LikeService {

  @Resource
  private final LikeRepository likeRepository;


  @Transactional(readOnly = true)
  public List<Like> getAll() {
    return likeRepository.findAll();
  }


  @Transactional(readOnly = true)
  public Like getById(Integer id) {
    return likeRepository.findById(id).orElse(null);
  }


  @Transactional
  public OperationResulter<String> add(Like like) {
    likeRepository.saveAndFlush(like);
    return () -> SUCCESSFULLY_ADDED;
  }


  @Transactional
  public Like update(Like like) {
    return likeRepository.saveAndFlush(like);
  }


  @Transactional
  public boolean delete(Integer id) {
    likeRepository.deleteById(id);
    return true;
  }
}
