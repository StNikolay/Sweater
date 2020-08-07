package com.stnikolay.sweater.service;

import com.stnikolay.sweater.model.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatService {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    UserService userService;

    @Transactional
    public void registrationUserInChat(User user) throws NotFoundException {

         if(restTemplate.postForObject(
                 "http://localhost:8081/user",
                 user,
                 User.class
         ) == null)
             throw new NotFoundException("User cannot be registered in chat");

         userService.setChatEnabledForUser(user.getUsername());
    }

}
