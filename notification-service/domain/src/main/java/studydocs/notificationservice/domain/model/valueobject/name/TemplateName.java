package studydocs.notificationservice.domain.model.valueobject.name;

import studydocs.notificationservice.domain.exceptions.vo.name.NameTooLongException;

public class TemplateName extends Name {
    public TemplateName(String value) {
        if (value.length() > 50)
            throw new NameTooLongException("tên mẫu thông báo", 50);
        this.value = value;
    }
}
