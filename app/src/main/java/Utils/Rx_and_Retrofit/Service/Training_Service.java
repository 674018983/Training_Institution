package Utils.Rx_and_Retrofit.Service;


import Utils.Rx_and_Retrofit.Model.Training_Institution;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by XZC on 2017/4/15.
 */

public interface Training_Service {
    String SERVICE_ENDPOINT = "http://deazy.cn";
    @GET("/users/{name}")
    Observable<Training_Institution> getData(@Path("name") String login);
}
