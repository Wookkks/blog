package com.example.seo.blog.mybatis;

import com.example.seo.blog.dto.UserDto;
import com.example.seo.blog.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Mapper
public interface UserMapper {
    Optional<User> findById(int id);
    void join(UserDto userDto);
    List<User> findAll();
    int idCheck(String username);
    int loginCheck(User user);
    int getTotalCount();
    List<User> getUserList(Map<String, Object> params);
    User findUser(String username, String password);
    void update(User user);
    void withDraw(int id);
    String findSalt(String username);
}



