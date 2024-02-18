package mm.pp.clicker.service;

import android.content.Intent;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;

import fi.iki.elonen.NanoHTTPD;
import mm.pp.clicker.dto.CommandDTO;
import mm.pp.clicker.tools.CommandResolver;

public class HttpServer extends NanoHTTPD {
    public HttpServer(int port) throws IOException {
        super(port);

    }

    public static class MyString{
        public String str;
        public MyString(String s){
            str=s;
        }
    }

    public static MyString getStringFromSessionByte(byte[] data,int len) throws UnsupportedEncodingException {
        return new MyString(new String(data, 0, len,"UTF-8"));
    }

    private static CommandDTO getDTO(IHTTPSession session) throws IOException {
        byte[] data = new byte[10000];
        int n = session.getInputStream().read(data);

        String s = getStringFromSessionByte(data,n).str;

        ObjectMapper objectMapper =new ObjectMapper();
        Log.d("", s);
        return objectMapper.readValue(s, CommandDTO.class);
    }

    @Override
    public Response serve(IHTTPSession session) {

        if(Method.POST == session.getMethod()) {
            try {
                CommandDTO command = getDTO(session);
                if(true){
                    Intent intent = new Intent("mm.pp.clicker.broadcast");
                    intent.setAction("mm.pp.clicker.broadcast");
                    intent.putExtra("commands", command.commands);
                    HttpService.getInstance().sendBroadcast(intent);
                }


                return newFixedLengthResponse("ok");
            } catch (IOException e) {
                Log.e("", "error when reading", e);
                return newFixedLengthResponse("wrong");
            }
        }
        return newFixedLengthResponse(Response.Status.OK,"","");
    }
}
