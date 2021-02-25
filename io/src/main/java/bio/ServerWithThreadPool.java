package bio;/*
 * Copyright 2013-2021 Smartdot Technologies Co., Ltd. All rights reserved.
 * SMARTDOT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */


import bio.threadpool.ServerThreadHandler;
import bio.threadpool.ServerThreadPoolHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>
 * 带线程池的传统bio下的socket服务端代码
 * @author <a href="mailto:fangzz@smartdot.com.cn">fangzizhong</a>
 * @version 1.0, 2021-02-13
 */
public class ServerWithThreadPool {

    protected int port = 8888;

    public void init() throws IOException {
        ServerSocket server = new ServerSocket(port);
        Socket socket = server.accept();
        ServerThreadPoolHandler threadPoolHandler = new ServerThreadPoolHandler(3, 10, 180, 10);
        Thread task = new Thread(new ServerThreadHandler(socket));
        threadPoolHandler.execute(task);
    }

    public static void main(String[] args) throws IOException {
        ServerWithThreadPool server = new ServerWithThreadPool();
        server.init();
    }
}
