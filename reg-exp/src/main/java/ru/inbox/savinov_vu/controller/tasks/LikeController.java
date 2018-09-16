package ru.inbox.savinov_vu.controller.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.common.interfaces.CRUD.CRUDController;
import ru.inbox.savinov_vu.common.interfaces.OperationResulter;
import ru.inbox.savinov_vu.model.tasks.Like;
import ru.inbox.savinov_vu.service.tasks.LikeService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;



@RestController
@RequestMapping(value = "/tasks/like", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class LikeController implements CRUDController<Like> {

    @Autowired
    LikeService likeService;


    @Override
    public ResponseEntity<OperationResulter<String>> add(HttpServletRequest request, Like like, Principal principal) {
        return new ResponseEntity(likeService.add(like), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<List<Like>> getAll(HttpServletRequest request, Principal principal) {
        return new ResponseEntity(likeService.getAll(), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<Like> getById(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        return new ResponseEntity(likeService.getById(id), HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<Boolean> delete(HttpServletRequest request, @PathVariable("id") Integer id, Principal principal) {
        likeService.delete(id);
        return new ResponseEntity(Boolean.TRUE, HttpStatus.ACCEPTED.OK);
    }


    @Override
    public ResponseEntity<Like> update(HttpServletRequest request, Like like, Principal principal) {
        return new ResponseEntity(likeService.update(like), HttpStatus.ACCEPTED.OK);
    }
}
