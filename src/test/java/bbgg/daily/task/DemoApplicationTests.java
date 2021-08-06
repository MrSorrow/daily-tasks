package bbgg.daily.task;

import bbgg.daily.task.api.hefeng.CityApi;
import bbgg.daily.task.api.hefeng.WeatherApi;
import bbgg.daily.task.api.hefeng.model.LocationResult;
import bbgg.daily.task.api.hefeng.model.WeatherResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.message.WxCpMessage;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

		wxCpService.getMessageService().send(message);
	}

	@Test
	void testWeatherCityInfo() {
		LocationResult locationResult = cityApi.queryCityInfo("上海");
		System.out.println(locationResult);

		LocationResult.Location location = locationResult.getLocation().get(0);

		WeatherResult weatherResult = weatherApi.queryWeatherInfo(location.getId());
		System.out.println(weatherResult);
	}

}
