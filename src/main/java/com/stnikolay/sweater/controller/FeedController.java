package com.stnikolay.sweater.controller;

import com.stnikolay.sweater.model.Post;
import com.stnikolay.sweater.service.PostService;
import com.stnikolay.sweater.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class FeedController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping("/feed")
    public String newPost(@ModelAttribute @Valid Post post,
                          BindingResult result) {

        if (!result.hasErrors()){
            post.setAuthor(UserUtil.getCurrentUser());
            postService.addPost(post);
        }

        return "redirect:/feed";
    }

    @GetMapping("/feed")
    public String feed(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("userPosts", postService.userPosts(UserUtil.getCurrentUser()));
        return "feed";
    }

    @PostMapping("/feed/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.removePostById(id);
        return "redirect:/feed";
    }

}
