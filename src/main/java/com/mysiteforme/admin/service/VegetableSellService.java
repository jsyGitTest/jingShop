package com.mysiteforme.admin.service;

import com.mysiteforme.admin.entity.VegetableSell;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 蔬菜销售 服务类
 * </p>
 *
 * @author jing
 * @since 2023-02-06
 */
public interface VegetableSellService extends IService<VegetableSell> {

    List<String> getViewList();

    List<String> getSellList();
}
