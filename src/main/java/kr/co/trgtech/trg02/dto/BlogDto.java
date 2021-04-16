package kr.co.trgtech.trg02.dto;

import org.apache.ibatis.type.Alias;

@Alias("BlogDto")
public class BlogDto {

	private String id;
	private String userId;
	private String blogName;
	private String createDate;
	private String updateDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBlogName() {
		return blogName;
	}
	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "BlogDto [id=" + id + ", userId=" + userId + ", blogName=" + blogName + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}
	
}