package cn.web.dao.impl;

import cn.web.dao.BaseDao;
import cn.web.dao.PCDDao;
import cn.web.entity.PCD;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-30
 */
public class PCDDaoImpl extends BaseDao<PCD> implements PCDDao {
    @Override
    public List<PCD> listPCDbyPid(String pid) {
        String sql = "select id, pid, name from province_city_district where pid = ?";
        return super.getBeanList(sql, pid);
    }
}
