package ru.inbox.savinov_vu.app.tasks.like.service;

import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.core.interfaces.OperationResulter;
import ru.inbox.savinov_vu.app.tasks.like.model.Like;
import ru.inbox.savinov_vu.app.tasks.like.repository.LikeRepository;

import javax.annotation.Resource;
import java.util.List;



@Service
public class LikeService {

    @Resource
    private LikeRepository likeRepository;


    public OperationResulter<String> add(Like like) {
        likeRepository.saveAndFlush(like);
        return () -> "successfully added";
    }


    public List<Like> getAll() {
        return likeRepository.findAll();
    }


    public Like getById(Integer id) {
        return likeRepository.findById(id).get();
    }


    public boolean delete(Integer id) {
        likeRepository.deleteById(id);
        return true;
    }


    public Like update(Like like) {
        return likeRepository.saveAndFlush(like);
    }
}
