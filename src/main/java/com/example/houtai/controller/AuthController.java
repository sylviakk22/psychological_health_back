package com.example.houtai.controller;


import com.example.houtai.common.CommonResult;
import com.example.houtai.domain.User;
import com.example.houtai.mapper.UserMapper;
import com.example.houtai.req.ChangePasswordRequest;
import com.example.houtai.req.EditRequest;
import com.example.houtai.req.LoginRequest;
import com.example.houtai.req.RegisterRequest;
import com.example.houtai.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin

@RequestMapping("/admin-api/auth/")
public class AuthController {

    @Autowired()
    private UserMapper userMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public CommonResult<?> register(@RequestBody RegisterRequest reg_user) {

        User foundUser = userMapper.findByUsername(reg_user.getUsername());

        if (foundUser != null) {
            return CommonResult.error(50003, "用户已存在");
        }

        User new_user = new User(reg_user.getUsername(), reg_user.getPassword()
                , reg_user.getName(), reg_user.getGender(), reg_user.getPhone()
                , reg_user.getEmail());
        try {
            // 将用户信息保存到数据库
            userMapper.insert(new_user);
        } catch (Exception e) {
            // 处理插入失败的情况
            System.out.println(e.toString());
            return CommonResult.error(50003, "User registration failed");
        }
        return CommonResult.success("User registered successfully");
    }
    @PostMapping("/login")
    public CommonResult<?> login(@RequestBody LoginRequest loginUser) {

        User user = userMapper.findByUsername(loginUser.getUsername());

        if (user == null) {
            return CommonResult.error(50007,"登录失败，账号密码不正确");
        }

        if (!loginUser.getPassword().equals(user.getPassword())) {
            return CommonResult.error(50007,"登录失败，账号密码不正确");
        }

        String username = loginUser.getUsername();

        // 生成访问令牌和刷新令牌
        String accessToken = jwtTokenUtil.generateAccessToken(username);
        String refreshToken = jwtTokenUtil.generateRefreshToken(username);
        com.example.houtai.resp.TokenResponse token_resp = new com.example.houtai.resp.TokenResponse(accessToken,refreshToken);

        CommonResult<com.example.houtai.resp.TokenResponse> result = CommonResult.success(token_resp);

        return result;
    }

    @PostMapping("/edit")
    public CommonResult<?> edit(@RequestBody EditRequest edit_user) {

        User e_User = userMapper.findByUsername(edit_user.getUsername());

        if (e_User == null) {
            return CommonResult.error(50003, "用户不存在");
        }

        try {
            // 更新用户信息
            e_User.setPassword(edit_user.getPassword());
            e_User.setName(edit_user.getName());
            e_User.setGender(edit_user.getGender());
            e_User.setPhone(edit_user.getPhone());
            e_User.setEmail(edit_user.getEmail());
            // 将用户信息保存到数据库
            userMapper.edit(e_User);
        } catch (Exception e) {
            // 处理插入失败的情况
            System.out.println(e.toString());
            return CommonResult.error(50004, "User refresh failed");
        }
        return CommonResult.success("User refreshed successfully");
    }

    @PostMapping("/editPwd")
    public CommonResult<?> changePassword(@RequestBody ChangePasswordRequest request) {
        try {
            userMapper.changePassword(request.getUsername(), request.getOldPassword(), request.getNewPassword());
            System.out.println(request.getUsername());
            return CommonResult.success("Password changed successfully");
        }
    catch(Exception e)
    {
        // 处理插入失败的情况
        System.out.println(e.toString());
        return CommonResult.error(400, "Password change failed");
    }

}

}
