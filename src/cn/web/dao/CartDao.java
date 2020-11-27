package cn.web.dao;

/**
 * @author: huakaimay
 * @since: 2020-11-27
 */
public interface CartDao {
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
}
