package com.example.seo.blog.service;

import com.example.seo.blog.dto.UserDto;
import com.example.seo.blog.model.User;
import com.example.seo.blog.vo.UserVo;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void join(UserDto userDto);
    Optional<User> findById(int id);
    List<UserVo> findAll();
    int idCheck(String username);
    int emailCheck(String email);
    int getTotalCount();
    List<User> getUserList(int currentPage, int size);
    User findUser(String username, String password);
    void update(User user);
    void withDraw(int id);
    String findSalt(String username);
}
