package com.example.seo.blog.config;

import com.example.seo.blog.mybatis.BoardMapper;
import com.example.seo.blog.mybatis.UserMapper;
import com.example.seo.blog.repository.BoardRepository;
import com.example.seo.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final UserMapper userMapper;
    private final BoardMapper boardMapper;

    @Bean
    public UserRepository userRepository() {
      return new UserRepository(userMapper);
    }

    @Bean
    public BoardRepository boardRepository(){
        return new BoardRepository(boardMapper);
    }
}
