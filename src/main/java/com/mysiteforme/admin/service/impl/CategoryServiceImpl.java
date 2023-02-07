package com.mysiteforme.admin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.mysiteforme.admin.dao.CategoryDao;
import com.mysiteforme.admin.entity.Category;
import com.mysiteforme.admin.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 类目表 服务实现类
 * </p>
 *
 * @author jing
 * @since 2023-01-17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, Category> implements CategoryService {

}
