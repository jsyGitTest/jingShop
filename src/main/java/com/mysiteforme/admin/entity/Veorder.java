package com.mysiteforme.admin.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.activerecord.Model;
import com.mysiteforme.admin.base.DataEntity;

/**
 * <p>
 * 订单
 * </p>
 *
 * @author jing
 * @since 2023-02-02
 */
public class Veorder extends DataEntity<Veorder> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
	private String orderno;
    /**
     * 快递单
     */
	private String express;
    /**
     * 蔬菜ID
     */
	private String vid;
    /**
     * 蔬菜名称
     */
	private String vname;
    /**
     * 图片
     */
	private String vimg;
    /**
     * 价格
     */
	private BigDecimal vprice;
    /**
     * 数量
     */
	private Integer vcout;
    /**
     * 总价
     */
	private BigDecimal oprice;
    /**
     * 客户姓名
     */
	private String cname;
    /**
     * 电话
     */
	private String ctel;
    /**
     * 状态
     */
	private String ostatus;

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}
	public String getExpress() {
		return express;
	}

	public void setExpress(String express) {
		this.express = express;
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
	public BigDecimal getVprice() {
		return vprice;
	}

	public void setVprice(BigDecimal vprice) {
		this.vprice = vprice;
	}
	public Integer getVcout() {
		return vcout;
	}

	public void setVcout(Integer vcout) {
		this.vcout = vcout;
	}
	public BigDecimal getOprice() {
		return oprice;
	}

	public void setOprice(BigDecimal oprice) {
		this.oprice = oprice;
	}
	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCtel() {
		return ctel;
	}

	public void setCtel(String ctel) {
		this.ctel = ctel;
	}
	public String getOstatus() {
		return ostatus;
	}

	public void setOstatus(String ostatus) {
		this.ostatus = ostatus;
	}


	@Override
	public String toString() {
		return "Veorder{" +
			", orderno=" + orderno +
			", express=" + express +
			", vid=" + vid +
			", vname=" + vname +
			", vimg=" + vimg +
			", vprice=" + vprice +
			", vcout=" + vcout +
			", oprice=" + oprice +
			", cname=" + cname +
			", ctel=" + ctel +
			", ostatus=" + ostatus +
			"}";
	}
}
