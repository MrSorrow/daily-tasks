package bbgg.daily.task;

import bbgg.daily.task.api.fund.FundApi;
import bbgg.daily.task.api.fund.model.FundInfoResult;
import bbgg.daily.task.api.fund.model.PredictResult;
import bbgg.daily.task.api.hefeng.CityApi;
import bbgg.daily.task.api.hefeng.WeatherApi;
import bbgg.daily.task.api.hefeng.model.City;
import bbgg.daily.task.api.hefeng.model.CityResult;
import bbgg.daily.task.api.hefeng.model.TopCityResult;
import bbgg.daily.task.api.hefeng.model.WeatherResult;
import bbgg.daily.task.constants.Constants;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private WeatherApi weatherApi;
    @Autowired
    private CityApi cityApi;
    @Autowired
    private FundApi fundApi;

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

        System.out.println(sb.toString());

        WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
        config.setCorpId("wwfa47dbc0016a7a49");      // 设置微信企业号的appid
        config.setCorpSecret("CkCTVGQHnOTvNZq6b87vN0CxTPZpcrU_dogYPG2v1xs");  // 设置微信企业号的app corpSecret

        WxCpServiceImpl wxCpService = new WxCpServiceImpl();
        wxCpService.setWxCpConfigStorage(config);

        WxCpMessage message = WxCpMessage.TEXT().agentId(1000002).toUser("WangGuoPing").content(sb.toString()).build();

		wxCpService.getMessageService().send(message);

    }

    @Test
    void testFundApi() {

        String json = fundApi.queryPredictResult("513050");
        PredictResult predictResult = JSONUtil.toBean(json, PredictResult.class);
        System.out.println(JSONUtil.toJsonStr(predictResult));

        String fundInfo = fundApi.queryFundInfo("513050");
        FundInfoResult fundInfoResult = JSONUtil.toBean(fundInfo, FundInfoResult.class);
        System.out.println(JSONUtil.toJsonStr(fundInfoResult));
    }


    @Test
    void testPredictFund() throws WxErrorException {
        StringBuilder sb = new StringBuilder();
        sb.append("【基金行情播报】\r\n");

        List<String> FUND_CODE_LIST = new ArrayList<>(2);
        FUND_CODE_LIST.add("161725");
        FUND_CODE_LIST.add("160225");

        for (int i = 0; i < FUND_CODE_LIST.size(); i++) {

            String json = fundApi.queryPredictResult(FUND_CODE_LIST.get(i));
            PredictResult predictResult = JSONUtil.toBean(json, PredictResult.class);
            PredictResult.Expansion expansion = predictResult.getExpansion();

            sb.append("\r\n").append("[").append(i + 1).append("]")
                    .append(expansion.getShortName()).append(":").append("\r\n")
                    .append("· 估值：").append(expansion.getGz()).append("\r\n")
                    .append("· 估算涨幅：").append(expansion.getGszzl()).append("%").append("\r\n")
                    .append("· 预测时间：").append(DateUtil.format(expansion.getGztime(), "MM-dd HH:mm")).append("\r\n");

        }

        WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
        config.setCorpId("wwfa47dbc0016a7a49");      // 设置微信企业号的appid
        config.setCorpSecret("CkCTVGQHnOTvNZq6b87vN0CxTPZpcrU_dogYPG2v1xs");  // 设置微信企业号的app corpSecret

        WxCpServiceImpl wxCpService = new WxCpServiceImpl();
        wxCpService.setWxCpConfigStorage(config);

        WxCpMessage message = WxCpMessage.TEXT().agentId(1000002).toUser("WangGuoPing").content(sb.toString()).build();

        wxCpService.getMessageService().send(message);
    }
}
