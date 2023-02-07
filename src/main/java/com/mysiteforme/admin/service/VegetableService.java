package com.mysiteforme.admin.service;

import com.baomidou.mybatisplus.service.IService;
import com.mysiteforme.admin.entity.Vegetable;

import java.util.List;

/**
 * <p>
 * 蔬菜 服务类
 * </p>
 *
 * @author jing
 * @since 2023-01-17
 */
public interface VegetableService extends IService<Vegetable> {

    List<String> findTagList();
}
