package com.mysiteforme.admin.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mysiteforme.admin.base.DataEntity;

/**
 * <p>
 * 留言板
 * </p>
 *
 * @author wangl
 * @since 2023-02-07
 */
@TableName("message_board")
public class MessageBoard extends DataEntity<MessageBoard> {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
	private String name;
    /**
     * 邮箱
     */
	private String email;
    /**
     * 标题
     */
	private String title;
    /**
     * 留言内容
     */
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


	@Override
	public String toString() {
		return "MessageBoard{" +
			", name=" + name +
			", email=" + email +
			", title=" + title +
			", content=" + content +
			"}";
	}
}
