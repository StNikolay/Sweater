package com.stnikolay.sweater.controller;

import com.stnikolay.sweater.model.User;
import com.stnikolay.sweater.service.PostService;
import com.stnikolay.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

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

    @GetMapping("/user/{id}")
    public String userProfile(@PathVariable Long id,
                              Model model) {
        User user = userService.getUserById(id);

        model.addAttribute("user", user);
        model.addAttribute("userPosts", postService.userPosts(user));
        return "/userProfile";
    }

}
