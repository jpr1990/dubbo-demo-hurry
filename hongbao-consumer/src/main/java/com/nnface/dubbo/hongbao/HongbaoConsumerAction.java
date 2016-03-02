package com.nnface.dubbo.hongbao;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.config.annotation.Reference;
import com.nnface.dubbo.service.HongbaoService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 红包服务 消费者
 * Created by Administrator on 2016/2/29.
 */
public class HongbaoConsumerAction {
    private static final Logger logger = LoggerFactory.getLogger(HongbaoConsumerAction.class);

    //@Resource
    //@Autowired
    //@Reference(version="1.1")
    private HongbaoService hongbaoService;

    /**
     *  注入实体
     * @param hongbaoService
     */
    public void setHongbaoService(HongbaoService hongbaoService) {
        this.hongbaoService = hongbaoService;
    }



    //@PostConstruct
    public void start() throws Exception {
        logger.info("Hongbao Service Consumer::start*************************************** !");

        String hi = hongbaoService.hi("HurryJiang");
        logger.info("Hongbao Service Consumer::  say hello to you :: "+hi);

        String result = hongbaoService.hongbaoAlgorithm(200, 13);
        logger.info("Hongbao Service Consumer::finished----------------------------------->hongbaoAlgorithm  return" + result);

        logger.info("Hongbao Service Consumer::end*************************************** !");
    }
}
