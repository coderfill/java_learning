/*
 * Copyright 2013-2021 Smartdot Technologies Co., Ltd. All rights reserved.
 * SMARTDOT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>
 * 基于nio非阻塞式的群聊服务端的代码实现
 * @author <a href="mailto:fangzz@smartdot.com.cn">fangzizhong</a>
 * @version 1.0, 2021-02-25
 */
public class MultiChatServer {

    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private final static int PORT = 9999;

    private volatile boolean stop;


    /**
     * 通过构造函数，初始化需要的变量参数
     * <pre>
     *     通过{@link ServerSocketChannel#open()}创建服务端的channel
     *     绑定服务端的地址以及端口并设定服务端为非阻塞试通信
     *     通过{@link Selector#open()}创建多路复用选择器
     * </pre>
     */
    public MultiChatServer() {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动服务端
     */
    public void startAndListen() {
        System.out.println("Server starting.....");
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                System.out.println("selectionKeys:" + selectionKeys.size());
                while (selectionKeys.size() > 0) {
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        handle(key);
                        iterator.remove();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    protected void handle(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                /**
                 * 处理新接入的连接请求
                 */
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel channel = ssc.accept();
                channel.configureBlocking(false);
                channel.register(selector, SelectionKey.OP_READ);
            } else if (key.isReadable()) {
                /**
                 * 处理客户端的传输过来的数据
                 */
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int count = sc.read(buffer);
                if (count > 0) {
                    buffer.flip();
                    String msg = new String(buffer.array(), 0, buffer.remaining());
                    System.out.println("client msg:" + msg);
                }
            }
        }
    }

    public static void main(String[] args) {
        new MultiChatServer().startAndListen();
    }
}
