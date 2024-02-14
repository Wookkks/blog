package com.example.seo.blog.controller;

import com.example.seo.blog.common.Paging;
import com.example.seo.blog.common.RSAUtil;
import com.example.seo.blog.model.User;
import com.example.seo.blog.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.*;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping({"", "/"})
    public String index(Model model, @RequestParam(defaultValue = "1") int currentPage, @RequestParam(defaultValue = "5") int size) {
        List<User> users = userService.findAll();
        int totalCount = userService.getTotalCount();
        List<User> userList = userService.getUserList(currentPage, size);
        Paging<User> paging = new Paging<>(totalCount, currentPage, 5, size, users);
        model.addAttribute("userList", userList);
        model.addAttribute("paging", paging);
        return "index";
    }

    @GetMapping("/user/join")
    public String joinForm(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, InvalidKeyException {
        request.getSession().removeAttribute(RSAUtil.PRIVATE_KEY);
        KeyPair keys = RSAUtil.genKey();
        request.getSession().setAttribute(RSAUtil.PRIVATE_KEY, keys.getPrivate());
        Map<String, String> spec = RSAUtil.getKeySpec(keys.getPublic());
        request.setAttribute(RSAUtil.PUBLIC_KEY_MODULUS, spec.get(RSAUtil.PUBLIC_KEY_MODULUS));
        request.setAttribute(RSAUtil.PUBLIC_KEY_EXPONENT, spec.get(RSAUtil.PUBLIC_KEY_EXPONENT));
        request.setAttribute(RSAUtil.PUBLIC_KEY, keys.getPublic());
        return "user/join";
    }

//    @GetMapping("/user/login")
//    public String loginForm() {
//        return "/user/login";
//    }

    @GetMapping("/user/login")
    public String loginForm(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{

        if(session.getAttribute(RSAUtil.PRIVATE_KEY) == null) {
            // 키쌍 생성
            KeyPair keys = RSAUtil.genKey();
            // 개인키 세션에 저장
            request.getSession().setAttribute(RSAUtil.PRIVATE_KEY, keys.getPrivate());
            // 여기서 생성된 공개키를 전달. 전달된 공개키는 문자열로 변환
            Map<String, String> spec = RSAUtil.getKeySpec(keys.getPublic());

            request.setAttribute(RSAUtil.PUBLIC_KEY_MODULUS, spec.get(RSAUtil.PUBLIC_KEY_MODULUS));
            request.setAttribute(RSAUtil.PUBLIC_KEY_EXPONENT, spec.get(RSAUtil.PUBLIC_KEY_EXPONENT));
            request.setAttribute(RSAUtil.PUBLIC_KEY, keys.getPublic());
        }

        return "/user/login";
    }
    @GetMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
    @GetMapping("/user/detail/{id}")
    public String userDetail(@PathVariable int id, Model model) {
        User findUser = userService.findById(id).orElseThrow(()->new IllegalArgumentException("유저를 찾을 수 없습니다."));
        model.addAttribute("user", findUser);
        return "/user/detail";
    }
}
