package Content.Crash;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by XZC on 2017/4/19.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private static CrashHandler sInstance = new CrashHandler();
    private  static final boolean Debug = true ;
    private Context mContext;
    private static final String TAG = "CrashHandler";
    private static final String PATH = Environment.getExternalStorageDirectory().getPath() + "/xzc_text/log/";
    private static final String FILE_NAME = "crash";
    private static final String FILE_NAME_SUFFIX = ".trace";
    private CrashHandler(){

    }
    public static CrashHandler getInstance(){
        return sInstance;
    }

    public void init(Context context){
        //获取系统默认的异常处理器
        mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        //将当前实例设为系统默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
        //获取context。方便内部使用
        mContext = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try {
            dumpExceptionToSDCard(e);
            uploadExceptionToService();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if(mDefaultCrashHandler != null){
            mDefaultCrashHandler.uncaughtException(t , e);
        }else {
            Process.killProcess(Process.myPid());
        }
    }


    private void dumpExceptionToSDCard(Throwable ex) throws IOException{
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            if(Debug){
                Log.w(TAG,"SDCard unmounted , skipdump excption" );
                return;
            }
        }

        File dir = new File(PATH);
        if(!dir.exists()){
            dir.mkdirs();
        }
        long current = System.currentTimeMillis();
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));

        File file = new File(PATH + FILE_NAME + FILE_NAME_SUFFIX);

        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));

        try {
            pw.println(time);
            dumpPhoneInfo(pw);
            pw.println();
            ex.printStackTrace(pw);
            pw.close();
        }catch (Exception e){
            Log.e(TAG, "dump crash info failed ");
        }

    }

    private void dumpPhoneInfo(PrintWriter pw) throws PackageManager.NameNotFoundException{
        PackageManager pm = mContext.getPackageManager();
        PackageInfo pi =pm.getPackageInfo(mContext.getPackageName(),PackageManager.GET_ACTIVITIES);
        pw.print("APP version: ");
        pw.print(pi.versionName);
        pw.print('_');
        pw.println(pi.versionCode);

        pw.print("OS version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print('_');
        pw.println(Build.VERSION.SDK_INT);

        pw.print("Vender: ");
        pw.println(Build.MANUFACTURER);

        pw.print("Model: ");
        pw.println(Build.MODEL);

        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);


    }

    private void uploadExceptionToService() {
        //do something to Service
//        Toast.makeText(mContext, "呵呵，报错了", Toast.LENGTH_SHORT).show();
        new Thread() {
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, "很抱歉,程序出现异常,即将退出", 0).show();
                Looper.loop();
            }
        }.start();
    }


}
