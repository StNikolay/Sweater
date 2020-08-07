package com.stnikolay.sweater.controller;

import com.stnikolay.sweater.model.User;
import com.stnikolay.sweater.service.ChatService;
import com.stnikolay.sweater.service.UserService;
import com.stnikolay.sweater.util.UserUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @GetMapping("/chat/registration")
    public String registration() throws NotFoundException {
        chatService.registrationUserInChat(UserUtil.getCurrentUser());
        return "redirect:/chat";
    }

    @GetMapping("/chat")
    public String chat(Model model) {
        model.addAttribute("user", (User)userService.loadUserByUsername(
                UserUtil.getCurrentUser().getUsername()));
        return "chat";
    }
}
