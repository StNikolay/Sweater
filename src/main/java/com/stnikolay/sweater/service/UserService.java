package com.stnikolay.sweater.service;

import com.stnikolay.sweater.model.Role;
import com.stnikolay.sweater.model.User;
import com.stnikolay.sweater.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List allUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        User existUser = userRepository.findByUsername(user.getUsername());

        if (existUser != null) {
            return false;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setChatEnabled(false);
        userRepository.save(user);
        return true;
    }

    public void removeUser(User user) {
        userRepository.delete(user);
    }

    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public void setChatEnabledForUser(String username) {
        userRepository.setChatEnabled(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

}
