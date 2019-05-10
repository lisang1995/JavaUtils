package com.lisang.socket.v1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lisang
 * @date 2019/5/10 21:38
 * @desc
 */
public class Client {
    public static void main(String[] args) {
        Socket s = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            s = new Socket("127.0.0.1",8888);
            //构建IO
            is = s.getInputStream();
            os = s.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            bw.write("测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
            bw.flush();

            //服务器返回消息
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String ret = br.readLine();
            System.out.println(ret);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                //is.close();
            }
        }
    }
}
