package bbgg.daily.task.api.fund.model;

import cn.hutool.core.annotation.Alias;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 基金详情
 */
@Getter
@Setter
@ToString
public class FundInfoResult {

    /**
     * 基金详情
     */
    @Alias("JJXQ")
    private FundBaseResult<FundDetailDatas, String> fundDetail;

    /**
     * 基金风险
     */
    @Alias("JJFX")
    private FundBaseResult<FundRiskDatas, String> fundRisk;

    /**
     * 基金持仓
     */
    @Alias("JJCC")
    private FundBaseResult<FundHoldingsDatas, Date> fundHoldings;


    @Getter
    @Setter
    @ToString
    public static class FundDetailDatas {
        private String FCODE;
        private String SHORTNAME;
        private String FTYPE;
        private String FEATURE;
        private String BFUNDTYPE;
        private String FUNDTYPE;
        private String RZDF;
        private String DWJZ;
        private String LJJZ;
        private String SGZT;
        private String SHZT;
        private String SOURCERATE;
        private String RATE;
        private String MINSG;
        private String MAXSG;
        private String SUBSCRIBETIME;
        private String RISKLEVEL;
        private String ISBUY;
        private String BAGTYPE;
        private String CASHBUY;
        private String SALETOCASH;
        private String STKTOCASH;
        private String STKEXCHG;
        private String FUNDEXCHG;
        private boolean BUY;
        private String ISSALES;
        private String SALEMARK;
        private String MINDT;
        private String DTZT;
        private String REALSGCODE;
        private String QDTCODE;
        private String BACKCODE;
        private Date ESTABDATE;
        private String INDEXCODE;
        private String INDEXNAME;
        private String INDEXTEXCH;
        private String NEWINDEXTEXCH;
        private String RLEVEL_SZ;
        private String SHARP1;
        private String SHARP2;
        private String SHARP3;
        private String MAXRETRA1;
        private String STDDEV1;
        private String STDDEV2;
        private String STDDEV3;
        private String SSBCFMDATA;
        private Date SSBCFDAY;
        private String CURRENTDAYMARK;
        private String BUYMARK;
        private String JJGS;
        private String JJGSID;
        private String TSRQ;
        private String TTYPENAME;
        private String TTYPE;
        private String FundSubjectURL;
        private String FBKINDEXCODE;
        private String FBKINDEXNAME;
        private Date FSRQ;
        private Date ISSBDATE;
        private String RGBEGIN;
        private Date ISSEDATE;
        private String RGEND;
        private String LISTTEXCH;
        private String NEWTEXCH;
        private String ISLIST;
        private String ISLISTTRADE;
        private String MINSBSG;
        private String MINSBRG;
        private String ENDNAV;
        private Date FEGMRQ;
        private String ISFNEW;
        private String ISAPPOINT;
        private String MINRG;
        private String CYCLE;
        private String OPESTART;
        private String OPEEND;
        private String OPEYIELD;
        private String FIXINCOME;
        private String APPOINTMENT;
        private String APPOINTMENTURL;
        private String ISABNORMAL;
        private String YZBA;
        private String FBYZQ;
        private String KFSGSH;
        private String LINKZSB;
        private String LISTTEXCHMARK;
        private boolean ISHAREBONUS;
        private String PTDT_Y;
        private String PTDT_TWY;
        private String PTDT_TRY;
        private String PTDT_FY;
        private String MBDT_Y;
        private String MBDT_TWY;
        private String MBDT_TRY;
        private String MBDT_FY;
        private String YDDT_Y;
        private String YDDT_TWY;
        private String YDDT_TRY;
        private String YDDT_FY;
        private String DWDT_Y;
        private String DWDT_TWY;
        private String DWDT_TRY;
        private String DWDT_FY;
        private String ISYYDT;
        private String SYL_Z;
        private Date SYRQ;
        private String COMETHOD;
        private String MCOVERDATE;
        private String MCOVERDETAIL;
        private String COMMENTS;
        private String TRKERROR;
        private String ESTDIFF;
        private String HRGRT;
        private String HSGRT;
        private String BENCH;
        private String FINSALES;
        private String INVESTMENTIDEAR;
        private String INVESTMENTIDEARIMG;
    }


    @Getter
    @Setter
    @ToString
    public static class FundRiskDatas {

        private String FCODE;
        private String SHORTNAME;
        private String FUNDTYPE;
        private String PLTDATE;
        private String BFUNDTYPE;
        private String FEATURE;
        private Date FSRQ;
        private String RZDF;
        private String DWJZ;
        private String SYI;
        private String SYL;
        private String SYLNAME;
        private String PERIODNAME;
        private String MCOVERDETAIL;
        private String COMETHOD;
        private String ISBUY;
        private boolean BUY;
        private String ISSALES;
        private String MINDT;
        private String DTZT;
        private String APPOINTMENT;
        private String APPOINTMENTURL;
        private String SHAREURL;
        private String CFHID;
        private String CFHName;
        private String HeaderImgPath;
    }


    @Getter
    @Setter
    @ToString
    public static class FundHoldingsDatas {
        private InverstPosition InverstPosition;
        private Map<String, List<Sector>> SectorAllocation;
    }


    @Getter
    @Setter
    @ToString
    public static class InverstPosition {
        private List<FundStocks> fundStocks;
        private List<Fundboods> fundboods;
        private List<String> fundfofs;
        private String ETFCODE;
        private String ETFSHORTNAME;
    }

    @Getter
    @Setter
    @ToString
    public static class FundStocks {
        private String GPDM;
        private String GPJC;
        private String JZBL;
        private String TEXCH;
        private String ISINVISBL;
        private String PCTNVCHGTYPE;
        private String PCTNVCHG;
        private String NEWTEXCH;
        private String INDEXCODE;
        private String INDEXNAME;
    }

    @Getter
    @Setter
    @ToString
    public static class Fundboods {
        private String ZQDM;
        private String ZQMC;
        private String ZJZBL;
        private String ISBROKEN;
    }


    @Getter
    @Setter
    @ToString
    public static class Sector {
        private String HYMC;
        private String SZ;
        private String ZJZBL;
        private Date FSRQ;
    }

}