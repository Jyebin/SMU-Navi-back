package smu.poodle.smnavi.map.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import smu.poodle.smnavi.map.domain.Accident;
import smu.poodle.smnavi.map.domain.BusRealTimeLocationInfo;
import smu.poodle.smnavi.map.domain.station.Waypoint;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BusRealTimeLocationDto {
    String licensePlate;
    int stationOrder;
    String stationId;

    public Accident toAccidentEntity(Waypoint waypoint) {
        return Accident.builder()
                .waypoint(waypoint)
                .message(waypoint.getPointName() + "정류장에서 버스가 심하게 정체중 입니다. 시위나 사고가 예상됩니다.")
                .build();
    }

    public BusRealTimeLocationInfo toInfoEntity(String busName) {
        return BusRealTimeLocationInfo.builder()
                .busName(busName)
                .licensePlate(licensePlate)
                .stationId(stationId)
                .stationOrder(stationOrder)
                .build();
    }
}
