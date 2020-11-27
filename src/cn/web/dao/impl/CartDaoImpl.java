package cn.web.dao.impl;

import cn.web.dao.BaseDao;
import cn.web.dao.CartDao;
import cn.web.entity.Cart;

/**
 * @author: huakaimay
 * @since: 2020-11-27
 */
public class CartDaoImpl extends BaseDao<Cart> implements CartDao {

    @Override
    public Integer getCartNumByUidAndGid(Integer uid, String gid) {
        String sql = "select uid, gid, buynum from cart where uid = ? and gid = ?";
        Integer num = (Integer) super.genericSelect(sql, uid, gid);
        return num;
    }

    @Override
    public void addCart(Integer uid, String gid) {
        String sql = "insert into cart (uid, gid, buynum) values (?, ?, 1)";
        super.update(sql, uid, gid);
    }

    @Override
    public void updateCartNum(Integer uid, String gid) {
        String sql = "update cart set buynum = buynum + 1 where uid = ? and gid = ?";
        super.update(sql, uid, gid);
    }
}
