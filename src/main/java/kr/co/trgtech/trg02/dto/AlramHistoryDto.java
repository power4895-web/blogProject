package kr.co.trgtech.trg02.dto;

import org.apache.ibatis.type.Alias;

@Alias("AlramHistoryDto")
public class AlramHistoryDto {

	private String id;
	private String sendingId;
	private String getingId;
	private String content;
	private String createDate;
	private String checkDate;
	private String deleteDate;
	private String count;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSendingId() {
		return sendingId;
	}
	public void setSendingId(String sendingId) {
		this.sendingId = sendingId;
	}
	public String getGetingId() {
		return getingId;
	}
	public void setGetingId(String getingId) {
		this.getingId = getingId;
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
	public String getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}
	public String getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(String deleteDate) {
		this.deleteDate = deleteDate;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}

	
	
}