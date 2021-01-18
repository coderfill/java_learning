
/*
 * Copyright 2013-2021 Smartdot Technologies Co., Ltd. All rights reserved.
 * SMARTDOT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package threadpool;

import constant.ConstantInfo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务端Socket关于线程池的处理器
 * @author <a href="mailto:fangzz@smartdot.com.cn">fangzizhong</a>
 * @version 1.0, 2021-01-15
 */
public class ServerSocketThreadPoolHandler {

    private ExecutorService executorService;

    public ServerSocketThreadPoolHandler() {
        executorService = new ThreadPoolExecutor(ConstantInfo.CORE_POOL_SIZE, ConstantInfo.MAX_THREAD_NUM,
            180, TimeUnit.SECONDS, new ArrayBlockingQueue<>(ConstantInfo.WORK_QUEUE_SIZE));
    }

    /**
     * 调用线程池的execute方法执行线程
     * @param target
     */
    public void execute(Runnable target) {
        executorService.execute(target);
    }

}
