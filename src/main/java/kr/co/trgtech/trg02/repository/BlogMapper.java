package kr.co.trgtech.trg02.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trgtech.trg02.dto.BlogDto;

@Mapper
public interface BlogMapper {

	public List<BlogDto> findAllBlog();
	
	public List<BlogDto> findAllBlogExcUserId(BlogDto blogDto);

	public List<BlogDto> findAllBlogContentExcUserId(BlogDto blogDto);
	
//	public List<BlogDto> findAllBlogContentExcUserIdAndFollowingId(BlogDto blogDto, FollowDto followDto);
	
	public BlogDto findBlogNameByLoginId(String userId);
	
	public int insertBlog(BlogDto blogDto);
}
