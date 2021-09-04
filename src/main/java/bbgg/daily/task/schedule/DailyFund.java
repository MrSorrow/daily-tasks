package bbgg.daily.task.schedule;

import bbgg.daily.task.api.fund.FundApi;
import bbgg.daily.task.api.fund.model.Expansion;
import bbgg.daily.task.api.fund.model.PredictResult;
import bbgg.daily.task.api.hefeng.WeatherApi;
import bbgg.daily.task.api.hefeng.model.WeatherResult;
import bbgg.daily.task.constants.Constants;
import bbgg.daily.task.push.WxPush;
import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @description: 每日基金
 * @author: guoping
 * @date: 2021/9/05
 */
@Slf4j
@Component
public class DailyFund {

    private static final List<String> FUND_CODE_LIST;

    static {
        FUND_CODE_LIST = new ArrayList<>(6);
        FUND_CODE_LIST.add("161725");
        FUND_CODE_LIST.add("160225");
        FUND_CODE_LIST.add("008282");
        FUND_CODE_LIST.add("160222");
        FUND_CODE_LIST.add("006757");
        FUND_CODE_LIST.add("001579");
    }

    @Autowired
    private WxPush wxPush;
    @Autowired
    private FundApi fundApi;

    @Scheduled(cron = "0 0 14 * * ? ")
    public void toMissZhou() {

        StringBuilder sb = new StringBuilder();
        sb.append("【基金行情播报】\r\n");

        for (int i = 0; i < FUND_CODE_LIST.size(); i++) {

            String json = fundApi.queryPredictResult(FUND_CODE_LIST.get(i));
            PredictResult predictResult = JSONUtil.toBean(json, PredictResult.class);
            Expansion expansion = predictResult.getExpansion();

            sb.append("\r\n").append("[").append(i + 1).append("]")
                    .append(expansion.getShortName()).append(":").append("\r\n")
                    .append("· 估值：").append(expansion.getGz()).append("\r\n")
                    .append("· 估算涨幅：").append(expansion.getGszzl()).append("%").append("\r\n")
                    .append("· 预测时间：").append(DateUtil.format(expansion.getGztime(), "MM-dd HH:mm")).append("\r\n");

        }

        wxPush.pushTextMsg(sb.toString(), Constants.MMGG_PARTY);
    }
}
