package studydocs.user.application.bus;

import studydocs.user.application.handler.CommandHandler;
import studydocs.user.domain.command.UserCommand;
import org.springframework.stereotype.Service;
import studydocs.user.error.ErrorCode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static studydocs.user.error.factory.ExceptionFactory.custom;

@Service
public class SimpleUserCommandBus implements UserCommandBus {

    private final Map<Class<?>, CommandHandler<?, ?>> handlers;

    public SimpleUserCommandBus(List<CommandHandler<?, ?>> handlerList) {
        // Map command class → handler
        this.handlers = handlerList.stream()
                .collect(Collectors.toMap(CommandHandler::commandType, h -> h));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <C extends UserCommand, R> R send(C command) {
        CommandHandler<C, R> handler = (CommandHandler<C, R>) handlers.get(command.getClass());
        if (handler == null) {
            // Nếu không có handler → ném DomainException với mã lỗi NO_HANDLER
            throw custom(ErrorCode.NO_HANDLER, "send");
        }
        // Gọi đồng bộ handler, trả về kết quả trực tiếp
        return handler.handle(command); // lỗi trong handler sẽ là DomainException
    }
}
