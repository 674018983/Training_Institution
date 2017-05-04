package NetWork.Rx_and_Retrofit.Service;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import NetWork.Rx_and_Retrofit.Model.Training_Institution;

/**
 * Created by XZC on 2017/4/29.
 */

public class ItemTypeAdapterFactory implements TypeAdapterFactory {
    private static final String TAG = "ItemTypeAdapterFactory";
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this,type);
        final TypeAdapter<JsonElement> elementTypeAdapter = gson.getAdapter(JsonElement.class);

        return  new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out,value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                JsonElement jsonElement = elementTypeAdapter.read(in);
                Log.e(TAG, "read: "+jsonElement.toString());

                if(jsonElement.isJsonObject()){
                    Log.e(TAG, "read: "+"有分支"+jsonElement.toString());

                    return delegate.fromJson(jsonElement.toString());
                }
//                    return (T) jsonElement.toString();
                return delegate.fromJsonTree(jsonElement);
            }
        }.nullSafe();

    }
}
