package cn.web.service.impl;

import cn.web.dao.CartDao;
import cn.web.dao.impl.CartDaoImpl;
import cn.web.entity.Cart;
import cn.web.service.CartService;
import cn.web.utils.JdbcUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-27
 */
public class CartServiceImpl implements CartService {

    private CartDao cartDao = new CartDaoImpl();

    @Override
    public Integer getCartNumByUidAndGid(Integer uid, String gid) {
        return cartDao.getCartNumByUidAndGid(uid, gid);
    }

    @Override
    public void addCart(Integer uid, String gid) {
        cartDao.addCart(uid, gid);
    }

    @Override
    public void updateCartNum(Integer uid, String gid) {
        cartDao.updateCartNum(uid, gid);
    }

    public void updateCart(Integer uid, String gid) {
        Integer num = getCartNumByUidAndGid(uid, gid);
        if (num == null) {
            addCart(uid, gid);
        } else {
            updateCartNum(uid, gid);
        }
    }

    @Override
    public List<Cart> listCartByUid(int uid) {
        return cartDao.listCartByUid(uid);
    }


    @Override
    public Cart listCartByGidAndUid(Cart cart) {
        return cartDao.listCartByGidAndUid(cart);
    }

    @Override
    public String deleteCartByGid(int uid, String gid) {
        cartDao.deleteCartByGid(uid, gid);

        List<Cart> carts = listCartByUid(uid);
        carts.forEach(System.out::println);
        // 购物金额
        double total = 0d;
        // 节省金额
        double savePrice = 0d;

        for (Cart ct : carts) {

            int buynum = ct.getBuynum();
            double marketprice = ct.getGood().getMarketprice();
            double estoreprice = ct.getGood().getEstoreprice();

            total += estoreprice * buynum;
            savePrice += buynum * (marketprice - estoreprice);
        }

        String json = "{\"total\":\"" + total + "\",\"savePrice\":\"" + savePrice + "\"}";
        return json;

    }

    @Override
    public String updateCartNum(Cart cart) {
        cartDao.updateCartNum(cart);

        List<Cart> carts = listCartByUid(cart.getUid());


        // 购物金额
        double total = 0d;
        // 节省金额
        double savePrice = 0d;
        // 当前商品的小计
        double totalPrice = 0d;
        for (Cart ct : carts) {

            int buynum = ct.getBuynum();
            double marketprice = ct.getGood().getMarketprice();
            double estoreprice = ct.getGood().getEstoreprice();


            // 当前商品
            if (ct.getGid() == cart.getGid()) {
                // 小计
                totalPrice = buynum * estoreprice;
            }

            total += estoreprice * buynum;
            savePrice += buynum * (marketprice - estoreprice);
        }


        String json = "{\"total\":\"" + total + "\",\"totalPrice\":\"" + totalPrice + "\",\"savePrice\":\"" + savePrice + "\"}";

        return json;


    }
}
