package cn.web.dao;

import cn.web.entity.OrderItems;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-12-01
 */
public interface OrderItemsDao {

    /**
     * 根据订单编号查询订单详情
     */
    List<OrderItems> getOrderItemsByOid(String oid);

    /**
     * 根据订单号删除详情信息
     */
    void delete(String oid);
}
