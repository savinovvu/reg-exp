package ru.inbox.savinov_vu.controller.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.common.interfaces.CRUD.CRUDController;
import ru.inbox.savinov_vu.common.interfaces.OperationResulter;
import ru.inbox.savinov_vu.model.tasks.Like;
import ru.inbox.savinov_vu.service.tasks.LikeService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;



@RestController
@RequestMapping(value = "/tasks/like", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class LikeController implements CRUDController<Like> {

    @Autowired
    LikeService likeService;


    @Override
    public OperationResulter<String> add(HttpServletRequest request, Like like) {
        return likeService.add(like);
    }


    @Override
    public List<Like> getAll(HttpServletRequest request ) {
        return likeService.getAll();
    }


    @Override
    public Like getById(HttpServletRequest request, @PathVariable("id") Integer id) {
        return likeService.getById(id);
    }


    @Override
    public boolean delete(HttpServletRequest request, @PathVariable("id") Integer id) {
        likeService.delete(id);
        return true;
    }


    @Override
    public Like update(HttpServletRequest request, Like like) {
        return likeService.update(like);
    }
}
