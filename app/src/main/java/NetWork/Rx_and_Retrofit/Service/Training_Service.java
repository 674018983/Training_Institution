package NetWork.Rx_and_Retrofit.Service;


import java.util.List;

import NetWork.Rx_and_Retrofit.Model.Training_Institution;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by XZC on 2017/4/15.
 */

public interface Training_Service {
    String SERVICE_ENDPOINT = "http://deazy.cn:8080";
    @GET("/search")
    Observable<List<Training_Institution>> getData(@Query("name") String name);
}
