package kr.co.trgtech.trg02.dto;

import org.apache.ibatis.type.Alias;

@Alias("PostDto")
public class PostDto {

	private String id;
	private String blogId;
	private String title;
	private String content;
	private String createDate;
	private String updateDate;


	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
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
		return "PostDto [id=" + id + ", blogId=" + blogId + ", title=" + title + ", content=" + content
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
	
	
}