package cn.web.service;

import cn.web.entity.Order;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-30
 */
public interface OrderService {

    /**
     * 提交订单信息
     */
    void subOrder(Order order);

    /**
     * 查询用户的所有订单信息
     */
    List<Order> listOrders(int uid);
}
