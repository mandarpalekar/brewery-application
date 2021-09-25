package com.mandar.breweryapplication.mappers;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.springframework.stereotype.Component;

@Component
public class DateMapper {

    public OffsetDateTime asOffSetDateTime(Timestamp timestamp) {
        if (null != timestamp) {
            return OffsetDateTime.of(timestamp.toLocalDateTime().getYear(), timestamp.toLocalDateTime().getMonthValue(),
                    timestamp.toLocalDateTime().getDayOfMonth(), timestamp.toLocalDateTime().getHour(),
                    timestamp.toLocalDateTime().getMinute(), timestamp.toLocalDateTime().getSecond(),
                    timestamp.toLocalDateTime().getNano(), ZoneOffset.UTC
            );
        } else {
            return null;
        }
    }

    public Timestamp asTimeStamp(OffsetDateTime offsetDateTime) {
        if (null != offsetDateTime) {
            return Timestamp.valueOf(offsetDateTime.atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
        } else {
            return null;
        }
    }
}
