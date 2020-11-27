package cn.web.dao.impl;

import cn.web.dao.BaseDao;
import cn.web.dao.CategoryDao;
import cn.web.entity.Category;
import cn.web.utils.JedisUtils;
import com.alibaba.fastjson.JSON;
import com.mysql.cj.xdevapi.JsonArray;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-27
 */
public class CategoryDaoImpl extends BaseDao<Category> implements CategoryDao {
    @Override
    public String listCategory() {
        Jedis jedis = JedisUtils.getJedis();
        jedis.select(3);
        String categoryList = jedis.get("category_list");

        // redis里没有就查数据库
        if (categoryList == null) {
            String sql = "select cid, cname from category";
            List<Category> beanList = super.getBeanList(sql);
            if (beanList != null) {
                categoryList = JSON.toJSONString(beanList);
                jedis.set("category_list", categoryList);
            } else {
                categoryList = "";
            }
        }


        return categoryList;
    }
}
