package com.mysiteforme.admin.service.impl;

import com.mysiteforme.admin.entity.MessageBoard;
import com.mysiteforme.admin.dao.MessageBoardDao;
import com.mysiteforme.admin.service.MessageBoardService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 留言板 服务实现类
 * </p>
 *
 * @author wangl
 * @since 2023-02-07
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MessageBoardServiceImpl extends ServiceImpl<MessageBoardDao, MessageBoard> implements MessageBoardService {

}
