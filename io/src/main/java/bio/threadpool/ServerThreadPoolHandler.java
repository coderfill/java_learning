/*
 * Copyright 2013-2021 Smartdot Technologies Co., Ltd. All rights reserved.
 * SMARTDOT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package bio.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务端线程池处理类代码
 * @author <a href="mailto:fangzz@smartdot.com.cn">fangzizhong</a>
 * @version 1.0, 2021-02-13
 */
public class ServerThreadPoolHandler {

    protected ThreadPoolExecutor executor;

    public ServerThreadPoolHandler(int corePoolSize, int maximumPoolSize, long keepAliveTime, int workQueueSize) {
        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(workQueueSize));
    }

    /**
     * 线程池执行方法入口
     * @param target
     */
    public void execute(Runnable target) {
        executor.execute(target);
    }
}
