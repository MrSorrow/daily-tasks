package bbgg.daily.task;

import bbgg.daily.task.api.hefeng.CityApi;
import bbgg.daily.task.api.hefeng.WeatherApi;
import bbgg.daily.task.api.hefeng.model.City;
import bbgg.daily.task.api.hefeng.model.CityResult;
import bbgg.daily.task.api.hefeng.model.TopCityResult;
import bbgg.daily.task.api.hefeng.model.WeatherResult;
import bbgg.daily.task.constants.Constants;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private WeatherApi weatherApi;
    @Autowired
    private CityApi cityApi;

    @Test
    void testSendMessage() throws WxErrorException {
        WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
        config.setCorpId("wwfa47dbc0016a7a49");      // 设置微信企业号的appid
        config.setCorpSecret("CkCTVGQHnOTvNZq6b87vN0CxTPZpcrU_dogYPG2v1xs");  // 设置微信企业号的app corpSecret

        WxCpServiceImpl wxCpService = new WxCpServiceImpl();
        wxCpService.setWxCpConfigStorage(config);

        WxCpMessage message = WxCpMessage.TEXT().agentId(1000002).toParty("1").content("Hello World").build();

//		wxCpService.getMessageService().send(message);
    }

    @Test
    void testWeatherCityInfo() {
        CityResult cityResult = cityApi.queryCityInfo("上海");
        System.out.println(cityResult);

        TopCityResult topCityResult = cityApi.queryTopCityInfo("cn");
        System.out.println(topCityResult);

        City location = cityResult.getLocation().get(0);

        WeatherResult weatherResult = weatherApi.queryWeatherNow(location.getId());
        System.out.println(weatherResult);

        WeatherResult weatherResult1 = weatherApi.queryNextDaysWeather(3, location.getId());
        System.out.println(weatherResult1.getDaily());

        WeatherResult weatherResult2 = weatherApi.queryNextHoursWeather(24, location.getId());
        System.out.println(weatherResult2.getHourly());
    }

    @Test
    void testSendWeather() throws WxErrorException {

        WeatherResult weatherResult = weatherApi.queryWeatherNow(Constants.CHANG_NING_LOCATION_ID);
        WeatherResult.NowWeather now = weatherResult.getNow();

        int days = 3;
        WeatherResult nextDaysWeather = weatherApi.queryNextDaysWeather(days, Constants.CHANG_NING_LOCATION_ID);

        StringBuilder sb = new StringBuilder();
        sb.append("长宁区天气预报：\r\n")
                .append("· 天气：").append(now.getText()).append("\r\n")
                .append("· 温度：").append(now.getTemp()).append("℃").append("\r\n")
                .append("· 体感温度：").append(now.getFeelsLike()).append("℃").append("\r\n")
                .append("· 数据观测于：").append(DateUtil.format(now.getObsTime(), "MM-dd HH:mm")).append("\r\n");

        sb.append("未来").append(days).append("天的天气状况：");

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

        System.out.println(sb.toString());

        WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
        config.setCorpId("wwfa47dbc0016a7a49");      // 设置微信企业号的appid
        config.setCorpSecret("CkCTVGQHnOTvNZq6b87vN0CxTPZpcrU_dogYPG2v1xs");  // 设置微信企业号的app corpSecret

        WxCpServiceImpl wxCpService = new WxCpServiceImpl();
        wxCpService.setWxCpConfigStorage(config);

        WxCpMessage message = WxCpMessage.TEXT().agentId(1000002).toUser("WangGuoPing").content(sb.toString()).build();

		wxCpService.getMessageService().send(message);

    }

}
