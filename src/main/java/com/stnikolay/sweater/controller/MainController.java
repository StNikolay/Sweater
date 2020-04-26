package com.stnikolay.sweater.controller;

import com.stnikolay.sweater.model.Post;
import com.stnikolay.sweater.model.User;
import com.stnikolay.sweater.service.PostService;
import com.stnikolay.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "/user_list";
    }

    @PostMapping("/feed")
    public String newPost(@ModelAttribute Post post,
                          BindingResult result) {

        if (!result.hasErrors()){
            User user = (User) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            post.setAuthor(user);
            postService.addPost(post);
        }

        return "redirect:/feed";
    }

    @GetMapping("/feed")
    public String feed(Model model) {
        model.addAttribute("post", new Post());
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("userPosts", postService.userPosts(user));
        return "feed";
    }
}
