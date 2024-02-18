//package mm.pp.clicker;
//
//import android.content.Context;
//import android.content.Intent;
//
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.platform.app.InstrumentationRegistry;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import mm.pp.clicker.service.ClickerService;
//
//@RunWith(AndroidJUnit4.class)
//public class ClickTest {
//    @Test
//    public void test(){
//        Context app=InstrumentationRegistry.getInstrumentation().getTargetContext();
//        Intent accintent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), ClickerService.class );
//        app.startService(accintent);
//
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        app.stopService(accintent);
//    }
//}
