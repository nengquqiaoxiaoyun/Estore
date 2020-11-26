package cn.web.dao;

import cn.web.entity.User;

/**
 * @author: huakaimay
 * @since: 2020-11-24
 */
public interface UserDao {

    /**
     * 查询手机号码是否存在
     */
    String findPhone(String phone);

    /**
     * 注册用户
     */
    void register(User user);

    /**
     * 根据用户名和密码查找用户是否存在
     */
    User findUser(String phone, String password);
}
