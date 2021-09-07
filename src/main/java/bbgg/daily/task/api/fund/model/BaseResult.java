package bbgg.daily.task.api.fund.model;

import cn.hutool.core.annotation.Alias;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @description:
 * @author: guoping
 * @date: 2021/9/6
 */
@Getter
@Setter
@ToString
public class BaseResult<T, U> {

    @Alias("ErrCode")
    private Integer errCode;

    @Alias("ErrMsg")
    private String errMsg;

    @Alias("TotalCount")
    private Integer totalCount;

    @Alias("Datas")
    private T datas;

    @Alias("Expansion")
    private U expansion;
}
