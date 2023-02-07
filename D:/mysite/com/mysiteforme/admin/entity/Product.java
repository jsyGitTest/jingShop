package com.mysiteforme.admin.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.mysiteforme.admin.base.DataEntity;

/**
 * <p>
 * 产品表
 * </p>
 *
 * @author jing
 * @since 2022-11-09
 */
public class Product extends DataEntity<Product> {

    private static final long serialVersionUID = 1L;

    /**
     * 产品名称
     */
	private String name;
    /**
     * 是否参加促销
     */
	@TableField("is_cuxiao")
	private Integer isCuxiao;
    /**
     * 产品来源
     */
	private Integer source;
    /**
     * 状态
     */
	@TableField("p_status")
	private Boolean pStatus;
    /**
     * 产品描述
     */
	private String description;
    /**
     * 上架时间
     */
	@TableField("create_time")
	private Date createTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Integer getIsCuxiao() {
		return isCuxiao;
	}

	public void setIsCuxiao(Integer isCuxiao) {
		this.isCuxiao = isCuxiao;
	}
	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}
	public Boolean getpStatus() {
		return pStatus;
	}

	public void setpStatus(Boolean pStatus) {
		this.pStatus = pStatus;
	}
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	@Override
	public String toString() {
		return "Product{" +
			", name=" + name +
			", isCuxiao=" + isCuxiao +
			", source=" + source +
			", pStatus=" + pStatus +
			", description=" + description +
			", createTime=" + createTime +
			"}";
	}
}
