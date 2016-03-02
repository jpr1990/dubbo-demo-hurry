package com.nnface.dubbo.service;

/**
 * 红包Service
 * Created by Administrator on 2016/2/29.
 */
public interface  HongbaoService {
    /**
     * 打招呼,TEST
     * @param name
     * @return
     */
    public String  hi(String name);
    /**
     * 红包分配算法
     * @param money 总红包金额
     * @param number 需要分为多少个红包
     * @return
     */
    public String hongbaoAlgorithm(long money,int number);
}
