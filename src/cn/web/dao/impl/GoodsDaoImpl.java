package cn.web.dao.impl;

import cn.web.dao.BaseDao;
import cn.web.dao.GoodsDao;
import cn.web.entity.Good;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-27
 */
public class GoodsDaoImpl extends BaseDao<Good> implements GoodsDao {
    @Override
    public List<Good> listGoods() {
        String sql = "select id, name, marketprice, estoreprice, " +
                "category, num, imgurl, description from goods";
        return super.getBeanList(sql);

    }

    @Override
    public Good getGoodDetail(String id) {
        String sql = "select id, name, marketprice, estoreprice, " +
                "category, num, imgurl, description from goods " +
                "where id = ?";
        return super.getBean(sql, id);
    }
}
