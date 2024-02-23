package mm.pp.clicker.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


import androidx.core.app.NotificationCompat;

import java.io.IOException;

import mm.pp.clicker.R;
import mm.pp.clicker.viewmodel.HomeViewViewModel;


public class HttpService extends Service {
    private static HttpService instance=null;

    public static HttpService getInstance(){
        return instance;
    }

    public static Boolean isServiceRuning(){
        return  instance!=null;
    }

    public HttpService() {
    }

    private HttpServer server;
    private static final int PORT = 8080;

    @Override
    public IBinder onBind(Intent intent) {
        // 我们不需要绑定，所以返回null
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        try {
            server = new HttpServer(Integer.valueOf(HomeViewViewModel.port.getValue()));
            server.start();
            Log.d("HttpService", "Server started on port " + Integer.valueOf(HomeViewViewModel.port.getValue()));
        } catch (IOException e) {
            Log.e("HttpService", "Error starting server", e);
            stopSelf(); // 如果启动失败，停止服务
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 启动HttpServer
        if(intent.getBooleanExtra("reboot",false)){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(),"mm.pp.clicker")
                    .setSmallIcon(R.mipmap.ic_launcher_round)
                    .setContentTitle("clicker")
                    .setContentText("已启动")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(NotificationManager.class);
            //notificationManager.notify(122,);
            startForeground(123,builder.build());
        }

        // 启动服务器


        return START_NOT_STICKY; // 服务在停止后不会自动重启
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance=null;
        // 停止HttpServer
        if (server != null) {
            server.stop();
            Log.d("HttpService", "Server stopped");
        }
    }
}