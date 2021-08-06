package bbgg.daily.task.api.hefeng.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class WeatherResult extends Result {

    private Date updateTime;
    private String fxLink;
    private Weather now;

    @Getter
    @Setter
    @ToString
    public static class Weather {
        private Date obsTime;
        private String temp;
        private String feelsLike;
        private String icon;
        private String text;
        private String wind360;
        private String windDir;
        private String windScale;
        private String windSpeed;
        private String humidity;
        private String precip;
        private String pressure;
        private String vis;
        private String cloud;
        private String dew;

    }
}