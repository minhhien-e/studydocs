package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserService {
    public static void main(String[] args) {
        SpringApplication.run(UserService.class, args);
    }

//    Lưu thông tin cá nhân (tên, giới tính, ngày sinh, avatar…)
//
//    Cập nhật profile
//
//    Quản lý role/permission của user (authorization cho business logic)
//
//    Quản lý cài đặt người dùng (notification, ngôn ngữ, timezone…)
//
//    Quan hệ xã hội (follow, bạn bè, block…)
}