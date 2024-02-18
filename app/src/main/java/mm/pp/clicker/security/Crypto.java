package mm.pp.clicker.security;

import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import mm.pp.clicker.service.HttpServer;
import mm.pp.clicker.view.HomeView;
import mm.pp.clicker.viewmodel.HomeViewViewModel;

@Aspect
public class Crypto {


    public static String decrypt(byte[] encryptedBytes, String key) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }


    public static byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] byteArray = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            byteArray[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return byteArray;
    }

    @Pointcut("execution(* mm.pp.clicker.service.HttpServer.getStringFromSessionByte(..))")
    public void jointPoint() {}

    @AfterReturning(value = "jointPoint()",returning = "result")
    public Object decryptoData(Object result) {
        if (HomeViewViewModel.password.getValue()) {
            Log.d("aspectj", "data changed!");
            byte[] encryptedHEX = hexStringToByteArray(((HttpServer.MyString) result).str);


            try {
                ((HttpServer.MyString) result).str = decrypt(encryptedHEX, CryptoKey.key);
            } catch (Exception exception) {
                Log.e("aspectj", "", exception);
                //exception.printStackTrace();
            }
        }
        return result;
    }

}
