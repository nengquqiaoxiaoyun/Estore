package cn.web.service.impl;

import cn.web.dao.CartDao;
import cn.web.dao.impl.CartDaoImpl;
import cn.web.service.CartService;

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
}
