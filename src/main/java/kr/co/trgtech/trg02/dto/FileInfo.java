package kr.co.trgtech.trg02.dto;

import java.util.Date;

public class FileInfo {
	private String no;
	private String userId;
	private String filePath;
	private String sysFilename;
	private String orgFilename;
	private long size;
	private String ext;
	private Date createDt;
	private String refKey;
	private String division;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getSysFilename() {
		return sysFilename;
	}
	public void setSysFilename(String sysFilename) {
		this.sysFilename = sysFilename;
	}
	public String getOrgFilename() {
		return orgFilename;
	}
	public void setOrgFilename(String orgFilename) {
		this.orgFilename = orgFilename;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public Date getCreateDt() {
		return createDt;
	}
	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}
	public String getRefKey() {
		return refKey;
	}
	public void setRefKey(String refKey) {
		this.refKey = refKey;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	@Override
	public String toString() {
		return "FileInfo [no=" + no + ", userId=" + userId + ", filePath=" + filePath + ", sysFilename=" + sysFilename
				+ ", orgFilename=" + orgFilename + ", size=" + size + ", ext=" + ext + ", createDt=" + createDt
				+ ", refKey=" + refKey + ", division=" + division + "]";
	}
	
	
	
}
