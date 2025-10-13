package com;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableRabbit
@SpringBootApplication
public class UserService {
    public static void main(String[] args) {
        SpringApplication.run(UserService.class, args);
    }

//    Lưu thông tin cá nhân (tên, giới tính, ngày sinh, avatar…)
//
//    Cập nhật profile
//
//
//    Quản lý cài đặt người dùng (notification, ngôn ngữ, timezone…)
//
//    Quan hệ xã hội (follow, bạn bè, block…)
}