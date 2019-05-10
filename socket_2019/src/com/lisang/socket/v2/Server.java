package com.lisang.socket.v2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author lisang
 * @date 2019/5/10 21:51
 * @desc
 */
public class Server {
    public static void main(String[] args) {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Socket s = ss.accept();
                System.out.println("客户端:"+s.getInetAddress().getLocalHost()+"已连接到服务器");
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                //读取客户端发送来的消息
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                bw.write("你已和服务器建立通信，现在进入你的请求处理线程"+"\n");
                bw.flush();
                Scanner sc  = new Scanner(System.in);
                new Thread(() -> {
                    while (true) {
                        try {
                            String msg = br.readLine();
                            System.out.println(msg);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },"readThread").start();
                new Thread(() -> {
                    while (true) {
                        try {
                            bw.write(sc.next() + "\n");
                            bw.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },"writeThread").start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
