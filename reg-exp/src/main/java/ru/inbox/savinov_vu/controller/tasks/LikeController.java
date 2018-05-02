package ru.inbox.savinov_vu.controller.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.interfaces.CRUD.CRUDController;
import ru.inbox.savinov_vu.model.tasks.Like;
import ru.inbox.savinov_vu.service.tasks.LikeService;

import java.util.List;

@RestController
@RequestMapping(value = "/tasks/like", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class LikeController implements CRUDController<Like> {
    @Autowired
    LikeService likeService;


    @Override
    public void add(Like like) {
        likeService.add(like);
    }

    @Override
    public List<Like> getAll() {
        return likeService.getAll();
    }

    @Override
    public Like getById(@PathVariable("id") Integer id) {
        return likeService.getById(id);
    }

    @Override
    public boolean delete(@PathVariable("id") Integer id) {
        likeService.delete(id);
        return true;
    }

    @Override
    public Like update(Like like) {
        return likeService.update(like);
    }
}
