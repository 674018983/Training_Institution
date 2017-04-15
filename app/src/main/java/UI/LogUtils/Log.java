package UI.LogUtils;

import java.util.Objects;

/**
 * Created by XZC on 2017/4/7.
 */

public interface Log {
    void log_Json(Object object);
    void log_Verbose(Object object);
    void log_Info(Object object);
    void log_Warn(Object object);
    void log_Error(Object object);
    void log_Assert(Object object);
    void log_Debug(Object object);
}
