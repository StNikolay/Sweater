package com.stnikolay.sweater.repository;

import com.stnikolay.sweater.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    User findByUsername(String username);

    @Modifying
    @Query("update User set chat_enabled = true where username = :username")
    void setChatEnabled(@Param("username") String username);

}
