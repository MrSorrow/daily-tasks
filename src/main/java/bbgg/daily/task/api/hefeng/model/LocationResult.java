package bbgg.daily.task.api.hefeng.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class LocationResult extends Result {

    private List<Location> location;

    @Getter
    @Setter
    @ToString
    public static class Location {
        private String name;
        private String id;
        private String lat;
        private String lon;
        private String adm2;
        private String adm1;
        private String country;
        private String tz;
        private String utcOffset;
        private String isDst;
        private String type;
        private String rank;
        private String fxLink;
    }
}