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

    private String code;
    private Refer refer;


    @Getter
    @Setter
    @ToString
    public static class Refer {
        private List<String> sources;
        private List<String> license;
    }
}
