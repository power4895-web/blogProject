package kr.co.trgtech.trg02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.trgtech.trg02.dto.FollowDto;
import kr.co.trgtech.trg02.dto.PostDto;
import kr.co.trgtech.trg02.repository.PostMapper;

@Service
public class PostService {

	@Autowired
	private PostMapper postMapper;

	public int postCount(PostDto postDto) {
		return postMapper.postCount(postDto);
	}

	public List<PostDto> findAllPost(PostDto postDto) {
		return postMapper.findAllPost(postDto);
	}
	
	public List<FollowDto> findAllBContentExcidAndFolId(FollowDto followDto) {
		return postMapper.findAllBContentExcidAndFolId(followDto);
	}
	
	public PostDto findByPostId(int id) {
		return postMapper.findByPostId(id);
	}

	public int insertPost(PostDto postDto) {
		return postMapper.insertPost(postDto);
	}

	public int updatePost(PostDto postDto) {
		return postMapper.updatePost(postDto);
	}

	public int deletePost(int id) {
		return postMapper.deletePost(id);
	}
	
	public List<PostDto> infiniteScrollDown(PostDto postDto) {
		return postMapper.infiniteScrollDown(postDto);
	}
	
	
	
}
