package cn.web.service;

import cn.web.entity.Cart;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-27
 */
public interface CartService {
    /**
     * 根据uid和gid查询购物车商品的数量
     */
    Integer getCartNumByUidAndGid(Integer uid, String gid);

    /**
     * 根据uid和gid向购物车添加一条数据
     */
    void addCart(Integer uid, String gid);

    /**
     * 根据uid和gid修改商品的数量
     */
    void updateCartNum(Integer uid, String gid);

    /**
     * 根据用户情况添加或跟新购物车
     */
    public void updateCart(Integer uid, String gid);

    /**
     * 查询某个用户的购物车
     */
    List<Cart> listCartByUid(int uid);

    /**
     * 跟新购物车的商品数量，并返回小计金额等信息
     */
    String updateCartNum(Cart cart );

    /**
     * 查询某个用户的购物车
     */
    Cart listCartByGidAndUid(Cart cart);

    /**
     * 根据gid删除购物车
     * @return 总金额和节省后的金额（json）
     */
    String deleteCartByGid(int uid, String gid);
}
