package bbgg.daily.task.api.fund;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @description:
 * @author: guoping
 * @date: 2021/9/5
 */
@RetrofitClient(baseUrl = "https://placeholder/")
public interface FundApi {

    @GET("https://fundmobapi.eastmoney.com/FundMApi/FundVarietieValuationDetail.ashx?" +
            "deviceid=5C1164BA-3D4A-4953-A488-00E47222D4BB" +
            "&plat=Iphone&product=EFund&version=6.2.5")
    String queryPredictResult(@Query("FCODE") String fCode);

    @GET("https://j5.fund.eastmoney.com/sc/tfs/qt/v2.0.1/{fCode}.json")
    String queryFundInfo(@Path("fCode") String fCode);

}
