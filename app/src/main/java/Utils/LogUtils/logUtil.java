package Utils.LogUtils;

import javax.inject.Inject;

import Content.Info;

/**
 * Created by XZC on 2017/4/7.
 */
public class logUtil {

    private Log mLog;
    @Inject
    public  logUtil(){
        //选择log工具
        mLog = new logutils();
    }
    /**日志输出json数据*/
    public void Json(Object object){
        if (true== Info.ACTIVITY_LOG) {
            mLog.log_Json(object);
        }
    }
    /**日志输出用Verbose类型*/
    public void Verbose(Object object){
        if (true== Info.ACTIVITY_LOG) {
        mLog.log_Verbose(object);
        }
    }
    /**日志输出用Info类型*/
    public void Info(Object object){
        if (true== Info.ACTIVITY_LOG) {
            mLog.log_Info(object);
        }
    }
    /**日志输出用Warn类型*/
    public void Warn(Object object){
        if (true== Info.ACTIVITY_LOG) {
            mLog.log_Warn(object);
        }
    }
    /**日志输出用Error类型*/
    public void Error(Object object){
        if (true== Info.ACTIVITY_LOG) {
            mLog.log_Error(object);
        }
    }
    /**日志输出用Assert类型*/
    public void Assert(Object object){
        if (true== Info.ACTIVITY_LOG) {
            mLog.log_Assert(object);
        }
    }
    /**日志输出用Debug类型*/
    public void Debug(Object object){
        if (true== Info.ACTIVITY_LOG) {
            mLog.log_Debug(object);
        }
    }


}
