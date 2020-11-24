package cn.web.service;

/**
 * @author: huakaimay
 * @since: 2020-11-24
 */
public interface UserService {

    /**
     * 查询手机号码是否存在
     */
    String findPhone(String phone);
}
