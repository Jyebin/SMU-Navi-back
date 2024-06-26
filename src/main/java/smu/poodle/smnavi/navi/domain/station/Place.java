package smu.poodle.smnavi.navi.domain.station;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import smu.poodle.smnavi.navi.dto.AbstractWaypointDto;
import smu.poodle.smnavi.navi.dto.WaypointDto;

@Entity
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Place extends Waypoint {
    @Column(unique = true)
    String placeName;

    @Override
    public String getPointName() {
        return placeName;
    }

    @Override
    public AbstractWaypointDto toDto() {
        return WaypointDto.PlaceDto.builder()
                .id(super.getId())
                .gpsX(super.getX())
                .gpsY(super.getY())
                .placeName(placeName)
                .build();
    }
}
