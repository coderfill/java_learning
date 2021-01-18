
/*
 * Copyright 2013-2021 Smartdot Technologies Co., Ltd. All rights reserved.
 * SMARTDOT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package threadpool;

import constant.ConstantInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>
 * 使用线程池下的Socket服务端
 * @author <a href="mailto:fangzz@smartdot.com.cn">fangzizhong</a>
 * @version 1.0, 2021-01-15
 */
public class Server {


    public void init() {
        try {
            System.out.println("threadpool server init....");
            ServerSocket server = new ServerSocket(ConstantInfo.SERVER_PORT);
            while (true) {
                // 阻塞式监听，获取服务端的socket
                Socket socket = server.accept();
                new Thread(() -> {
                    try {
                        // 获取socket中的输入流，并且包装成待缓冲的BufferedReader
                        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String msg;
                        while ((msg = br.readLine()) != null) {
                            // 如果读取到数据，则输出至控制台
                            System.out.println(msg);
                        }
                    } catch (Exception e) {

                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
