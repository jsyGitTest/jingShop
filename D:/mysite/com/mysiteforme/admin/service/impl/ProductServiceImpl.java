package com.mysiteforme.admin.service.impl;

import com.mysiteforme.admin.entity.Product;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 产品表 服务实现类
 * </p>
 *
 * @author jing
 * @since 2022-11-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {

}
