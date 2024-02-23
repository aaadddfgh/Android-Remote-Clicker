package mm.pp.clicker.logger;


import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import mm.pp.clicker.security.CryptoKey;
import mm.pp.clicker.service.HttpServer;
import mm.pp.clicker.viewmodel.HomeViewViewModel;

@Aspect
public class ServiceLogger {
    private static final String TAG  = "mm.pp.clicker.service";

    @Pointcut("execution(* mm.pp.clicker.service.**.**(..))")
    public void jointPoint() {}

    @Before(value = "jointPoint()")
    public void logD(JoinPoint joinPoint) {
        Log.d(TAG,  joinPoint.getSignature().getName()+" "+joinPoint.getArgs().toString());
        return;
    }
}
