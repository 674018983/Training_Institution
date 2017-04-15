package Login.Model;

/**
 * Created by XZC on 2017/4/7.
 */

public interface Login_Model {
    interface onFinishListener{
        void onSuccess();
        void Faile();
        void register();
        void third_party_Login();
    }
    void Login(String Username,String Password,onFinishListener listener);
}
