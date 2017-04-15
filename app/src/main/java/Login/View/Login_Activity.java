package Login.View;

/**
 * Created by XZC on 2017/4/7.
 */
import android.os.Bundle;
import android.os.PersistableBundle;

import Content.myAppCompatActivity;
import deazy.myapp.R;

public class Login_Activity extends myAppCompatActivity implements Login_View {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.login);
    }



    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setUsernameError() {

    }

    @Override
    public void setPasswordError() {

    }

    @Override
    public void toHome() {

    }

    @Override
    public void toRegister() {

    }

    @Override
    public void to_Third_party_Login() {

    }
}
