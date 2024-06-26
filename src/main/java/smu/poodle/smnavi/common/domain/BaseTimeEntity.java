package smu.poodle.smnavi.common.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseTimeEntity {

    ZonedDateTime createdAt;
    ZonedDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        ZonedDateTime now = getCurrentSeoulTime();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = getCurrentSeoulTime();
    }


    private ZonedDateTime getCurrentSeoulTime() {
        return ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    }

    public String getCreatedDateTimeToString() {
        return createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    public String getFormattedCreatedAt() {
        ZonedDateTime now = getCurrentSeoulTime();
        Duration duration = Duration.between(createdAt, now);

        if (duration.toMinutes() < 60) {
            long minutes = duration.toMinutes();
            return minutes + "분 전";
        } else if (duration.toHours() < 24) {
            long hours = duration.toHours();
            return hours + "시간 전";
        } else if (duration.toDays() < 5) {
            long days = duration.toDays();
            return days + "일 전";
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
            return createdAt.format(formatter);
        }
    }
}