package bbgg.daily.task.api.fund.model;

import cn.hutool.core.annotation.Alias;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class PredictResult extends BaseResult<List<String>, PredictResult.Expansion> {

    @Getter
    @Setter
    @ToString
    public static class Expansion {

        @Alias("FCODE")
        private String fCode;
        @Alias("SHORTNAME")
        private String shortName;
        @Alias("GZTIME")
        private Date gztime;
        @Alias("GZ")
        private String gz;
        @Alias("GSZZL")
        private String gszzl;
        @Alias("GZZF")
        private String gzzf;
        @Alias("SGZT")
        private String sgzt;
        @Alias("SHZT")
        private String shzt;
        @Alias("SOURCERATE")
        private String sourceRate;

        private String rate;
        @Alias("DTZT")
        private String dtzt;
        @Alias("ISBUY")
        private String isbuy;
        @Alias("JZRQ")
        private Date jzrq;
        @Alias("DWJZ")
        private String dwjz;
        @Alias("BUY")
        private boolean buy;
        @Alias("market")
        private String Market;

    }
}