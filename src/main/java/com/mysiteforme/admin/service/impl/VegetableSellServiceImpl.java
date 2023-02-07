package com.mysiteforme.admin.service.impl;

import com.mysiteforme.admin.entity.VegetableSell;
import com.mysiteforme.admin.dao.VegetableSellDao;
import com.mysiteforme.admin.service.VegetableSellService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 蔬菜销售 服务实现类
 * </p>
 *
 * @author jing
 * @since 2023-02-06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VegetableSellServiceImpl extends ServiceImpl<VegetableSellDao, VegetableSell> implements VegetableSellService {

    @Resource
    private VegetableSellDao vegetableSellDao;
    @Override
    public List<String> getViewList() {
        return vegetableSellDao.getViewList();
    }

    @Override
    public List<String> getSellList() {
        return vegetableSellDao.getSellList();
    }
}
