package com.mysiteforme.admin.service.impl;

import com.mysiteforme.admin.entity.Blog;
import com.mysiteforme.admin.dao.BlogDao;
import com.mysiteforme.admin.service.BlogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 文章 服务实现类
 * </p>
 *
 * @author wangl
 * @since 2023-02-06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BlogServiceImpl extends ServiceImpl<BlogDao, Blog> implements BlogService {

}
