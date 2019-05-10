package com.lisang.socket.v2;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author lisang
 * @date 2019/5/10 21:51
 * @desc
 */
public class Client {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("127.0.0.1", 8888);

            //构建IO
            InputStream is = s.getInputStream();
            OutputStream os = s.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            Scanner sc = new Scanner(System.in);
            new Thread(() -> {
                while (true) {
                    String msg = null;
                    try {
                        msg = br.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(msg);
                }
            },"readThread").start();

            new Thread(() -> {
                while(true){
                    try {
                        bw.write(sc.next()+"\n");
                        bw.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            },"writeThread").start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
