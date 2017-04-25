package Content.Inject;

import Content.myAppCompatActivity;
import dagger.Component;

/**
 * Created by XZC on 2017/4/7.
 */
@Component
public interface mainComponent{
        void inject(myAppCompatActivity activity);
}
