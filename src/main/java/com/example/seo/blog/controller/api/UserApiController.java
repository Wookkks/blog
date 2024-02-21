package com.example.seo.blog.controller.api;

import com.example.seo.blog.common.Hashing;
import com.example.seo.blog.common.RSAUtil;
import com.example.seo.blog.dto.ResponseDto;
import com.example.seo.blog.dto.UserDto;
import com.example.seo.blog.model.User;
import com.example.seo.blog.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.PrivateKey;

@RestController
@AllArgsConstructor
@Slf4j
public class UserApiController {

    private final UserService userService;

    @PostMapping("/user/api/join")
    @ResponseBody
    public ResponseDto join(@RequestBody User user, HttpSession session) {
        try {
            PrivateKey privateKey = (PrivateKey) session.getAttribute(RSAUtil.PRIVATE_KEY);
            String rawPassword = RSAUtil.decryptRSA(user.getPassword(), privateKey);
            String salt = Hashing.getSalt();
            String encPassword = Hashing.hashPassword(rawPassword, salt);

            UserDto userDto = new UserDto();
            userDto.setName(user.getName());
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setPassword(encPassword);
            userDto.setSalt(salt);

            userService.join(userDto);
            return new ResponseDto("success", "회원가입이 완료되었습니다.");
        }catch (Exception e){
            return new ResponseDto("failure", "회원가입에 실패하였습니다.");
        }
    }
    @PostMapping("/user/api/idCheck")
    @ResponseBody
    public int idCheck(@RequestBody String username) {
        int count = 0;
        count = userService.idCheck(username);
        return count;
    }
    @PostMapping("/user/api/emailCheck")
    @ResponseBody
    public int emailCheck(@RequestBody String email) {
        int count = 0;
        count = userService.emailCheck(email);
        return count;
    }
    @PostMapping("/user/api/login")
    @ResponseBody
    public ResponseDto loginCheck(@RequestBody UserDto userDto, HttpSession session) {
        PrivateKey privateKey = (PrivateKey) session.getAttribute(RSAUtil.PRIVATE_KEY);
        if(privateKey == null) {
            throw new RuntimeException("암호화 비밀키 정보를 찾을 수 없습니다.");
        }
        try {
            String username = userDto.getUsername();
            String salt = userService.findSalt(username);
            String rawPassword = RSAUtil.decryptRSA(userDto.getPassword(), privateKey);
            String encPassword = Hashing.hashPassword(rawPassword, salt);
            User loginUser = userService.findUser(username, encPassword);
            session.setAttribute("userId", loginUser.getId());
            return new ResponseDto("success", loginUser.getName() + "님 환영합니다.");

        } catch (Exception e) {

            log.error(e.getMessage());
            return new ResponseDto("failure", "아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }
    @PutMapping("/user/api/update/{id}")
    @ResponseBody
    public ResponseDto userUpdate(@RequestBody UserDto userDto, @PathVariable int id) {
        try {
            User user = userService.findById(id).orElseThrow(()->new IllegalArgumentException("회원을 찾을 수 없습니다."));
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(user.getPassword());
            userService.update(user);
            return new ResponseDto("success", "수정이 완료되었습니다.");
        } catch (Exception e) {
            return new ResponseDto("failure", "수정에 실패하였습니다.");
        }
    }
    @DeleteMapping("/user/api/withdraw/{id}")
    public ResponseDto userWithdraw(@PathVariable int id) {
        try {
            userService.findById(id).orElseThrow(()->new IllegalArgumentException("회원을 찾을 수 없습니다."));
            userService.withDraw(id);
            return new ResponseDto("success", "탈퇴가 완료되었습니다. 서비스를 이용해 주셔서 감사합니다.");
        } catch (Exception e) {
            return new ResponseDto("failure", "에러가 발생하였습니다. 관리자에게 문의해주세요.");
        }
    }
}
