package com.mysiteforme.admin.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.mysiteforme.admin.entity.Menu;
import com.mysiteforme.admin.entity.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.awt.*;
import java.util.Set;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author jing
 * @since 2017-10-31
 */
public interface RoleDao extends BaseMapper<Role> {

    Role selectRoleById(@Param("id") Long id);

    void saveRoleMenus(@Param("roleId") Long id, @Param("menus") Set<Menu> menus);

    void dropRoleMenus(@Param("roleId")Long roleId);

    void dropRoleUsers(@Param("roleId")Long roleId);

    @Select("INSERT INTO sys_user_role(`user_id`, `role_id`) VALUES (${id}, 5)")
    void addRole(@Param("id")Long id);
}