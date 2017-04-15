package deazy.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    @BindView(R.id.web)
    EditText web;
    @BindView(R.id.company)
    EditText company;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.confim)
    Button confim;
    @BindView(R.id.text)
    TextView text;
    private String json;
    private To_Json g;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        g = new To_Json();
//        json = g.To_Json(new Training_Institution("达内", "美国上市公司"));
    }

    private void postJson(String web,String json1) {
        //申明给服务端传递一个json串
        //创建一个OkHttpClient对象
        OkHttpClient okHttpClient = new OkHttpClient();
        //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
        RequestBody requestBody = RequestBody.create(JSON, json1);
        //创建一个请求对象
        Request request = new Request.Builder()
                .url(web)
                .post(requestBody)
                .build();
        //发送请求获取响应
        try {
            Response response = okHttpClient.newCall(request).execute();
            //判断请求是否成功
            if (response.isSuccessful()) {
                //打印服务端返回结果
                Log.i("回调结果", response.body().string());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnClick(R.id.confim)
    public void onClick() {
        if("".equals(web.getText().toString())){
            Toast.makeText(this, "网站不能为空", Toast.LENGTH_SHORT).show();
        } else{
            String company1 = company.getText().toString();
            String address1 = address.getText().toString();
            if(company1 == null){
                company1 = "";
            }
            if(address1 == null){
                address1 = "";
            }
            json = g.To_Json(new Training_Institution(company1,address1));
            text.setText(json);
            postJson(web.getText().toString(),json);
        }
    }
}
