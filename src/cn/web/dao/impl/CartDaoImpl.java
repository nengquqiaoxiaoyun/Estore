package cn.web.dao.impl;

import cn.web.dao.BaseDao;
import cn.web.dao.CartDao;
import cn.web.entity.Cart;
import cn.web.entity.Good;
import cn.web.utils.JdbcUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<Cart> listCartByUid(int uid) {
        String sql = "SELECT uid, gid, buynum, g.name, g.marketprice, g.estoreprice, g.imgurl " +
                "FROM cart, goods AS g WHERE cart.gid = g.id AND uid = ?";
        List<Cart> cartList = new ArrayList<Cart>();
        MapListHandler mapListHandler = new MapListHandler();
        try {
            QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
            List<Map<String, Object>> list = queryRunner.query(sql, mapListHandler, uid);
            if (list != null) {

                for (Map<String, Object> map : list) {
                    Cart cart = new Cart();
                    Good good = new Good();
                    BeanUtils.populate(cart, map);
                    BeanUtils.populate(good, map);
                    cart.setGood(good);
                    cartList.add(cart);
                }
                return cartList;
            }


        } catch (Exception throwables) {
            throw new RuntimeException(throwables);
        }

        return null;
    }

    @Override
    public void updateCartNum(Cart cart) {
        String sql = "update cart set buynum = ? where uid = ? and gid = ?";
        super.update(sql, cart.getBuynum(), cart.getUid(), cart.getGid());
        JdbcUtils.commit();
    }

    @Override
    public Cart listCartByGidAndUid(Cart cart) {
        String sql = "SELECT uid, gid, buynum, g.name, g.marketprice, g.estoreprice, g.imgurl " +
                "FROM cart, goods AS g WHERE cart.gid = g.id AND uid = ? and gid = ?";
        return super.getBean(sql, cart.getUid(), cart.getGid());
    }

    @Override
    public void deleteCartByGid(int uid, String gid) {
        String sql = "delete from cart where gid = ? and uid = ?";
        super.update(sql, gid, uid);
        JdbcUtils.commit();
    }

    @Override
    public void deleteCartByUid(int uid) {
        String sql = "delete from cart where uid = ?";
        super.update(sql, uid);
    }
}
