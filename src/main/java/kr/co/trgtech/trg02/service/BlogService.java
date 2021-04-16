package kr.co.trgtech.trg02.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.trgtech.trg02.dto.BlogDto;
import kr.co.trgtech.trg02.dto.PostDto;
import kr.co.trgtech.trg02.repository.BlogMapper;

@Service
public class BlogService {

	@Autowired
	private BlogMapper blogMapper;

	public List<BlogDto> findAllBlog() {
		return blogMapper.findAllBlog();
	}
	
	public List<BlogDto> findAllBlogExcUserId(BlogDto blogDto) {
		return blogMapper.findAllBlogExcUserId(blogDto);
	}
	
	public BlogDto findBlogNameByLoginId(String userId) {
		return blogMapper.findBlogNameByLoginId(userId);
	}
	
	
	public List<BlogDto> findAllBlogContentExcUserId(BlogDto blogDto) {
		return blogMapper.findAllBlogContentExcUserId(blogDto);
	}
	
//	public List<BlogDto> findAllBlogContentExcUserIdAndFollowingId(BlogDto blogDto, FollowDto followDto) {
//		return blogMapper.findAllBlogContentExcUserIdAndFollowingId(blogDto, followDto);
//	}

	public int insertBlog(BlogDto blogDto) {
		return blogMapper.insertBlog(blogDto);
	}

}
