package cn.web.dao.impl;

import cn.web.dao.BaseDao;
import cn.web.dao.OrderDao;
import cn.web.entity.Order;
import cn.web.entity.OrderItems;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-30
 */
public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
    @Override
    public void saveOrder(Order order) {
        String sql = "insert into orders (id, uid, totalprice, address, status, createtime) " +
                "values (?, ?, ?, ?, ?, ?)";
        super.update(sql, order.getId(), order.getUid(),
                order.getTotalprice(), order.getAddress(), order.getStatus(), order.getCreatetime());
    }

    @Override
    public void saveOrderItems(OrderItems orderItems) {
        String sql = "insert into orderitems (oid, gid, buynum) values (?, ?, ?)";
        super.update(sql,orderItems.getOid(), orderItems.getGid(), orderItems.getBuynum());
    }

    @Override
    public List<Order> listOrders(int uid) {
        String sql = "select id, uid, totalprice, address, status, createtime from orders " +
                "where uid = ?";
        return super.getBeanList(sql, uid);
    }

}
