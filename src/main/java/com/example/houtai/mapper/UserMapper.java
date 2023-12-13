package com.example.houtai.mapper;

import com.example.houtai.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User findByUsername(String username);
    void insert(User user);
    void edit(User user);
    void changePassword(String username, String oldPassword, String newPassword);
}
