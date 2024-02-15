package com.example.seo.blog.service;

import com.example.seo.blog.dto.UserDto;
import com.example.seo.blog.model.User;
import com.example.seo.blog.mybatis.UserMapper;
import com.example.seo.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public void join(UserDto userDto) {
        userRepository.join(userDto);
    }
    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    public int idCheck(String username) {
        return userRepository.idCheck(username);
    }
    public int loginCheck(User user) {
        return userRepository.loginCheck(user);
    }
    public int getTotalCount(){
        return userRepository.getTotalCount();
    }
    public List<User> getUserList(int currentPage, int size) {
        int startRow = (currentPage - 1) * size;
        Map<String, Object> params = new HashMap<>();
        params.put("startRow", startRow);
        params.put("size", size);
        return userRepository.getUserList(params);
    }
    @Override
    public User findUser(String username, String password) {
        return userRepository.findUser(username, password);
    }
    @Override
    public void update(User user) {
        userRepository.update(user);
    }
    @Override
    public void withDraw(int id) {
        userRepository.withDraw(id);
    }
    @Override
    public String findSalt(String username) {
        return userRepository.findSalt(username);
    }
}
