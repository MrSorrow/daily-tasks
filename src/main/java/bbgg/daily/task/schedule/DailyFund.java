package bbgg.daily.task.schedule;

import bbgg.daily.task.api.fund.FundApi;
import bbgg.daily.task.api.fund.model.FundBaseResult;
import bbgg.daily.task.api.fund.model.FundInfoResult;
import bbgg.daily.task.api.fund.model.PredictResult;
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

    private static final List<String> FUND_CODE_LIST_ZHOU;
    private static final List<String> FUND_CODE_LIST_WANG;

    static {
        FUND_CODE_LIST_ZHOU = new ArrayList<>(6);
        FUND_CODE_LIST_ZHOU.add("161725");
        FUND_CODE_LIST_ZHOU.add("160225");

        FUND_CODE_LIST_WANG = new ArrayList<>(6);
        FUND_CODE_LIST_WANG.add("161725");
        FUND_CODE_LIST_WANG.add("160225");
        FUND_CODE_LIST_WANG.add("008282");
        FUND_CODE_LIST_WANG.add("160222");
        FUND_CODE_LIST_WANG.add("006757");
        FUND_CODE_LIST_WANG.add("001579");
    }

    @Autowired
    private WxPush wxPush;
    @Autowired
    private FundApi fundApi;

    @Scheduled(cron = "0 0 14 ? * 2,3,4,5,6 ")
    public void toMissZhou() {

        StringBuilder sb = new StringBuilder();
        sb.append("【基金行情播报】\r\n");

        for (int i = 0; i < FUND_CODE_LIST_ZHOU.size(); i++) {
            sb.append(getFundBoardCastText(i + 1, FUND_CODE_LIST_ZHOU.get(i)));
        }

        wxPush.pushTextMsgToUser(sb.toString(), Constants.MISS_ZHOU_USER);
    }


    @Scheduled(cron = "0 30 14 ? * 2,3,4,5,6 ")
    public void toMrWang() {

        StringBuilder sb = new StringBuilder();
        sb.append("【基金行情播报】\r\n");

        for (int i = 0; i < FUND_CODE_LIST_WANG.size(); i++) {
            sb.append(getFundBoardCastText(i + 1, FUND_CODE_LIST_WANG.get(i)));
        }

        wxPush.pushTextMsgToUser(sb.toString(), Constants.MR_WANG_USER);
    }


    private String getFundBoardCastText(int index, String fundCode) {
        String json = fundApi.queryPredictResult(fundCode);
        PredictResult predictResult = JSONUtil.toBean(json, PredictResult.class);
        PredictResult.Expansion expansion = predictResult.getExpansion();

        String fundInfoJson = fundApi.queryFundInfo(fundCode);
        FundInfoResult fundInfo = JSONUtil.toBean(fundInfoJson, FundInfoResult.class);
        FundBaseResult<FundInfoResult.FundDetailDatas, String> fundDetail = fundInfo.getFundDetail();
        FundInfoResult.FundDetailDatas detailData = fundDetail.getDatas();


        StringBuilder sb = new StringBuilder();
        sb.append("\r\n").append("[").append(index).append("]")
                .append(expansion.getShortName()).append(":").append("\r\n")
                .append("· 单位净值：").append(detailData.getDwjz()).append("\r\n")
                .append("· 日涨跌幅：").append(detailData.getRzdf()).append("\r\n")
                .append("· 预测估值：").append(expansion.getGz()).append("\r\n")
                .append("· 估算涨幅：").append(expansion.getGszzl()).append("%").append("\r\n")
                .append("· 预测时间：").append(DateUtil.format(expansion.getGztime(), "MM-dd HH:mm")).append("\r\n");

        return sb.toString();
    }
}
