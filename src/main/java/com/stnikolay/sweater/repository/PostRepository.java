package com.stnikolay.sweater.repository;

import com.stnikolay.sweater.model.Post;
import com.stnikolay.sweater.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByTitle(String title);

    List<Post> findAllByAuthor(User user);

}