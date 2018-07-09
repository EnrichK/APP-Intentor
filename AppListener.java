import java.util.Timer;
import java.util.TimerTask;
import android.app.ActivityManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class AppListener extends Service{
    private boolean isAppStart = false;
    private String packageName_now = "";
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {        
         timer.schedule(task, 0, 500);
        super.onCreate();
    }
     Handler handler_listen = new Handler() {  
            public void handleMessage(Message msg) {  
                if (msg.what == 1) {  
                    ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
                    ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
                    String packageName = cn.getPackageName();
                    if (!packageName_now.equals(packageName)) {
                        packageName_now=packageName;
                        isAppStart=false;
                    }
                    if (packageName.equals("com.tencent.mobileqq")) {
                        if (!isAppStart) {
                            isAppStart=true;
                            
                        }
                    }
                }  
                super.handleMessage(msg);  
            };  
        };  
        Timer timer = new Timer();  
        TimerTask task = new TimerTask() {  

            @Override  
            public void run() {  
                Message message = new Message();  
                message.what = 1;  
                handler_listen.sendMessage(message);  
            }  
        };  

    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

}