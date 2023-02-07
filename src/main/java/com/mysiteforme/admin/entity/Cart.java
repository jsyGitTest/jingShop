package com.mysiteforme.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.mysiteforme.admin.base.DataEntity;

import java.math.BigDecimal;

/**
 * <p>
 * 购物车
 * </p>
 *
 * @author jing
 * @since 2023-01-31
 */
public class Cart extends DataEntity<Cart> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
	@TableField("customer_id")
	private String customerId;
    /**
     * 蔬菜ID
     */
	private String vid;
    /**
     * 蔬菜名称
     */
	private String vname;
	private BigDecimal vprice;
    /**
     * 蔬菜图片
     */
	private String vimg;
    /**
     * 数量
     */
	private Integer vcount;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
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
	public String getVimg() {
		return vimg;
	}

	public void setVimg(String vimg) {
		this.vimg = vimg;
	}
	public Integer getVcount() {
		return vcount;
	}

	public void setVcount(Integer vcount) {
		this.vcount = vcount;
	}

	public BigDecimal getVprice() {
		return vprice;
	}

	public void setVprice(BigDecimal vprice) {
		this.vprice = vprice;
	}

	@Override
	public String toString() {
		return "Cart{" +
			", customerId=" + customerId +
			", vid=" + vid +
			", vname=" + vname +
			", vimg=" + vimg +
			", vcount=" + vcount +
			"}";
	}
}
