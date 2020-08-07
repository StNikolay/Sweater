package com.stnikolay.sweater.controller;

import com.stnikolay.sweater.model.Post;
import com.stnikolay.sweater.model.User;
import com.stnikolay.sweater.service.PostService;
import com.stnikolay.sweater.service.UserService;
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
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "/user_list";
    }

    @PostMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        postService.removePostsByAuthor(userService.getUserById(id));
        userService.removeUserById(id);
        return "redirect:/users";
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

    @GetMapping("/user/{id}")
    public String userProfile(@PathVariable Long id,
                              Model model) {
        User user = userService.getUserById(id);

        model.addAttribute("user", user);
        model.addAttribute("userPosts", postService.userPosts(user));
        return "/userProfile";
    }
}
