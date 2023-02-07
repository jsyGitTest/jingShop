package com.mysiteforme.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.DataEntity;

/**
 * <p>
 * 蔬菜销售
 * </p>
 *
 * @author jing
 * @since 2023-02-06
 */
@TableName("vegetable_sell")
public class VegetableSell extends DataEntity<VegetableSell> {

    private static final long serialVersionUID = 1L;

    /**
     * 蔬菜id
     */
	private String vid;
    /**
     * 蔬菜名
     */
	private String vname;
    /**
     * 浏览次数
     */
	@TableField("view_count")
	private Integer viewCount;
    /**
     * 购买次数
     */
	@TableField("buy_count")
	private Integer buyCount;

	public String getVid() {
		return vid;
	}

	public void setVid(String vid) {
		this.vid = vid;
	}
	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}
	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}


	@Override
	public String toString() {
		return "VegetableSell{" +
			", vid=" + vid +
			", vname=" + vname +
			", viewCount=" + viewCount +
			", buyCount=" + buyCount +
			"}";
	}
}
