package SMS;

import android.content.Context;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by XZC on 2017/4/9.
 */

public class Mob_SMS implements SMS {
    private final static String appkey="1cd259b2af94c";
    private final static String appsecret="aed1040f158e74f0d2b8e7bd796dad5d";
    @Override
    /**初始化SMS短信发送*/
    public void init_SMS(Context context) {
        SMSSDK.initSDK(context, appkey,appsecret);
    }

    @Override
    /**向指定号码发送验证短信*/
    public void send_SMS(String phone_number) {
        SMSSDK.getVerificationCode("86", phone_number, new OnSendMessageHandler() {
            @Override
            public boolean onSendMessage(String s, String s1) {
                return false;
            }
        });
    }

    @Override
    /**短信回调处理*/
    public void EventHandler() {
        SMSSDK.registerEventHandler(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
        // 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");
                }
            }
        });
    }
}
