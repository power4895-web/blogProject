package kr.co.trgtech.trg02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.trgtech.trg02.dto.FollowDto;
import kr.co.trgtech.trg02.dto.PostDto;
import kr.co.trgtech.trg02.repository.FollowMapper;

@Service
public class FollowService {

	@Autowired
	private FollowMapper followMapper;

	public int followingCount(FollowDto followDto) {
		return followMapper.followingCount(followDto);
	}
	
	public int followerCount(FollowDto followDto) {
		return followMapper.followerCount(followDto);
	}
	
	public List<FollowDto> FindAllByFollowing(FollowDto followDto) {
		return followMapper.FindAllByFollowing(followDto);
	}

	public List<FollowDto> FindAllByFollower(FollowDto followDto) {
		return followMapper.FindAllByFollower(followDto);
	}
	
	public List<FollowDto> FindContentByFollowingId(FollowDto followDto) {
		return followMapper.FindContentByFollowingId(followDto);
	}
	
	public int insertFollowing(FollowDto followDto) {
		return followMapper.insertFollowing(followDto);
	}

	
	
	public int deleteFollowing(FollowDto followDto) {
		return followMapper.deleteFollowing(followDto);
	}
	
	public int deleteFollowerByMe(FollowDto followDto) {
		return followMapper.deleteFollowerByMe(followDto);
	}
	
	public FollowDto getPostDetailFollowerIdByLoginId(FollowDto followDto) {
		return followMapper.getPostDetailFollowerIdByLoginId(followDto);
	}
	
	
	public String followCount(String id, String followerId) {
		return followMapper.followCount(id, followerId);
	}
	
	
	
	public List<FollowDto> infiniteScrollDown(FollowDto followDto) {
		return followMapper.infiniteScrollDown(followDto);
	}



}
