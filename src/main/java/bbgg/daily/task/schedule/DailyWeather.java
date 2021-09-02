package bbgg.daily.task.schedule;

import bbgg.daily.task.api.hefeng.WeatherApi;
import bbgg.daily.task.api.hefeng.model.WeatherResult;
import bbgg.daily.task.constants.Constants;
import bbgg.daily.task.push.WxPush;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 每日天气
 * @author: guoping
 * @date: 2021/6/18
 */
@Slf4j
@Component
public class DailyWeather {

    private static final Map<String, String> CITIES_MAP;

    static {
        CITIES_MAP = new HashMap<>(2);
        CITIES_MAP.put("徐汇区", Constants.XU_HUI_LOCATION_ID);
        CITIES_MAP.put("长宁区", Constants.CHANG_NING_LOCATION_ID);
    }

    @Autowired
    private WxPush wxPush;
    @Autowired
    private WeatherApi weatherApi;

    @Scheduled(cron = "0 20 7 * * ? ")
    public void toMissZhou() {

        CITIES_MAP.forEach((city, location) -> {
            WeatherResult weatherResult = weatherApi.queryWeatherNow(location);
            WeatherResult.NowWeather now = weatherResult.getNow();

            int days = 3;
            WeatherResult nextDaysWeather = weatherApi.queryNextDaysWeather(days, location);

            StringBuilder sb = new StringBuilder();
            sb.append(city).append("天气预报：\r\n")
                    .append("· 天气：").append(now.getText()).append("\r\n")
                    .append("· 温度：").append(now.getTemp()).append("℃").append("\r\n")
                    .append("· 体感温度：").append(now.getFeelsLike()).append("℃").append("\r\n")
                    .append("· 详细数据：").append("<a href=\"").append(weatherResult.getFxLink()).append("\">点击查看</a>").append("\r\n")
                    .append("· 数据观测于：").append(DateUtil.format(now.getObsTime(), "MM-dd HH:mm")).append("\r\n");

            sb.append("\r\n").append("未来").append(days).append("天的天气状况：");

            if (nextDaysWeather != null) {
                List<WeatherResult.DayWeather> dailyWeather = nextDaysWeather.getDaily();
                for (WeatherResult.DayWeather dayWeather : dailyWeather) {
                    sb.append("\r\n")
                            .append("· ")
                            .append(dayWeather.getFxDate())
                            .append(": ")
                            .append(dayWeather.getTextDay());
                }
            }

            wxPush.pushTextMsg(sb.toString(), Constants.MMGG_PARTY);
        });
    }
}
