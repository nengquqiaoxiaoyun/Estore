package cn.web.dao.impl;

import cn.web.dao.BaseDao;
import cn.web.dao.UserDao;
import cn.web.entity.User;

/**
 * @author: huakaimay
 * @since: 2020-11-24
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {
    @Override
    public String findPhone(String phone) {
        String sql = "select phone from user where phone = ?";
        String ph = (String) super.genericSelect(sql, phone);
        return ph;
    }
}
