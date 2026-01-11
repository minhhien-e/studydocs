package studydocs.media.infrastructure.adapter.bus.handler;

import org.springframework.stereotype.Component;
import studydocs.media.application.dto.command.DeleteAssetByIdCommand;
import studydocs.media.application.port.in.usecase.DeleteAssetByIdUseCasePort;
import studydocs.media.infrastructure.adapter.bus.handler.base.AbstractHandler;

@Component
public class DeleteAssetByIdHandler extends AbstractHandler<DeleteAssetByIdCommand, Void> {
    protected DeleteAssetByIdHandler(DeleteAssetByIdUseCasePort useCase) {
        super(useCase, DeleteAssetByIdCommand.class);
    }
}
