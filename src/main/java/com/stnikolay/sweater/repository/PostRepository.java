package com.stnikolay.sweater.repository;

import com.stnikolay.sweater.model.Post;
import com.stnikolay.sweater.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByTitle(String title);

    List<Post> findAllByAuthor(User user);

    void deleteAllByAuthor(User user);

}