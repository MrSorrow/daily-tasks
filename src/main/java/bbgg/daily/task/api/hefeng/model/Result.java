package bbgg.daily.task.api.hefeng.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @description:
 * @author: guoping
 * @date: 2021/7/7
 */
@Getter
@Setter
@ToString
public abstract class Result {

    /**
     * API 请求状态码
     * https://dev.qweather.com/docs/start/status-code/
     */
    private String code;
    private Refer refer;


    @Getter
    @Setter
    @ToString
    public static class Refer {
        /**
         * 原始数据来源，或数据源说明，可能为空
         */
        private List<String> sources;
        /**
         * 数据许可或版权声明，可能为空
         */
        private List<String> license;
    }
}
