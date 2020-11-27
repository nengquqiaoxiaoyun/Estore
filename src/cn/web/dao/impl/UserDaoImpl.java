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

    @Override
    public void register(User user) {
        String sql = "insert into user (email, nickname, password, phone) values (?, ?, ?, ?)";
        super.update(sql, user.getEmail(), user.getNickname(), user.getPassword(), user.getPhone());
    }

    @Override
    public User findUser(String phone, String password) {
        String sql = "select id, phone, email, nickname, password from user where phone = ? and password = ?";
        return super.getBean(sql, phone, password);
    }
}
