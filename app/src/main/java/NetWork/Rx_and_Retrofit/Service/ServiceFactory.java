package NetWork.Rx_and_Retrofit.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by XZC on 2017/4/15.
 */

public class ServiceFactory {

    public static <T> T createTrainingService(final Class<T> tClass , final String endpoint){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(new ItemTypeAdapterFactory());
        Gson mgson = gsonBuilder.create();
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setConverter(new GsonConverter(mgson))
                .build();
        T service = restAdapter.create(tClass);

        return service;
    }
}
