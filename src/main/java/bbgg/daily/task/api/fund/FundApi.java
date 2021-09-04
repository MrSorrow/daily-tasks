package bbgg.daily.task.api.fund;

import bbgg.daily.task.api.fund.model.PredictResult;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @description:
 * @author: guoping
 * @date: 2021/9/5
 */
@RetrofitClient(baseUrl = "https://fundmobapi.eastmoney.com/")
public interface FundApi {

    @GET("FundMApi/FundVarietieValuationDetail.ashx?" +
            "&deviceid=5C1164BA-3D4A-4953-A488-00E47222D4BB" +
            "&plat=Iphone&product=EFund&version=6.2.5")
    String queryPredictResult(@Query("FCODE") String fCode);

}
