package com.rosejames.picdemo.engine;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by rosejames on 16/6/8.
 */
public class HttpRequest {
    //网络连接超时
    private static final int CONNECT_TIME_OUT = 30 * 1000;
    //网络读取超时
    private static final int READ_TIME_OUT = 30 * 1000;

    public static void request(String path, HttpCallback httpCallback) {
        HttpURLConnection conn = null;
        BufferedInputStream bin = null;
        try {
            byte[] result;
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(CONNECT_TIME_OUT);
            conn.setReadTimeout(READ_TIME_OUT);
            InputStream in = conn.getInputStream();
            bin = new BufferedInputStream(in);
            ByteArrayOutputStream bAos = new ByteArrayOutputStream();
            BufferedOutputStream buf = new BufferedOutputStream(bAos);
            //数据缓冲区
            byte[] buffer = new byte[1024];
            int size;
            while ((size = bin.read(buffer)) != -1)
                buf.write(buffer, 0, size);
            buf.flush();
            buf.close();
            bin.close();
            in.close();
            bAos.flush();
            result = bAos.toByteArray();
            bAos.close();
            if (result != null) {
                httpCallback.callback(true, new String(result));
            } else {
                httpCallback.callback(false, "");
            }
            conn.disconnect();
        } catch (Throwable e) {
            e.printStackTrace();
            httpCallback.callback(false, "");
        } finally {
            try {
                if (bin != null) {
                    bin.close();
                }
            } catch (Exception e) {
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
    }


    public interface HttpCallback {
        void callback(boolean isSuc, String str);
    }

}
