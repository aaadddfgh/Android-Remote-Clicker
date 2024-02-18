package mm.pp.clicker;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

import mm.pp.clicker.service.HttpServer;
import mm.pp.clicker.tools.CommandResolver;

public class AspectTest {
    @Test
    public void Test() throws UnsupportedEncodingException {
        String s="eyJjb21tYW5kcyI6InN3aXBlIDUwIDEwIDUwIDEwMDAgMjAwO3NsZWVwIDEwMDA7c3dpcGUgNTU3IDQzNCA1NTcgMTMwMCAyMDA7c2xlZXAgMTAwMDtzd2lwZSA5NDMgNzU1IDIwMCA3NTUgMjAwO3NsZWVwIDEwMDA7Y2xpY2sgMjIwIDUxMDtzbGVlcCAxMDAwO2NsaWNrIDU0NCAyMTUyOyJ9";
        byte[] b=s.getBytes();
        HttpServer.getStringFromSessionByte(b,b.length);
    }
}
