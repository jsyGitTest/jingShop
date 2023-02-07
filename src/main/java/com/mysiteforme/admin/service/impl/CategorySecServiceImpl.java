package com.mysiteforme.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.dao.CategorySecDao;
import com.mysiteforme.admin.entity.CategorySec;
import com.mysiteforme.admin.service.CategorySecService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 二级类目 服务实现类
 * </p>
 *
 * @author jing
 * @since 2023-01-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CategorySecServiceImpl extends ServiceImpl<CategorySecDao, CategorySec> implements CategorySecService {

}
