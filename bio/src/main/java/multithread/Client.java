
/*
 * Copyright 2013-2021 Smartdot Technologies Co., Ltd. All rights reserved.
 * SMARTDOT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package multithread;

import constant.ConstantInfo;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * <p>
 *
 * @author <a href="mailto:fangzz@smartdot.com.cn">fangzizhong</a>
 * @version 1.0, 2021-01-15
 */
public class Client {

    public void init() {
        try {
            System.out.println("tranditional server init....");
            // 定义客户端的socket
            Socket socket = new Socket(ConstantInfo.SERVER_ADDRESS, ConstantInfo.SERVER_PORT);
            Scanner scanner = new Scanner(System.in);
            String msg;
            System.out.println("请输入：");
            while ((msg = scanner.nextLine()) != null) {
                PrintStream ps = new PrintStream(socket.getOutputStream());
                ps.println(msg);
                ps.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
