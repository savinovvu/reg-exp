package ru.inbox.savinov_vu.app.tasks.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.inbox.savinov_vu.app.tasks.comment.model.Comment;



@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
