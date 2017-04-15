package Content.Inject;

import javax.inject.Inject;

import Content.myAppCompatActivity;
import dagger.Component;
import dagger.Module;
import UI.LogUtils.logUtil;
/**
 * Created by XZC on 2017/4/7.
 */
@Component
public interface mainComponent{
        void inject(myAppCompatActivity activity);
}
