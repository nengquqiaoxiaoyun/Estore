package cn.web.service.impl;

import cn.web.dao.GoodsDao;
import cn.web.dao.impl.GoodsDaoImpl;
import cn.web.entity.Good;
import cn.web.service.GoodsService;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-27
 */
public class GoodsServiceImpl implements GoodsService {
    private GoodsDao goodsDao = new GoodsDaoImpl();

    @Override
    public List<Good> listGoods() {
        return goodsDao.listGoods();
    }

    @Override
    public Good getGoodDetail(String id) {
        return goodsDao.getGoodDetail(id);
    }
}
