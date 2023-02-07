package com.mysiteforme.admin.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.mysiteforme.admin.base.DataEntity;

/**
 * <p>
 * 文章
 * </p>
 *
 * @author wangl
 * @since 2023-02-06
 */
public class Blog extends DataEntity<Blog> {

    private static final long serialVersionUID = 1L;

    /**
     * 标题
     */
	private String title;
    /**
     * 图片路径
     */
	private String url;
    /**
     * 文章内容
     */
	private String content;
    /**
     * 文章标签
     */
	private String tag;
    /**
     * 文章简介
     */
	@TableField("sim_text")
	private String simText;
    /**
     * 作者
     */
	private String author;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getSimText() {
		return simText;
	}

	public void setSimText(String simText) {
		this.simText = simText;
	}
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}


	@Override
	public String toString() {
		return "Blog{" +
			", title=" + title +
			", url=" + url +
			", content=" + content +
			", tag=" + tag +
			", simText=" + simText +
			", author=" + author +
			"}";
	}
}
