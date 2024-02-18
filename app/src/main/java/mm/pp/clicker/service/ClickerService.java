package mm.pp.clicker.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Path;

import android.graphics.Point;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;

import mm.pp.clicker.tools.Command;
import mm.pp.clicker.tools.CommandResolver;


public class ClickerService extends AccessibilityService {
    private static final String TAG = "ClickerService";


    @Override
    public void onCreate() {
        Log.i(TAG, "BindService-->onCreate()");
        super.onCreate();
        registerReceiver(receiver, new IntentFilter("mm.pp.clicker.broadcast"));
        //click(100,100);
    }

    // 只有在startService()启动Service的情况下才调用
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String s = intent.getStringExtra("commands");
            ArrayList<Command> commands;

            // 处理接收到的数据
            try {
                commands = CommandResolver.build(s);
            } catch (Exception exception) {
                Log.d(TAG, "Error when resolve command", exception);
                return;
            }
            try {
                for (Command command : commands
                ) {
                    Log.d(TAG, "action "+ LocalTime.now().toString());
                    switch (Command.Actions.getAction(command.action)) {
                        case CLICK:
                            click(command.pos.get(0).x, command.pos.get(0).y);
                            break;

                        case SLEEP:
                            Thread.sleep(command.duration);
                            break;

                        case SWIPE:
                            swipe(command.pos.get(0).x,
                                    command.pos.get(0).y,
                                    command.pos.get(1).x,
                                    command.pos.get(1).y,
                                    command.duration);
                            break;
                        default:
                            Log.e(TAG, "Not vaild command");
                            throw new Exception("");

                    }
                }
            } catch (Exception e) {
                Log.e(TAG, "", e);
            }
        }
    };


    //Service被启动时调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Log.i(TAG, "onStartCommand方法被调用!");
        return super.onStartCommand(intent, flags, startId);
    }

    //Service被关闭之前回调
    @Override
    public void onDestroy() {
        //Log.i(TAG, "BindService-->onDestroy()");
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        //Log.i(TAG, "BindService-->onUnbind()");
        return super.onUnbind(intent);
    }


    // 模拟点击
    public void click(float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
        gestureBuilder.addStroke(new GestureDescription.StrokeDescription(path, 0, 50));
        GestureDescription description = gestureBuilder.build();
        dispatchGesture(description, new GestureResultCallback() {

            @Override
            //如果滑动成功，会回调如下函数，可以在下面记录是否滑动成功，滑动成功或失败都要关闭该路径笔画
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                Log.d(TAG, "click  success "+ LocalDateTime.now().toString());
                path.close();
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
                Log.d(TAG, " click  fail.");
                path.close();
            }
        }, null);
    }

    // 模拟长按
    public void longClick(float x, float y, long duration) {
        Path path = new Path();
        path.moveTo(x, y);
        GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
        gestureBuilder.addStroke(new GestureDescription.StrokeDescription(path, 0, duration));

    }

    // 模拟拖动
    public void swipe(float startX, float startY, float endX, float endY, long duration) {
        Path path = new Path();
        path.moveTo(startX, startY);
        path.lineTo(endX, endY);
        GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
        gestureBuilder.addStroke(new GestureDescription.StrokeDescription(path, 0, duration));
        dispatchGesture(gestureBuilder.build(), new GestureResultCallback() {

            @Override
            //如果滑动成功，会回调如下函数，可以在下面记录是否滑动成功，滑动成功或失败都要关闭该路径笔画
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
                Log.d(TAG, "swipe  success "+ LocalTime.now().toString());
                path.close();
            }

            @Override
            public void onCancelled(GestureDescription gestureDescription) {
                super.onCancelled(gestureDescription);
                Log.d(TAG, " swipe  fail.");
                path.close();
            }
        }, null);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {

    }


    public static boolean isAccessibilityServiceSettingOn(Context mContext) {
        int accessibilityEnabled = 0;
        final String service = mContext.getPackageName() + "/" + ClickerService.class.getCanonicalName();
        Log.d(TAG, service);
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                    mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ACCESSIBILITY_ENABLED
            );
            Log.d(TAG, "accessibilityEnabled = " + accessibilityEnabled);
        } catch (Settings.SettingNotFoundException e) {
            Log.e(TAG, "Error finding setting, default accessibility to not found: "
                    + e.getMessage());
        }
        TextUtils.SimpleStringSplitter mStringColonSplitter = new TextUtils.SimpleStringSplitter(':');

        if (accessibilityEnabled == 1) {
            Log.d(TAG, "***ACCESSIBILITY IS ENABLED*** -----------------");
            String settingValue = Settings.Secure.getString(
                    mContext.getApplicationContext().getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (settingValue != null) {
                mStringColonSplitter.setString(settingValue);
                while (mStringColonSplitter.hasNext()) {
                    String accessibilityService = mStringColonSplitter.next();
                    Log.d(TAG, "-------------- > accessibilityService :: " + accessibilityService + " " + service);
                    if (accessibilityService.equalsIgnoreCase(service)) {
                        Log.d(TAG, "We've found the correct setting - accessibility is switched on!");
                        return true;
                    }
                }

            }
        } else {
            Log.d(TAG, "***ACCESSIBILITY IS DISABLED***");
        }
        return false;
    }

    @Override
    public void onInterrupt() {

    }
}
