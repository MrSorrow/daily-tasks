package bbgg.daily.task.api.hefeng;

import bbgg.daily.task.api.hefeng.model.LocationResult;
import bbgg.daily.task.constants.Constants;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @description:
 * @author: guoping
 * @date: 2021/7/7
 */
@RetrofitClient(baseUrl = "https://geoapi.qweather.com/v2/")
public interface CityApi {

    @GET("city/lookup?key=" + Constants.HEFENG_WEATHER_KEY)
    LocationResult queryCityInfo(@Query("location") String location);
}
