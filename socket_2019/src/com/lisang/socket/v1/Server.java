package com.lisang.socket.v1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author lisang
 * @date 2019/5/10 21:38
 * @desc
 */
public class Server {
    public static void main(String[] args) {
        boolean FLAG = true;
        try {
            ServerSocket ss = new ServerSocket(8888);
            System.out.println("启动服务器....");
            while (FLAG) {
                Socket s = ss.accept();

                System.out.println("客户端:" + s.getInetAddress().getLocalHost() + "已连接到服务器");

                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                //读取客户端发送来的消息
                String mess = br.readLine();
                System.out.println("客户端：" + mess);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                bw.write(mess + "\n");
                bw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
