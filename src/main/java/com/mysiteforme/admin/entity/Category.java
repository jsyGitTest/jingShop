package com.mysiteforme.admin.entity;

import com.mysiteforme.admin.base.DataEntity;

/**
 * <p>
 * 类目表
 * </p>
 *
 * @author jing
 * @since 2023-01-17
 */
public class Category extends DataEntity<Category> {

    private static final long serialVersionUID = 1L;

    /**
     * 类目名称
     */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Category{" +
			", name=" + name +
			"}";
	}
}
