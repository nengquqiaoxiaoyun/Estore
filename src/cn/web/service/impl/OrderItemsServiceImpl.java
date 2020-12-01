package cn.web.service.impl;

import cn.web.dao.OrderItemsDao;
import cn.web.dao.impl.OrderItemsDaoImpl;
import cn.web.entity.OrderItems;
import cn.web.service.OrderItemsService;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-12-01
 */
public class OrderItemsServiceImpl implements OrderItemsService {
    private OrderItemsDao orderItemsDao = new OrderItemsDaoImpl();

    @Override
    public List<OrderItems> getOrderItemsByOid(String oid) {
        return orderItemsDao.getOrderItemsByOid(oid);
    }
}
