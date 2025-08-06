package studydocs.notificationservice.infrastructure.mongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Configuration
public class MongoConvertersConfig {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        Converter<Date, LocalDateTime> dateToLocalDateTime = new Converter<Date, LocalDateTime>() {
            @Override
            public LocalDateTime convert(Date source) {
                return source.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            }
        };

        Converter<LocalDateTime, Date> localDateTimeToDate = new Converter<LocalDateTime, Date>() {
            @Override
            public Date convert(LocalDateTime source) {
                return Date.from(source.atZone(ZoneId.systemDefault()).toInstant());
            }
        };

        return new MongoCustomConversions(List.of(dateToLocalDateTime, localDateTimeToDate));
    }
}
