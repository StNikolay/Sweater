package com.stnikolay.sweater.service;

import com.stnikolay.sweater.model.Dialogue;
import com.stnikolay.sweater.model.Message;
import com.stnikolay.sweater.model.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ChatService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    UserService userService;

    @Transactional
    public void registrationUserInChat(User user) throws NotFoundException {

         if (restTemplate.postForObject(
                 "http://localhost:8081/users",
                 user,
                 User.class
         ) == null)
             throw new NotFoundException("User cannot be registered in chat");

         userService.setChatEnabledForUser(user.getUsername());
    }

    public Message sendMessage(Message message) throws NotFoundException {

        Message messageInfo = restTemplate.postForObject(
                "http://localhost:8081/messages",
                message,
                Message.class
        );

        if (messageInfo == null)
            throw new NotFoundException("Message cannot be sent");

        return messageInfo;
    }

    public boolean checkForExistence(String username) throws NotFoundException {

        try {
            User user = userService.getUserByUsername(username);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            ResponseEntity<Boolean> response = restTemplate.exchange(
                    "http://localhost:8081/users/check?username={username}&password={password}",
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    Boolean.class,
                    user.getUsername(),
                    user.getPassword()
            );

            if (response.getStatusCode() == HttpStatus.OK)
                return response.getBody();
            else
                throw new NotFoundException("Request Failed with status code: " + response.getStatusCode());

        } catch (UsernameNotFoundException e) {
            return false;
        }
    }

    public List<Message> getDialogue(String user, String interlocutor, String date) throws NotFoundException {

        ResponseEntity<Dialogue> response = restTemplate.exchange(
                "http://localhost:8081/messages?user={user}&interlocutor={interlocutor}&date={date}",
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                Dialogue.class,
                user,
                interlocutor,
                date
        );

        if (response.getStatusCode() == HttpStatus.OK)
            return response.getBody().getMessages();
        else
            throw new NotFoundException("Request Failed with status code: " + response.getStatusCode());
    }
}
