
/*
 * Copyright 2013-2021 Smartdot Technologies Co., Ltd. All rights reserved.
 * SMARTDOT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package volatiledemo;

/**
 * <p>
 * volatile关键字类型的变量实现线程之间可见的示例<br>
 * 与{@link ThreadLocal}不同的是，volatile关键主要是用来保持变量在线程间的一致性
 * 而{@link ThreadLocal}则是用来保证相同变量在不同线程之间的隔离性
 * @author <a href="mailto:fangzz@smartdot.com.cn">fangzizhong</a>
 * @version 1.0, 2021-03-01
 */
public class VolatileParamDemo {

    public volatile String localName;

    public void setLocalName(String value) {
        this.localName = value;
    }

    public String getLocalName() {
        return this.localName;
    }


    /**
     * 开启两个线程，同时持有一个{@link VolatileParamDemo}对象
     * 第一个线程执行{@link VolatileParamDemo#setLocalName(String)}，第二个线程执行{@link VolatileParamDemo#getLocalName()}
     * 由于变量是通过volatile关键字修饰的，因此保证了不同线程之间的同步性，因此第二个线程获取到的值应该即为第一个线程赋予的值
     * @param args
     */
    public static void main(String[] args) {
        VolatileParamDemo demo = new VolatileParamDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.setLocalName("thread001");
                System.out.println("thread001:" + demo.getLocalName());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread002:" + demo.getLocalName());
            }
        }).start();
    }
}
