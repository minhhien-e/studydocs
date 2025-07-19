package studydocs.notificationservice.adapter.input.rest.request.template.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import studydocs.notificationservice.application.port.input.dto.inputmodel.template.create.AddTemplateInputModel;

public record AddTemplateRequest(
        @NotBlank(message = "Thông tin tên mẫu thông báo không được bỏ trống")
        @Size(max = 100, message = "Tên mẫu thông báo không được vượt quá 100 ký tự")
        String name,
        @NotBlank(message = "Kênh gửi thông báo không được bỏ trống")
        @Pattern(regexp = "EMAIL|SMS|PUSH", message = "Kênh phải là EMAIL, SMS hoặc PUSH")
        String channel,
        @NotBlank(message = "Tiêu đề thông báo không được bỏ trống")
        @Size(max = 150, message = "Tiêu đề không được vượt quá 150 ký tự")
        String subjectTemplate,
        @NotBlank(message = "Nội dung thông báo không được bỏ trống")
        String bodyTemplate,
        @Size(max = 255, message = "Mô tả không được vượt quá 255 ký tự")
        String description) {
    public AddTemplateInputModel toInputModel() {
        return new AddTemplateInputModel(
                name,
                channel,
                subjectTemplate,
                bodyTemplate,
                description
        );
    }
}
