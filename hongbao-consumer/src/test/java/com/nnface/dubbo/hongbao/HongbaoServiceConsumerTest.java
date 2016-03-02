package com.nnface.dubbo.hongbao;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.rpc.RpcContext;
import com.nnface.dubbo.service.HongbaoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 红包服务消费类
 * Consumer开发参考文章：<a href="http://blog.csdn.net/jnqqls/article/details/44762637">CSDN参考文章链接</a>
 * xml配置文件默认放置在classpath/META-INF/spring/*.xml路径下,别弄错了.
 * 否则就用第二种启动方式,自己选择xml的存放位置
 * Created by Administrator on 2016/2/29.
 */
public class HongbaoServiceConsumerTest {
    private static final Logger logger = LoggerFactory.getLogger(HongbaoServiceConsumerTest.class);

    /**
     * 启动方式一
     * xml配置文件默认放置在project/classpath/META-INF/spring/*.xml路径下,别弄错了.
     * @param args
     */
    public static void main1(String[] args) {

        //设置dubbo使用slf4j来记录日志
        //System.setProperty("dubbo.application.logger","slf4j");

        //System.setProperty("dubbo.application.logger","log4j");

        logger.info("Hongbao Service Consumer::main=====================>begin");
        com.alibaba.dubbo.container.Main.main(args);
    }

    /**
     * 启动方式二
     * 官网文档 http://dubbo.io/User+Guide-zh.htm#UserGuide-zh-%E5%BF%AB%E9%80%9F%E5%90%AF%E5%8A%A8
     * 可以手动设置xml配置文件目录地址,更灵活
     * @param args
     * @throws Exception
     */
    public static void main(String[] args)throws Exception {
        //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"http://10.20.160.198/wiki/display/dubbo/consumer.xml"});
        ClassPathXmlApplicationContext context= new ClassPathXmlApplicationContext(new String[] {"META-INF/spring/dubbo-hongbao-consumer.xml" });
        context.start();
        //System.in.read();//阻塞用于测试看看没有调用业务方法之前消费者是否注册成功，可注释掉改代码
        HongbaoService hongbaoService = (HongbaoService)context.getBean("hongbaoService");
        // 单个测试
        //String resInfo =hongbaoService.hongbaoAlgorithm(2000,13);
        String resInfo =hongbaoService.hi("Data");
        String serverIP =RpcContext.getContext().getRemoteHost();
        int serverPort = RpcContext.getContext().getRemotePort();
        System.out.println("rspInfo:" + resInfo + ", response from provider: " + serverIP +"," + serverPort);

    }

    /**
     * 启动方式3，纯代码启动
     * @param args
     * @throws Exception
     */
    public static void main3(String[] args) throws Exception{
        // 当前应用配置
        ApplicationConfig application = new ApplicationConfig();
        application.setName("hurry-app-consumer");

    // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://192.168.0.179:2181");
        //registry.setAddress("192.168.0.179:2181");
        //registry.setUsername("aaa");
        //registry.setPassword("bbb");

        // 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

        // 引用远程服务
        ReferenceConfig<HongbaoService> reference = new ReferenceConfig<HongbaoService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
        reference.setApplication(application);
        reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
        reference.setInterface(HongbaoService.class);
        reference.setVersion("1.1");

        // 和本地bean一样使用xxxService
        HongbaoService hongbaoService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        System.in.read();//阻塞用于测试看看没有调用业务方法之前消费者是否注册成功，可注释掉改代码

        // 单个测试
        //String resInfo =hongbaoService.hongbaoAlgorithm(2000,13);
        String resInfo =hongbaoService.hi("Data");
        String serverIP =RpcContext.getContext().getRemoteHost();
        int serverPort = RpcContext.getContext().getRemotePort();
        System.out.println("rspInfo:" + resInfo + ", response from provider: " + serverIP +"," + serverPort);
    }


}
