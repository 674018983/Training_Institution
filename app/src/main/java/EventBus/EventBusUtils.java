package EventBus;

import android.os.Handler;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by XZC on 2017/4/9.
 */

public class EventBusUtils {
    private static final EventBus mEventBus= EventBus.builder()
            .strictMethodVerification(true)
            .throwSubscriberException(true)
            .build();
    private EventBusUtils() {}

    public static void register(Object subscriber) {
        mEventBus.register(subscriber);
    }

    public static void unregister(Object subscriber) {
        mEventBus.unregister(subscriber);
    }

    public static void postSync(Object event) {
        mEventBus.post(event);
    }

    public static void postAsync(final Object event) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                mEventBus.post(event);
            }
        });
    }
}
