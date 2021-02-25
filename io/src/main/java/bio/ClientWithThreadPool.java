package bio;/*
 * Copyright 2013-2021 Smartdot Technologies Co., Ltd. All rights reserved.
 * SMARTDOT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * <p>
 * 带线程池的客户端代码
 * @author <a href="mailto:fangzz@smartdot.com.cn">fangzizhong</a>
 * @version 1.0, 2021-02-14
 */
public class ClientWithThreadPool {

    protected final int SERVER_PORT = 8888;
    protected final String SERVER_ADDR = "127.0.0.1";

    public void init() throws IOException {
        System.out.println("client starting....");
        Socket socket = new Socket(SERVER_ADDR, SERVER_PORT);
        Scanner scanner = new Scanner(System.in);
        String msg = null;
        System.out.println("请输入:");
        while ((msg = scanner.nextLine()) != null) {
            PrintStream stream = new PrintStream(socket.getOutputStream());
            stream.println(msg);
            stream.flush();
        }
    }


    public static void main(String[] args) {
        ClientWithThreadPool client = new ClientWithThreadPool();
        try {
            client.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
