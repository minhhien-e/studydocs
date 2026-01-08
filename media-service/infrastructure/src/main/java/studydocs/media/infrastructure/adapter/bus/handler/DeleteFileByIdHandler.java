package studydocs.media.infrastructure.adapter.bus.handler;

import org.springframework.stereotype.Component;
import studydocs.media.application.dto.command.DeleteFileByIdCommand;
import studydocs.media.application.port.in.usecase.DeleteFileByIdUseCasePort;
import studydocs.media.infrastructure.adapter.bus.handler.base.AbstractHandler;
@Component
public class DeleteFileByIdHandler extends AbstractHandler<DeleteFileByIdCommand, Void> {
    protected DeleteFileByIdHandler(DeleteFileByIdUseCasePort useCase) {
        super(useCase, DeleteFileByIdCommand.class);
    }
}
