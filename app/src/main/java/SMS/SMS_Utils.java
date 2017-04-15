package SMS;

import android.content.Context;

import javax.inject.Inject;



/**
 * Created by XZC on 2017/4/9.
 */

public class SMS_Utils implements SMS{
    private SMS mSMS;
    @Inject
    public  SMS_Utils(){
        //选择SMS
        mSMS = new Mob_SMS();
    }


    @Override
    public void init_SMS(Context context) {
        mSMS.init_SMS(context);
    }

    @Override
    public void send_SMS(String phone_number) {
        mSMS.send_SMS(phone_number);
    }

    @Override
    public void EventHandler() {
        mSMS.EventHandler();
    }
}
