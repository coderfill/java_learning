/*
 * Copyright 2013-2021 Smartdot Technologies Co., Ltd. All rights reserved.
 * SMARTDOT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package bio.threadpool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * <p>
 * 服务端线程处理器代码
 * <pre>
 *     定义一个带缓冲的Reader用来接收{@link Socket}中的{@link InputStream}对象
 *     定义一个{@link PrintWriter}用来输出接收的{@link InputStream}内容
 * </pre>
 * @author <a href="mailto:fangzz@smartdot.com.cn">fangzizhong</a>
 * @version 1.0, 2021-02-13
 */
public class ServerThreadHandler implements Runnable{

    protected Socket socket;

    public ServerThreadHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        try (InputStream inputStream = socket.getInputStream()) {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String msg;
            while ((msg = reader.readLine()) != null) {
                System.out.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                System.out.println("关闭 Socket....");
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
