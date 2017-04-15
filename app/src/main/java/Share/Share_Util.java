package Share;

import android.content.Context;

import javax.inject.Inject;


/**
 * Created by XZC on 2017/4/10.
 */

public class Share_Util implements share {
    private share mShare;
    @Inject
    public  Share_Util(){
        //选择分享工具
        mShare = new Mob_share();
    }

    @Override
    public void init_share(Context context) {
        mShare.init_share(context);
    }

    @Override
    public void show_share(Context context) {
        mShare.show_share(context);
    }
}
