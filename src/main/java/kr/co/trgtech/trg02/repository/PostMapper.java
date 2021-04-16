package kr.co.trgtech.trg02.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trgtech.trg02.dto.FollowDto;
import kr.co.trgtech.trg02.dto.PostDto;

@Mapper
public interface PostMapper {

	public int postCount(PostDto postDto);

	public List<PostDto> findAllPost(PostDto postDto);

	public List<FollowDto> findAllBContentExcidAndFolId(FollowDto followDto);

	public int insertPost(PostDto postDto);

	public PostDto findByPostId(int id);

	public int updatePost(PostDto postDto);

	public int deletePost(int id);

	public List<PostDto> infiniteScrollDown(PostDto postDto);

}
