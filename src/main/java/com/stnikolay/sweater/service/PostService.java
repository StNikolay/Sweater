package com.stnikolay.sweater.service;

import com.stnikolay.sweater.model.Post;
import com.stnikolay.sweater.model.User;
import com.stnikolay.sweater.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public List<Post> userPosts(User user) {
        return postRepository.findAllByAuthor(user);
    }

    public void addPost(Post post) {
        postRepository.save(post);
    }

    public void removePostById(Long id) {
        postRepository.deleteById(id);
    }

    @Transactional
    public void removePostsByAuthor(User user) {
        postRepository.deleteAllByAuthor(user);
    }

}