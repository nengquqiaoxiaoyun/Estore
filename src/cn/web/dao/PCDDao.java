package cn.web.dao;

import cn.web.entity.PCD;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-30
 */
public interface PCDDao {

    /**
     * 根据父id查找省市区信息
     */
    List<PCD> listPCDbyPid(String pid);
}
