package com.mysiteforme.admin.service.impl;

import com.mysiteforme.admin.entity.Veorder;
import com.mysiteforme.admin.dao.VeorderDao;
import com.mysiteforme.admin.service.VeorderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author jing
 * @since 2023-02-02
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class VeorderServiceImpl extends ServiceImpl<VeorderDao, Veorder> implements VeorderService {

}
