package kr.co.trgtech.trg02.dto;

import org.apache.ibatis.type.Alias;

@Alias("FollowDto")
public class FollowDto extends PostDto{

	private String id;
	private String userId;
	private String followingId;
	private String followerId;
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
	public String getFollowingId() {
		return followingId;
	}
	public void setFollowingId(String followingId) {
		this.followingId = followingId;
	}
	public String getFollowerId() {
		return followerId;
	}
	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}
	@Override
	public String toString() {
		return "FollowDto [id=" + id + ", userId=" + userId + ", followingId=" + followingId + ", followerId="
				+ followerId + "]";
	}
}