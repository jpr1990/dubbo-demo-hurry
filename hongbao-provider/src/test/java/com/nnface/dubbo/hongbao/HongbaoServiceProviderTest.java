package com.nnface.dubbo.hongbao;

/**
 * Created by Administrator on 2016/2/29.
 */
public class HongbaoServiceProviderTest {
    public static void main(String[] args) {
//        new Thread(new Runnable() {
//            public void run() {
//                try {
//                    Thread.sleep(20000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                ProtocolConfig.destroyAll();
//            }
//        }).start();
        com.alibaba.dubbo.container.Main.main(args);
    }

}
