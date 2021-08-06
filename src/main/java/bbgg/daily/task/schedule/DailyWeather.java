package bbgg.daily.task.schedule;

import bbgg.daily.task.constants.Constants;
import bbgg.daily.task.push.WxPush;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @description: 每日天气
 * @author: guoping
 * @date: 2021/6/18
 */
@Slf4j
@Component
public class DailyWeather {

    @Autowired
    private WxPush wxPush;

    @Scheduled(cron = "0 48,49 10 * * ? ")
    public void toMissZhou() {



        wxPush.pushTextMsg("ss", Constants.MMGG_PARTY);
    }
}
