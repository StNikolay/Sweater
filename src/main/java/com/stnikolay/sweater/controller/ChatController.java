package com.stnikolay.sweater.controller;

import com.stnikolay.sweater.model.Message;
import com.stnikolay.sweater.model.User;
import com.stnikolay.sweater.service.ChatService;
import com.stnikolay.sweater.service.UserService;
import com.stnikolay.sweater.util.UserUtil;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public String registration() throws NotFoundException {
        chatService.registrationUserInChat(UserUtil.getCurrentUser());
        return "redirect:/chat";
    }

    @GetMapping
    public String chat(Model model) {
        model.addAttribute("user", (User) userService.loadUserByUsername(
                UserUtil.getCurrentUser().getUsername()));
        return "chat";
    }

    @GetMapping("/user")
    public RedirectView redirectInDialogue(@RequestParam("username") String username) {
        return new RedirectView(username);
    }

    @GetMapping("/{username}")
    public String chatWithUser(@PathVariable String username, Model model) throws NotFoundException {
        if (!chatService.checkForExistence(username))
            return "redirect:/chat";
        model.addAttribute("username", username);
        return "chat_page";
    }

    @PostMapping("/{username}/send")
    public RedirectView sendMessage(@PathVariable("username") String receiver,
                                    @RequestParam String text) throws NotFoundException {

        chatService.sendMessage(new Message(
                UserUtil.getCurrentUser(),
                userService.getUserByUsername(receiver),
                text,
                UserUtil.getTime(),
                UserUtil.getDate()
        ));

        return new RedirectView("");
    }
}
