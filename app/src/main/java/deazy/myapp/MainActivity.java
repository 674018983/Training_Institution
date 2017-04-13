package deazy.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

import javax.inject.Inject;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import SMS.SMS_Utils;

public class MainActivity extends AppCompatActivity {
    @Inject
    SMS_Utils mSMS;
    private RegisterPage registerPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
//        SMSSDK.initSDK(this, "1cd259b2af94c", "aed1040f158e74f0d2b8e7bd796dad5d");
        mSMS.init_SMS(this);
        //asdjfhaskcccsssss
        setContentView(R.layout.activity_main);
        mSMS.EventHandler();
        mSMS.send_SMS("15914478944");
//        SMSSDK.registerEventHandler(new EventHandler() {
//                        public void afterEvent(int event, int result, Object data) {
//        // 解析注册结果
//                if (result == SMSSDK.RESULT_COMPLETE) {
//                    @SuppressWarnings("unchecked")
//                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
//                    String country = (String) phoneMap.get("country");
//                    String phone = (String) phoneMap.get("phone");
//
////            // 提交用户信息（此方法可以不调用）
////                    registerUser(country, phone);
//                }
//            }
//        });
//        registerPage = new RegisterPage();
//        registerPage.setRegisterCallback(new EventHandler() {
//            public void afterEvent(int event, int result, Object data) {
//        // 解析注册结果
//                if (result == SMSSDK.RESULT_COMPLETE) {
//                    @SuppressWarnings("unchecked")
//                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
//                    String country = (String) phoneMap.get("country");
//                    String phone = (String) phoneMap.get("phone");
//
////            // 提交用户信息（此方法可以不调用）
////                    registerUser(country, phone);
//                }
//            }
//        });
//        getSMS();


    }
//    private void getSMS() {
//        String phone ="15914478944".toString();
//        SMSSDK.getVerificationCode("86", phone, new OnSendMessageHandler() {
//            @Override
//            public boolean onSendMessage(String s, String s1) {
//                return false;
//            }
//        });
//    }
}
