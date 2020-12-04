package com.qianlei.zhifou.controller;

import com.nimbusds.jose.jwk.RSAKey;
import com.qianlei.zhifou.requestparam.RegisterParam;
import com.qianlei.zhifou.requestparam.SendMailParam;
import com.qianlei.zhifou.service.IUserService;
import com.qianlei.zhifou.utils.JwtUtils;
import com.qianlei.zhifou.vo.UserInfo;
import com.qianlei.zhifou.vo.UserVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** @author qianlei */
@RestController
@RequestMapping("/api/user")
public class UserController {
  @Autowired private IUserService userService;
  @Autowired private RSAKey rsaKey;

  @Operation(summary = "用户注册")
  @PostMapping("/")
  public String register(@Parameter(description = "注册需要的信息") @RequestBody RegisterParam param) {
    var user = userService.register(param);
    return JwtUtils.createJwt(new UserVo(user), rsaKey);
  }

  @Operation(summary = "发送注册邮件")
  @PostMapping("/registerCode/")
  public void sendEmail(@Parameter(description = "邮箱信息") @RequestBody SendMailParam param) {
    userService.sendRegisterEmail(param.getEmail());
  }

  @Operation(summary = "获取指定用户的信息")
  @GetMapping("/{id}")
  public UserInfo getUserInfo(
      @Parameter(description = "被获取信息的用户 id") @PathVariable("id") Integer id,
      @RequestAttribute(value = "user", required = false) UserVo user) {
    return userService.getUserInfoByUserId(id, user);
  }

  @Operation(summary = "关注某人")
  @PostMapping("/{follower}/following/")
  public void follow(
      @Parameter(description = "被关注的人的用户 id") @PathVariable("follower") Integer follower,
      @RequestAttribute("user") UserVo following) {
    userService.follow(follower, following.getId());
  }

  @Operation(summary = "取消关注")
  @DeleteMapping("/{follower}/following/")
  public void unfollow(
      @Parameter(description = "被取消关注的人的用户 id") @PathVariable("follower") Integer follower,
      @RequestAttribute("user") UserVo following) {
    userService.unfollow(follower, following.getId());
  }
}
