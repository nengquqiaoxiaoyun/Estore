package cn.web.service;

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
}
