package ru.inbox.savinov_vu.service.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.interfaces.CRUD.CRUDService;
import ru.inbox.savinov_vu.model.tasks.Like;
import ru.inbox.savinov_vu.repository.tasks.LikeRepository;

import java.util.List;

@Service
public class LikeService implements CRUDService<Like> {
    @Autowired
    LikeRepository likeRepository;


    @Override
    public void add(Like like) {
        likeRepository.saveAndFlush(like);
    }

    @Override
    public List<Like> getAll() {
        return likeRepository.findAll();
    }

    @Override
    public Like getById(Integer id) {
        return likeRepository.findById(id).get();
    }

    @Override
    public boolean delete(Integer id) {
        likeRepository.deleteById(id);
        return true;
    }

    @Override
    public Like update(Like like) {
        return likeRepository.saveAndFlush(like);
    }
}
