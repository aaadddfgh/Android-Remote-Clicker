package mm.pp.clicker.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;



import java.io.IOException;


public class HttpService extends Service {
    private static HttpService instance=null;

    public static HttpService getInstance(){
        return instance;
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
            server = new HttpServer(8080);
            server.start();
            Log.d("HttpService", "Server started on port " + PORT);
        } catch (IOException e) {
            Log.e("HttpService", "Error starting server", e);
            stopSelf(); // 如果启动失败，停止服务
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 启动HttpServer


        // 启动服务器


        return START_NOT_STICKY; // 服务在停止后不会自动重启
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 停止HttpServer
        if (server != null) {
            server.stop();
            Log.d("HttpService", "Server stopped");
        }
    }
}