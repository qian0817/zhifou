package com.qianlei.zhifou.controller;

import com.qianlei.zhifou.pojo.User;
import com.qianlei.zhifou.service.IUserService;
import com.qianlei.zhifou.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/** @author qianlei */
@RestController
@RequestMapping("/api/token")
public class TokenController {
  @Autowired private IUserService userService;

  @PostMapping("/")
  public UserVo login(@RequestBody User user, HttpSession session) {
    user = userService.login(user);
    session.setAttribute("user", user);
    return new UserVo(user);
  }

  @DeleteMapping("/")
  public void login(HttpSession session) {
    session.invalidate();
  }

  @GetMapping("/")
  public UserVo getUserInfo(@SessionAttribute("user") User user) {
    return new UserVo(user);
  }
}
