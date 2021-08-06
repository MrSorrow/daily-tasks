package bbgg.daily.task;

import bbgg.daily.task.constants.Constants;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitScan;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@RetrofitScan("bbgg.daily.task.api")
public class DemoApplication {

	@Bean
	public WxCpService wxCpService() {
		WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
		config.setCorpId(Constants.CROP_ID);
		config.setCorpSecret(Constants.SECRET);

		WxCpService wxCpService = new WxCpServiceImpl();
		wxCpService.setWxCpConfigStorage(config);
		return wxCpService;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

