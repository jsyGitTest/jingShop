package com.mysiteforme.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.Vegetable;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 蔬菜 Mapper 接口
 * </p>
 *
 * @author jing
 * @since 2023-01-17
 */
public interface VegetableDao extends BaseMapper<Vegetable> {

    @Select("select tag from vegetable group by tag limit 7")
    List<String> findTagList();
}
