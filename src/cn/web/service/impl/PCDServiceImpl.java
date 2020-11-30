package cn.web.service.impl;

import cn.web.dao.PCDDao;
import cn.web.dao.impl.PCDDaoImpl;
import cn.web.entity.PCD;
import cn.web.service.PCDService;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-30
 */
public class PCDServiceImpl implements PCDService {

    private PCDDao pcdDao = new PCDDaoImpl();
    @Override
    public List<PCD> listPCDbyPid(String pid) {
        return pcdDao.listPCDbyPid(pid);
    }
}
