package bbgg.daily.task.api.hefeng;

import bbgg.daily.task.api.hefeng.model.WeatherResult;
import bbgg.daily.task.constants.Constants;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @description:
 * @author: guoping
 * @date: 2021/7/7
 */
@RetrofitClient(baseUrl = "https://devapi.qweather.com/v7/")
public interface WeatherApi {

    /**
     * 实时天气
     * @param location 需要查询地区的LocationID或以英文逗号分隔的经度,纬度坐标（十进制），LocationID可通过城市搜索服务获取。
     *                 例如 location=101010100 或 location=116.41,39.92
     * @return
     */
    @GET("weather/now?key=" + Constants.HEFENG_WEATHER_KEY)
    WeatherResult queryWeatherNow(@Query("location") String location);

    /**
     * 逐天天气预报
     * @param location 需要查询地区的LocationID或以英文逗号分隔的经度,纬度坐标（十进制），LocationID可通过城市搜索服务获取。
     *                 例如 location=101010100 或 location=116.41,39.92
     * @return
     */
    @GET("weather/{days}d?key=" + Constants.HEFENG_WEATHER_KEY)
    WeatherResult queryNextDaysWeather(@Path ("days") Integer days, @Query("location") String location);

    /**
     * 逐小时天气预报
     * @param location 需要查询地区的LocationID或以英文逗号分隔的经度,纬度坐标（十进制），LocationID可通过城市搜索服务获取。
     *                 例如 location=101010100 或 location=116.41,39.92
     * @return
     */
    @GET("weather/{hours}h?key=" + Constants.HEFENG_WEATHER_KEY)
    WeatherResult queryNextHoursWeather(@Path ("hours") Integer hours, @Query("location") String location);

}
