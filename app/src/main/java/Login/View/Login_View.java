package Login.View;

/**
 * Created by XZC on 2017/4/7.
 */

public interface Login_View {
    /**显示进度动画*/
    void showProgress();
    /**隐藏进度动画*/
    void hideProgress();
    /**设置用户名失败时的错误View*/
    void setUsernameError();
    /**设置密码失败时的错误View*/
    void setPasswordError();
    /**登录成功时进入主菜单*/
    void toHome();
    /**没有账号进入注册界面*/
    void toRegister();
    /**第三方账号登录*/
    void to_Third_party_Login();
}
