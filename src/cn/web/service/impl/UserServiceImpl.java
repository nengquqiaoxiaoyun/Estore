package cn.web.service.impl;

import cn.web.dao.UserDao;
import cn.web.dao.impl.UserDaoImpl;
import cn.web.entity.User;
import cn.web.service.UserService;
import com.alibaba.fastjson.JSON;

/**
 * @author: huakaimay
 * @since: 2020-11-24
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public String findPhone(String phone) {
        String ph = userDao.findPhone(phone);
        String str = JSON.toJSONString("notExist");
        if (ph != null) {
            str = JSON.toJSONString("exist");
        }
        return str;
    }

    @Override
    public void register(User user) {
        userDao.register(user);
    }

    @Override
    public User findUser(String phone, String password) {
        return userDao.findUser(phone, password);
    }
}
