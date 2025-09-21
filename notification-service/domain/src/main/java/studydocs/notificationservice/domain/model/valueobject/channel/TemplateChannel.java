package studydocs.notificationservice.domain.model.valueobject.channel;


import studydocs.notificationservice.domain.enums.NotificationChannel;
import studydocs.notificationservice.domain.exceptions.vo.channel.InvalidChannelValueException;

public class TemplateChannel extends Channel {
    public TemplateChannel(String value) {
        if (!NotificationChannel.contains(value))
            throw new InvalidChannelValueException(value, NotificationChannel.getValues());
        super.value = value;
    }
}
