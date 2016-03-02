package com.nnface.dubbo.service;

/**
 * 商品秒杀服务
 * Created by Administrator on 2016/2/29.
 */
public interface SeckillService {
    /**
     * 秒杀商品
     * @param uid 用户ID
     * @param pid 商品ID
     */
    public void seckillProduct(String uid,String pid);
}
