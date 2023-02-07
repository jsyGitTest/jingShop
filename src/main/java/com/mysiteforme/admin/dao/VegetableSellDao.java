package com.mysiteforme.admin.dao;

import com.mysiteforme.admin.entity.VegetableSell;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 蔬菜销售 Mapper 接口
 * </p>
 *
 * @author jing
 * @since 2023-02-06
 */
public interface VegetableSellDao extends BaseMapper<VegetableSell> {

    @Select("select vid from vegetable_sell ORDER BY view_count desc limit 10")
    List<String> getViewList();

    @Select("select vid from vegetable_sell ORDER BY buy_count desc limit 10")
    List<String> getSellList();
}
