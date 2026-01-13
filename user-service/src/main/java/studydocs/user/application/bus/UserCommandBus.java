package studydocs.user.application.bus;

import studydocs.user.domain.command.UserCommand;
import studydocs.user.error.exception.DomainException;

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
     * @throws DomainException nếu có lỗi.
     */
    <C extends UserCommand, R> R send(C command);
}
