package ru.inbox.savinov_vu.app.tasks.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.inbox.savinov_vu.app.tasks.like.model.Like;



@Repository
public interface LikeRepository extends JpaRepository<Like, Integer> {

}
