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

    /**
     * 当前API的最近更新时间
     */
    private Date updateTime;
    /**
     * 当前数据的响应式页面，便于嵌入网站或应用
     */
    private String fxLink;
    /**
     * 实时天气信息
     */
    private NowWeather now;
    /**
     * 天级预报
     */
    private List<DayWeather> daily;
    /**
     * 小时级预报
     */
    private List<HourWeather> hourly;

    @Getter
    @Setter
    @ToString
    public static class NowWeather {
        /**
         * 数据观测时间
         */
        private Date obsTime;
        /**
         * 温度，默认单位：摄氏度
         */
        private String temp;
        /**
         * 体感温度，默认单位：摄氏度
         */
        private String feelsLike;
        /**
         * 天气状况和图标的代码，图标可通过天气状况和图标下载
         */
        private String icon;
        /**
         * 天气状况的文字描述，包括阴晴雨雪等天气状态的描述
         */
        private String text;
        /**
         * 风向360角度
         */
        private String wind360;
        /**
         * 风向
         */
        private String windDir;
        /**
         * 风力等级
         */
        private String windScale;
        /**
         * 风速，公里/小时
         */
        private String windSpeed;
        /**
         * 相对湿度，百分比数值
         */
        private String humidity;
        /**
         * 当前小时累计降水量，默认单位：毫米
         */
        private String precip;
        /**
         * 大气压强，默认单位：百帕
         */
        private String pressure;
        /**
         * 能见度，默认单位：公里
         */
        private String vis;
        /**
         * 云量，百分比数值
         */
        private String cloud;
        /**
         * 露点温度
         */
        private String dew;

    }


    @Getter
    @Setter
    @ToString
    public static class DayWeather {
        /**
         * 预报日期
         */
        private String fxDate;
        /**
         * 日出时间
         */
        private String sunrise;
        /**
         * 日落时间
         */
        private String sunset;
        /**
         * 月升时间
         */
        private String moonrise;
        /**
         * 月落时间
         */
        private String moonset;
        /**
         * 月相名称
         */
        private String moonPhase;
        /**
         * 预报当天最高温度
         */
        private String tempMax;
        /**
         * 预报当天最低温度
         */
        private String tempMin;
        /**
         * 预报白天天气状况的图标代码，图标可通过天气状况和图标下载
         */
        private String iconDay;
        /**
         * 预报白天天气状况文字描述，包括阴晴雨雪等天气状态的描述
         */
        private String textDay;
        /**
         * 预报夜间天气状况的图标代码，图标可通过天气状况和图标下载
         */
        private String iconNight;
        /**
         * 预报晚间天气状况文字描述，包括阴晴雨雪等天气状态的描述
         */
        private String textNight;
        /**
         * 预报白天风向360角度
         */
        private String wind360Day;
        /**
         * 预报白天风向
         */
        private String windDirDay;
        /**
         * 预报白天风力等级
         */
        private String windScaleDay;
        /**
         * 预报白天风速，公里/小时
         */
        private String windSpeedDay;
        /**
         * 预报夜间风向360角度
         */
        private String wind360Night;
        /**
         * 预报夜间当天风向
         */
        private String windDirNight;
        /**
         * 预报夜间风力等级
         */
        private String windScaleNight;
        /**
         * 预报夜间风速，公里/小时
         */
        private String windSpeedNight;
        /**
         * 预报当天总降水量，默认单位：毫米
         */
        private String humidity;
        /**
         * 紫外线强度指数
         */
        private String precip;
        /**
         * 相对湿度，百分比数值
         */
        private String pressure;
        /**
         * 大气压强，默认单位：百帕
         */
        private String vis;
        /**
         * 能见度，默认单位：公里
         */
        private String cloud;
        /**
         * 云量，百分比数值
         */
        private String uvIndex;

    }

    @Getter
    @Setter
    @ToString
    public static class HourWeather {
        /**
         * 预报时间
         */
        private String fxTime;
        /**
         * 温度，默认单位：摄氏度
         */
        private String temp;
        /**
         * 天气状况和图标的代码，图标可通过天气状况和图标下载
         */
        private String icon;
        /**
         * 天气状况的文字描述，包括阴晴雨雪等天气状态的描述
         */
        private String text;
        /**
         * 风向360角度
         */
        private String wind360;
        /**
         * 风向
         */
        private String windDir;
        /**
         * 风力等级
         */
        private String windScale;
        /**
         * 风速，公里/小时
         */
        private String windSpeed;
        /**
         * 相对湿度，百分比数值
         */
        private String humidity;
        /**
         * 逐小时预报降水概率，百分比数值，可能为空
         */
        private String pop;
        /**
         * 当前小时累计降水量，默认单位：毫米
         */
        private String precip;
        /**
         * 大气压强，默认单位：百帕
         */
        private String pressure;
        /**
         * 云量，百分比数值
         */
        private String cloud;
        /**
         * 露点温度
         */
        private String dew;
    }
}