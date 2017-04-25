package Login.Presenter;

import Login.Model.Login_Model;
import Login.Model.Login_Model_Impl;
import Login.View.Login_View;

/**
 * Created by XZC on 2017/4/7.
 */

public class Login_Presenter_Impl implements Login_Presenter ,Login_Model.onFinishListener {

    private Login_View loginView;
    private Login_Model loginModel;

    public void Login_Presenter_Impl(Login_View loginView){
        this.loginView=loginView;
        this.loginModel=new Login_Model_Impl();
    }
    @Override
    public void valildate(String Username,String Password) {
        //显示进度，登录验证
        loginView.showProgress();
        loginModel.Login(Username,Password,this);
    }

    @Override
    public void onSuccess() {
        loginView.hideProgress();
        loginView.toHome();
    }

    @Override
    public void Faile() {
        loginView.hideProgress();
//        loginView.setPasswordError();
//        loginView.setUsernameError();

    }

    @Override
    public void register() {
        loginView.toRegister();
    }

    @Override
    public void third_party_Login() {
        loginView.to_Third_party_Login();
    }


}
