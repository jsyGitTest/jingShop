package com.mysiteforme.admin.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.DataEntity;

/**
 * <p>
 * 二级类目
 * </p>
 *
 * @author jing
 * @since 2023-01-17
 */
@TableName("category_sec")
public class CategorySec extends DataEntity<CategorySec> {

    private static final long serialVersionUID = 1L;

    /**
     * 二级类目名称
     */
	private String name;
    /**
     * 一级类目
     */
	private String cid;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}


	@Override
	public String toString() {
		return "CategorySec{" +
			", name=" + name +
			", cid=" + cid +
			"}";
	}
}
