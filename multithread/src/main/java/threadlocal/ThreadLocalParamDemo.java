/*
 * Copyright 2013-2021 Smartdot Technologies Co., Ltd. All rights reserved.
 * SMARTDOT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package threadlocal;

/**
 * <p>
 * {@link ThreadLocal}示例代码，说明{@link ThreadLocal}对于不同线程的同一个变量起到相互隔离的作用
 * @author <a href="mailto:fangzz@smartdot.com.cn">fangzizhong</a>
 * @version 1.0, 2021-03-01
 */
public class ThreadLocalParamDemo {

    public static String local_name;
    public static ThreadLocal<String> threadLocal_name = new ThreadLocal<>();



    public void setToLocalName(String value) {
        local_name = value;
    }

    public void setToThreadLocalName(String value) {
        threadLocal_name.set(value);
    }

    public String getValueFromLocal() {
        return local_name;
    }

    public String getValueFromThreadLocal() {
        return threadLocal_name.get();
    }


    public static void main(String[] args) {
        ThreadLocalParamDemo demo = new ThreadLocalParamDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.setToLocalName("thread001");
                demo.setToThreadLocalName("thread001");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread01-local:" + demo.getValueFromLocal());
                System.out.println("thread01-threadLocal:" + demo.getValueFromThreadLocal());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.setToLocalName("thread002");
                demo.setToThreadLocalName("thread002");
                System.out.println("thread02-local:" + demo.getValueFromLocal());
                System.out.println("thread02-threadLocal:" + demo.getValueFromThreadLocal());
            }
        }).start();
    }
}
