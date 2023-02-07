package com.mysiteforme.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.mysiteforme.admin.base.DataEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 蔬菜
 * </p>
 *
 * @author jing
 * @since 2023-01-17
 */
public class Vegetable extends DataEntity<Vegetable> {

    private static final long serialVersionUID = 1L;

    /**
     * 蔬菜名称
     */
	private String name;
    /**
     * 当前价格
     */
	@TableField("current_price")
	private BigDecimal currentPrice;
    /**
     * 原价
     */
	@TableField("old_price")
	private BigDecimal oldPrice;
    /**
     * 标签
     */
	private String tag;
    /**
     * 一级类目
     */
	@TableField("category_first")
	private String categoryFirst;
    /**
     * 二级类目
     */
	@TableField("category_second")
	private String categorySecond;
    /**
     * 库存
     */
	private Integer stock;
    /**
     * 图片地址
     */
	private String img;
    /**
     * 简单介绍
     */
	@TableField("vegetable_introduction")
	private String vegetableIntroduction;
    /**
     * 蔬菜描述
     */
	@TableField("vegetable_description")
	private String vegetableDescription;
    /**
     * 产地
     */
	private String producer;
    /**
     * 是否上架
     */
	private String shelf;
    /**
     * 保质期
     */
	@TableField("quality_guarantee_period")
	private Integer qualityGuaranteePeriod;
    /**
     * 批次
     */
	private String batch;
    /**
     * 种植日期
     */
	@TableField("plant_date")
	private Date plantDate;
    /**
     * 采摘日期
     */
	@TableField("pick_date")
	private Date pickDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
	}
	public BigDecimal getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(BigDecimal oldPrice) {
		this.oldPrice = oldPrice;
	}
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getCategoryFirst() {
		return categoryFirst;
	}

	public void setCategoryFirst(String categoryFirst) {
		this.categoryFirst = categoryFirst;
	}
	public String getCategorySecond() {
		return categorySecond;
	}

	public void setCategorySecond(String categorySecond) {
		this.categorySecond = categorySecond;
	}
	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	public String getVegetableIntroduction() {
		return vegetableIntroduction;
	}

	public void setVegetableIntroduction(String vegetableIntroduction) {
		this.vegetableIntroduction = vegetableIntroduction;
	}
	public String getVegetableDescription() {
		return vegetableDescription;
	}

	public void setVegetableDescription(String vegetableDescription) {
		this.vegetableDescription = vegetableDescription;
	}
	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getShelf() {
		return shelf;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}
	public Integer getQualityGuaranteePeriod() {
		return qualityGuaranteePeriod;
	}

	public void setQualityGuaranteePeriod(Integer qualityGuaranteePeriod) {
		this.qualityGuaranteePeriod = qualityGuaranteePeriod;
	}
	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}
	public Date getPlantDate() {
		return plantDate;
	}

	public void setPlantDate(Date plantDate) {
		this.plantDate = plantDate;
	}
	public Date getPickDate() {
		return pickDate;
	}

	public void setPickDate(Date pickDate) {
		this.pickDate = pickDate;
	}


	@Override
	public String toString() {
		return "Vegetable{" +
			", name=" + name +
			", currentPrice=" + currentPrice +
			", oldPrice=" + oldPrice +
			", tag=" + tag +
			", categoryFirst=" + categoryFirst +
			", categorySecond=" + categorySecond +
			", stock=" + stock +
			", img=" + img +
			", vegetableIntroduction=" + vegetableIntroduction +
			", vegetableDescription=" + vegetableDescription +
			", producer=" + producer +
			", shelf=" + shelf +
			", qualityGuaranteePeriod=" + qualityGuaranteePeriod +
			", batch=" + batch +
			", plantDate=" + plantDate +
			", pickDate=" + pickDate +
			"}";
	}
}
