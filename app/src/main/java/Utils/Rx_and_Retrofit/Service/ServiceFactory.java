package Utils.Rx_and_Retrofit.Service;

import retrofit.RestAdapter;

/**
 * Created by XZC on 2017/4/15.
 */

public class ServiceFactory {
    public static <T> T createTrainingService(final Class<T> tClass , final String endpoint){
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .build();
        T service = restAdapter.create(tClass);

        return service;
    }
}
