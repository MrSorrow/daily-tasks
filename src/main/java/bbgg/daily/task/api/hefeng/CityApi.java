package bbgg.daily.task.api.hefeng;

import bbgg.daily.task.api.hefeng.model.CityResult;
import bbgg.daily.task.api.hefeng.model.TopCityResult;
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

    /**
     * 城市信息查询
     * @param location 需要查询地区的名称，支持文字、以英文逗号分隔的经度,纬度坐标（十进制）、LocationID或Adcode（仅限中国城市）。
     *                 例如 location=北京 或 location=116.41,39.92
     * @return
     */
    @GET("city/lookup?key=" + Constants.HEFENG_WEATHER_KEY)
    CityResult queryCityInfo(@Query("location") String location);

    /**
     * 热门城市查询
     * @param range 搜索范围，可设定只在某个国家范围内进行搜索，国家名称需使用ISO 3166 所定义的国家代码。
     *               world 全球城市范围，默认
     *               cn 中国城市范围，可替换为其他国家的ISO 3166 国家代码，例如range=us
     *               us 美国
     *               fr 法国
     *               uk 英国
     *               ru 俄罗斯
     * @return
     */
    @GET("city/top?key=" + Constants.HEFENG_WEATHER_KEY)
    TopCityResult queryTopCityInfo(@Query("range") String range);
}
