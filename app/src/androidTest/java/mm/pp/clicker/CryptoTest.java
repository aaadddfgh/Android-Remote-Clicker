package mm.pp.clicker;


import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;

import mm.pp.clicker.security.Crypto;

@RunWith(AndroidJUnit4.class)
public class CryptoTest {
    @Test
    public void test(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String data= "46695cf4be3736d26d84f1ec69e0082d";
        Crypto crypto=new Crypto();
        crypto.decryptoData(data);
    }
}
