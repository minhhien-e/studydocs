package com.application.bus;

import com.domain.command.UserCommand;

/**
 * UserCommandBus đồng bộ.
 * Gửi command đến handler tương ứng và trả về kết quả trực tiếp.
 * Nếu có lỗi, handler sẽ ném ExceptionMessage.
 */
public interface UserCommandBus {

    /**
     * Gửi command đến handler tương ứng.
     *
     * @param command Command cần xử lý.
     * @param <C> Kiểu command.
     * @param <R> Kiểu kết quả trả về (UserEntity, Boolean, v.v).
     * @return Kết quả xử lý command.
     * @throws com.error.exception.DomainException nếu có lỗi.
     */
    <C extends UserCommand, R> R send(C command);
}
