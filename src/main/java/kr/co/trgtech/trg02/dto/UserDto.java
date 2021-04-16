package kr.co.trgtech.trg02.dto;

import org.apache.ibatis.type.Alias;

@Alias("userDto")
public class UserDto {

	private String id;
	private String loginId;
	private String password;
	private String createDate;
	private String modifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", loginId=" + loginId + ", password=" + password + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + "]";
	}

}
