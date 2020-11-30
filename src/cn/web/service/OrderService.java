package cn.web.service;

import cn.web.entity.Order;

/**
 * @author: huakaimay
 * @since: 2020-11-30
 */
public interface OrderService {

    /**
     * 提交订单信息
     */
    void subOrder(Order order);
}
