package deazy.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Adapter.CardAdapter;
import Utils.Okhttp.key_value;
import Utils.Okhttp.okhttp_GET;
import okhttp3.Response;

/**
 * Created by XZC on 2017/4/25.
 */

public class act_main2 extends Activity {

    private EditText name;
    private EditText address;
    private Button insert;
    private Button search;
    private Button clear;
    private TextView show;
    private okhttp_GET oknet;
    private RecyclerView recyclerView;
    private CardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setContentView(R.layout.layout_main2);
        init();
    }



    private void init() {
        name = (EditText) findViewById(R.id.name);
        address = (EditText) findViewById(R.id.address);
        insert = (Button) findViewById(R.id.insert);
        search = (Button) findViewById(R.id.search);
        clear = (Button) findViewById(R.id.clear);
        show = (TextView) findViewById(R.id.show);

        oknet = new okhttp_GET();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cardAdapter = new CardAdapter();
        recyclerView.setAdapter(cardAdapter);

       insert.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String name1 = name.getText().toString();
               String address1 = address.getText().toString();
               Toast.makeText(act_main2.this, name1+";"+address1, Toast.LENGTH_SHORT).show();
               net("http://deazy.cn/insert",name1,address1);
           }
       });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = name.getText().toString();
                String address1 = address.getText().toString();

                Toast.makeText(act_main2.this, name1+";"+address1, Toast.LENGTH_SHORT).show();
                net1("http://deazy.cn:8080/search",name1);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardAdapter.clear();
                show.setText("信息原始代码");
            }
        });

    }

    public void net(String str,String str1,String str2){
        List<key_value> kk = new ArrayList<>();
        kk.add(new key_value("name",str1));
        kk.add(new key_value("address",str2));
        oknet.okhttp_GET(str,kk ,new okhttp_GET.OnOkhttpListener() {
            @Override
            public void onFailure() {
                show.setText("出错了吧");
            }

            @Override
            public void onResponse(final Response response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e("老奶奶过马路", "onClick: "+response.toString());
                        try {
                            show.setText(response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

//        oknet.okhttp_GET(str, new okhttp_GET.OnOkhttpListener() {
//            @Override
//            public void onFailure() {
//                show.setText("出错了吧");
//            }
//
//            @Override
//            public void onResponse(final Response response) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.e("老奶奶过马路", "onClick: "+response.toString());
//                        show.setText(response.toString());
//                    }
//                });
//
//            }
//        });
    }
    public void net1(String str,String str1) {
        List<key_value> kk = new ArrayList<>();
        kk.add(new key_value("name", str1));
//        oknet.okhttp_GET(str, kk, new okhttp_GET.OnOkhttpListener() {
        oknet.okhttp_GET(str, new okhttp_GET.OnOkhttpListener() {

            private String string;

            @Override
            public void onFailure() {
                show.setText("出错了吧");
            }

            @Override
            public void onResponse(Response response) {
                Log.e("老奶奶过马路", "onClick: " + response.toString());
                try {
                    string = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.e("老奶奶过马路", "onClick: " + string);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {


//                            show.setText(string);
                            cardAdapter.addData(string);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });
    }
}
