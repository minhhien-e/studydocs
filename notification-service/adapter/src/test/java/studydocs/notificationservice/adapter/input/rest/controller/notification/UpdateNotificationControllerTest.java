package studydocs.notificationservice.adapter.input.rest.controller.notification;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.SpringDataMongoDB;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import studydocs.notificationservice.adapter.input.rest.request.recipient.update.MarkAllAsReadRequest;
import studydocs.notificationservice.adapter.input.rest.request.recipient.update.MarkAsReadRequest;
import studydocs.notificationservice.application.port.input.usecase.recipient.update.MarkAllAsReadUseCase;
import studydocs.notificationservice.application.port.input.usecase.recipient.update.MarkAsReadUseCase;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateNotificationControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    private MarkAllAsReadUseCase markAllAsReadUseCase;
    @MockitoBean
    private MarkAsReadUseCase markAsReadUseCase;

    @Test
    void markAsRead_WhenDataValid_ReturnsOk() throws Exception {
        UUID notificationId = UUID.randomUUID();
        MarkAsReadRequest request = new MarkAsReadRequest(notificationId);
        request.setRecipientId(UUID.randomUUID());
        mockMvc.perform(patch("/api/v1/notifications/{notificationId}/read", notificationId)
                        .param("userId", request.getRecipientId().toString()))
                .andExpect(status().isOk());
        verify(markAsReadUseCase).execute(request.toInputModel());
    }

    @Test
    void markAllAsRead_WhenDataValid_ReturnsOk() throws Exception {
        MarkAllAsReadRequest request = new MarkAllAsReadRequest();
        request.setRecipientId(UUID.randomUUID());
        mockMvc.perform(patch("/api/v1/notifications/read-all")
                        .param("userId", request.getRecipientId().toString()))
                .andExpect(status().isOk());
        verify(markAllAsReadUseCase).execute(request.toInputModel());
    }
}
