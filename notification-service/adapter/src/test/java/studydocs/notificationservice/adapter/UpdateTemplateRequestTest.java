package studydocs.notificationservice.adapter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import studydocs.notificationservice.adapter.input.rest.request.template.update.concrete.UpdateTemplateSubjectRequest;

@SpringBootTest
public class UpdateTemplateRequestTest {
    @Test
    public void test() {
        var request = new UpdateTemplateSubjectRequest("hello");
        request.setTemplateName("hello");
        System.out.println(request.toInputModel().toString());
    }
}
