package com.mysiteforme.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.dao.VegetableDao;
import com.mysiteforme.admin.entity.Vegetable;
import com.mysiteforme.admin.service.VegetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 蔬菜 服务实现类
 * </p>
 *
 * @author jing
 * @since 2023-01-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VegetableServiceImpl extends ServiceImpl<VegetableDao, Vegetable> implements VegetableService {

    @Resource
    private VegetableDao vegetableDao;
    @Override
    public List<String> findTagList() {
        return vegetableDao.findTagList();
    }
}
