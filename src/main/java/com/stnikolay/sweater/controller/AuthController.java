package com.stnikolay.sweater.controller;

import com.stnikolay.sweater.model.User;
import com.stnikolay.sweater.repository.UserRepository;
import com.stnikolay.sweater.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/join")
    public String setSignUp(Model model) {
        model.addAttribute("user", new User());
        return "/sign_up";
    }

    @PostMapping("/join")
    public String signUp(@ModelAttribute @Valid User user,
                        BindingResult result,
                         Model model) {

        if (result.hasErrors()){
            return "/sign_up";
        }

        if (!userService.saveUser(user)){
            model.addAttribute("message", "User is already exist");
            return "/sign_up";
        }

        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login(/*@RequestParam(required = false) Boolean error,
                        Model model*/) {
        return "sign_in";
    }
}
