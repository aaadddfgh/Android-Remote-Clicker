package mm.pp.clicker.bootstart;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import java.time.LocalDateTime;

import mm.pp.clicker.R;
import mm.pp.clicker.activity.MainActivity;
import mm.pp.clicker.security.Crypto;
import mm.pp.clicker.security.CryptoKey;
import mm.pp.clicker.service.ClickerService;
import mm.pp.clicker.service.HttpService;
import mm.pp.clicker.viewmodel.HomeViewViewModel;

import static androidx.core.content.ContextCompat.getSystemService;

public class BootReceiver extends BroadcastReceiver {

    private static final String TAG = "mm.pp.clicker.BootReceiver";

    private void createNotificationChannel(Context context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "mm.pp.clicker";
            String description = "clicker";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("mm.pp.clicker", name, importance);
            channel.setDescription(description);
            // Register the channel with the system. You can't change the importance
            // or other notification behaviors after this.
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void initSetting(Context context){
        HomeViewViewModel.port.setValue(context.getSharedPreferences("mm.pp.clicker",0).getString("port","8080"));
        HomeViewViewModel.needPassword.setValue(context.getSharedPreferences("mm.pp.clicker",0).getBoolean("needPassword",false));
        CryptoKey.key=context.getSharedPreferences("mm.pp.clicker",0).getString("key","");
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"开机启动"+ LocalDateTime.now().toString());

        context.getSharedPreferences("mm.pp.clicker",Context.MODE_PRIVATE).getAll().forEach((key,value)->{
            Log.d(TAG,"key:"+key+" val:"+value.toString());

        });

        Log.d(TAG,  context.getSharedPreferences("mm.pp.clicker",Context.MODE_PRIVATE).getBoolean("reboot",false)+" "+intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED));
        //开机启动
        if (context.getSharedPreferences("mm.pp.clicker",Context.MODE_PRIVATE).getBoolean("reboot",false) && intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            initSetting(context);
            Log.d(TAG,"setting done!");
            //createNotificationChannel(context);

            try {
                Log.d(TAG, "msg has sent!");
                Intent clickerIntent = new Intent(context, ClickerService.class);
                Log.d(TAG, "ClickerService Intent");
                clickerIntent.putExtra("reboot",true);
                context.startForegroundService(clickerIntent);
                Log.d(TAG, "ClickerService started");
                Intent serverIntent = new Intent(context, HttpService.class);
                serverIntent.putExtra("reboot",true);
                Log.d(TAG, "HttpService Intent");
                context.startForegroundService(serverIntent);
                Log.d(TAG, "HttpService started");
            }
            catch (Exception e){
                Log.e(TAG, "", e);
            }
        }
    }
}