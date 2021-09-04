package bbgg.daily.task.api.fund.model;
import cn.hutool.core.annotation.Alias;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PredictResult {

    @Alias("Datas")
    private List<String> datas;

    @Alias("ErrCode")
    private Integer errCode;

    @Alias("ErrMsg")
    private String errMsg;

    @Alias("TotalCount")
    private Integer totalCount;

    @Alias("Expansion")
    private Expansion expansion;

}