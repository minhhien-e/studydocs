package com.example.authservicev2.domain.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalRegisterRequest {

    @NotBlank(message = "Email không được bỏ trống")
    private String username;
    @NotBlank(message = "Email không được bỏ trống")
    @Email(message = "Email không hợp lệ")
    @Size(max = 255, message = "Email không được quá 255 ký tự")
    private String email;
    @NotBlank(message = "Mật khẩu không được bỏ trống")
    @Size(min = 6, message = "Mật khẩu phải từ 6 ký tự")
    private String password;
}
