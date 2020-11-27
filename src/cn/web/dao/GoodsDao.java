package cn.web.dao;

import cn.web.entity.Good;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-27
 */
public interface GoodsDao {

    /**
     * 查询所有商品
     */
    List<Good> listGoods();

    /**
     * 根据id查询商品信息
     */
    Good getGoodDetail(String id);
}
