package com.example.seo.blog.repository;

import com.example.seo.blog.dto.UserDto;
import com.example.seo.blog.model.User;
import com.example.seo.blog.mybatis.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class UserRepository implements UserMapper {

    private final UserMapper userMapper;

    @Override
    public Optional<User> findById(int id) {
        return userMapper.findById(id);
    }
    @Override
    public void join(UserDto userDto) {
        userMapper.join(userDto);
    }
    @Override
    public List<User> findAll() {
        return  userMapper.findAll();
    }
    @Override
    public int idCheck(String username) {
        return userMapper.idCheck(username);
    }
    @Override
    public int emailCheck(String email) {
        return userMapper.emailCheck(email);
    }

    @Override
    public int loginCheck(User user) {
        return userMapper.loginCheck(user);
    }
    @Override
    public int getTotalCount() {
        return userMapper.getTotalCount();
    }
    @Override
    public List<User> getUserList(Map<String, Object> params) {
        return userMapper.getUserList(params);
    }
    @Override
    public User findUser(String username, String password) {
        return userMapper.findUser(username, password);
    }
    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void withDraw(int id) {
        userMapper.withDraw(id);
    }

    @Override
    public String findSalt(String username) {
        return userMapper.findSalt(username);
    }
}
