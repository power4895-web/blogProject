package kr.co.trgtech.trg02.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trgtech.trg02.dto.FollowDto;
import kr.co.trgtech.trg02.dto.PostDto;

@Mapper
public interface FollowMapper {
	
	public int followingCount(FollowDto followDto);
	public int followerCount(FollowDto followDto);
	public List<FollowDto> FindAllByFollowing(FollowDto followDto);
	public List<FollowDto> FindAllByFollower(FollowDto followDto);
	public List<FollowDto> FindContentByFollowingId(FollowDto followDto);
	public FollowDto getPostDetailFollowerIdByLoginId(FollowDto followDto);
	public String followCount(String id, String followerId);
	public int insertFollowing(FollowDto followDto);

	public int deleteFollowing(FollowDto followDto);
	public int deleteFollowerByMe(FollowDto followDto);
	public List<FollowDto> infiniteScrollDown(FollowDto followDto);
	
}
