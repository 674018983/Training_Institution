package SMS;

import android.content.Context;

/**
 * Created by XZC on 2017/4/9.
 */

public interface SMS {
    void init_SMS(Context context);
    void send_SMS(String phone_number);
    void EventHandler();

}
