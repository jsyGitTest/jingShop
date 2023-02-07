package com.mysiteforme.admin.service.impl;

import com.mysiteforme.admin.entity.Cart;
import com.mysiteforme.admin.dao.CartDao;
import com.mysiteforme.admin.service.CartService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author jing
 * @since 2023-01-31
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CartServiceImpl extends ServiceImpl<CartDao, Cart> implements CartService {

}
