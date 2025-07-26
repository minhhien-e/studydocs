package studydocs.notificationservice.domain.valueobject.notification;

import studydocs.notificationservice.shared.enums.NotificationChannel;
import studydocs.notificationservice.shared.exception.concrete.valueobjects.notification.channel.MissingNotificationChanelFieldException;
import studydocs.notificationservice.shared.exception.concrete.valueobjects.notification.channel.NotificationChannelNotFoundException;
import studydocs.notificationservice.shared.utils.StringUtils;

import java.util.Objects;

public class NotificationChannelType {
    private final NotificationChannel channel;

    public NotificationChannelType(String channel, String domainName) {
        if (StringUtils.isNullOrBlank(channel))
            throw new MissingNotificationChanelFieldException(domainName);
        try {
            this.channel = NotificationChannel.valueOf(channel);
        } catch (IllegalArgumentException e) {
            throw new NotificationChannelNotFoundException(channel);
        }
    }

    public String getChannel() {
        return channel.name();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        NotificationChannelType that = (NotificationChannelType) o;
        return channel == that.channel;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(channel);
    }
}
