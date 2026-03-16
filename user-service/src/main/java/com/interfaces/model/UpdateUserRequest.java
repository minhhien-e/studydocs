package com.interfaces.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotBlank(message = "ID người dùng không được để trống")
    private UUID id;

    @NotBlank(message = "Họ và tên không được để trống")
    @Size(max = 100, message = "Họ và tên không được dài quá 100 ký tự")
    private String fullName;

    @NotBlank(message = "Username không được để trống")
    @Size(max = 50, message = "Username không được dài quá 50 ký tự")
    private String username;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Size(max = 20, message = "Số điện thoại không quá 20 ký tự")
    private String phoneNumber;

    private String avatarUrl;

    @NotBlank(message = "Giới tính không được để trống")
    private String gender;

    @NotNull(message = "Ngày sinh không được để trống")
    @Past(message = "Ngày sinh phải là ngày trong quá khứ")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(max = 255, message = "Địa chỉ không quá 255 ký tự")
    private String address;

    private MultipartFile file; // dùng nếu cập nhật ảnh cùng request

}