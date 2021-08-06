package bbgg.daily.task.api.hefeng;

import bbgg.daily.task.api.hefeng.model.WeatherResult;
import bbgg.daily.task.constants.Constants;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @description:
 * @author: guoping
 * @date: 2021/7/7
 */
@RetrofitClient(baseUrl = "https://devapi.qweather.com/v7/")
public interface WeatherApi {

    @GET("grid-weather?key=" + Constants.HEFENG_WEATHER_KEY)
    WeatherResult queryWeatherInfo(@Query("location") String location);
}
