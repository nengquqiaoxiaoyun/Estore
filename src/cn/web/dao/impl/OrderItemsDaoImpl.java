package cn.web.dao.impl;

import cn.web.dao.BaseDao;
import cn.web.dao.OrderItemsDao;
import cn.web.entity.Good;
import cn.web.entity.OrderItems;
import cn.web.utils.JdbcUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: huakaimay
 * @since: 2020-12-01
 */
public class OrderItemsDaoImpl extends BaseDao<OrderItems> implements OrderItemsDao {

    @Override
    public List<OrderItems> getOrderItemsByOid(String oid) {
        String sql = "SELECT oid, gid, buynum, id, name, marketprice, estoreprice " +
                "FROM orderitems, goods WHERE oid = ? AND gid = id";
        List<OrderItems> orderItemsList = new ArrayList<OrderItems>();
        QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
        try {
            List<Map<String, Object>> query = queryRunner.query(sql, new MapListHandler(), oid);
            for (Map<String, Object> map : query) {
                OrderItems orderItems = new OrderItems();
                Good good = new Good();
                BeanUtils.populate(orderItems, map);
                BeanUtils.populate(good, map);
                orderItems.setGood(good);
                orderItemsList.add(orderItems);
            }
        } catch (Exception throwables) {
            throw new RuntimeException(throwables);
        }

        return orderItemsList;
    }

    @Override
    public void delete(String oid) {
        String sql = "delete from orderitems where oid = ?";
        super.update(sql, oid);
    }
}
