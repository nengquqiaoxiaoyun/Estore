package cn.web.service;

import cn.web.entity.OrderItems;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-12-01
 */
public interface OrderItemsService {

    /**
     * 根据订单编号查询订单详情
     */
    List<OrderItems> getOrderItemsByOid(String oid);
}
