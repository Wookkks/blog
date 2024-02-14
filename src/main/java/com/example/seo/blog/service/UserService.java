package com.example.seo.blog.service;

import com.example.seo.blog.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void join(User user);
    Optional<User> findById(int id);
    List<User> findAll();
    int idCheck(String username) ;
    int loginCheck(User user);
    int getTotalCount();
    List<User> getUserList(int currentPage, int size);
    User findUser(String username, String password);
    void update(User user);
    void withDraw(int id);
}
