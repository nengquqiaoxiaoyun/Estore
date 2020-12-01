package cn.web.service.impl;

import cn.web.dao.CartDao;
import cn.web.dao.GoodsDao;
import cn.web.dao.OrderDao;
import cn.web.dao.impl.CartDaoImpl;
import cn.web.dao.impl.GoodsDaoImpl;
import cn.web.dao.impl.OrderDaoImpl;
import cn.web.entity.Cart;
import cn.web.entity.Order;
import cn.web.entity.OrderItems;
import cn.web.service.OrderService;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-30
 */
public class OrderServiceImpl implements OrderService {


    private OrderDao orderDao = new OrderDaoImpl();
    private CartDao cartDao = new CartDaoImpl();
    private GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public void subOrder(Order order) {
        int uid = order.getUid();
        List<Cart> carts = cartDao.listCartByUid(uid);

        saveOrder(order, carts);
        updateGoodNum(carts);
        deleteCarts(uid);
        saveOrderItems(order, carts);

    }

    @Override
    public List<Order> listOrders(int uid) {
        return orderDao.listOrders(uid);
    }

    /**
     * 保存订单信息
     *
     * @param order
     * @param carts
     */
    private void saveOrderItems(Order order, List<Cart> carts) {
        for (Cart cart : carts) {
            OrderItems orderItems = new OrderItems();
            orderItems.setGid(cart.getGid());
            orderItems.setOid(order.getId());
            orderItems.setBuynum(cart.getBuynum());
            orderDao.saveOrderItems(orderItems);
        }
    }

    /**
     * 删除指定用户的购物车
     */
    private void deleteCarts(int uid) {

        cartDao.deleteCartByUid(uid);
    }

    /**
     * 跟新库存
     */
    private void updateGoodNum(List<Cart> carts) {
        for (Cart cart : carts) {
            int buynum = cart.getBuynum();
            goodsDao.updateGoods(cart.getGid(), buynum);
        }
    }

    /**
     * 保存订单信息
     */
    private void saveOrder(Order order, List<Cart> carts) {

        double total = 0d;
        for (Cart cart : carts) {
            int buynum = cart.getBuynum();
            double estoreprice = cart.getGood().getEstoreprice();

            total += buynum * estoreprice;
        }
        order.setTotalprice(total);
        orderDao.saveOrder(order);
    }
}
