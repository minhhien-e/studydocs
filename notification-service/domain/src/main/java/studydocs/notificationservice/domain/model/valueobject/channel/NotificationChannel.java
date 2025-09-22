package studydocs.notificationservice.domain.model.valueobject.channel;


import studydocs.notificationservice.domain.enums.NotificationChannelEnum;
import studydocs.notificationservice.domain.exceptions.vo.channel.InvalidChannelValueException;

public class NotificationChannel extends Channel {
    public NotificationChannel(String value) {
        if (!NotificationChannelEnum.contains(value))
            throw new InvalidChannelValueException(value, NotificationChannelEnum.getValues());
        super.value = value;
    }
}
