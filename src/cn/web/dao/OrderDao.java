package cn.web.dao;

import cn.web.entity.Order;
import cn.web.entity.OrderItems;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-30
 */
public interface OrderDao {
    /**
     * 保存订单信息
     */
    void saveOrder(Order order);


    /**
     * 保存订单详情
     */
    void saveOrderItems(OrderItems orderItems);

    /**
     * 查询用户的所有订单信息
     */
    List<Order> listOrders(int uid);
}
