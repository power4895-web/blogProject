package kr.co.trgtech.trg02.controller;

import java.security.Principal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.trgtech.trg02.dto.BlogDto;
import kr.co.trgtech.trg02.dto.FollowDto;
import kr.co.trgtech.trg02.service.BlogService;

@Controller
public class BlogController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BlogService blogService;

	 //블로그 리스트
	@RequestMapping("/blogList")
	public String FindAllblog(Model model) {
		logger.info("FindAllblog begin - ");
		List<BlogDto> blogList = blogService.findAllBlog();

		logger.debug("FindAllblog[{}]", blogList);
		model.addAttribute("blogList", blogList);

		logger.info("FindAllblog end");
		return "blogList";
	}
	
	//	나를 제외한 블로그 리스트
	@RequestMapping("/blogListExcUserId")
	public String findAllBlogExcUserId(Model model, Principal principal, BlogDto blogDto) {
		logger.info("blogList begin - ");
		blogDto.setUserId(principal.getName());
		logger.debug("blogDto[{}]", blogDto);
		List<BlogDto> blogList = blogService.findAllBlogExcUserId(blogDto);

		logger.debug("blogList[{}]", blogList);
		model.addAttribute("blogList", blogList);

		logger.info("blogList end");
		return "blogList";
	}
	
	
	//	나를 제외한 블로그 컨텐츠,  나중에 페이징처리
//	@RequestMapping("/blogContentExcUserId2")
//	public String findAllBlogContentExcLoginId(Model model, Principal principal, BlogDto blogDto,FollowDto followDto) {
//		logger.info("blogList begin - ");
//		blogDto.setUserId(principal.getName());
//		logger.debug("blogDto[{}]", blogDto);
//		followDto.setFollowingId(principal.getName());
//		followDto.setFollowerId(principal.getName());
//		logger.debug("followDto[{}]", followDto);
//		
//		List<BlogDto> findAllBlogContentExcUserIdAndFollowingId = blogService.findAllBlogContentExcUserIdAndFollowingId(blogDto, followDto);
//
//		logger.debug("blogContentListByExcLoginId[{}]", findAllBlogContentExcUserIdAndFollowingId);
//		model.addAttribute("blogContentListByExcLoginId", findAllBlogContentExcUserIdAndFollowingId);
//		
//		logger.info("blogList end");
//		return "blogContentExcUserId2";
//	}

//	/**
//	 * 블로그 등록
//	 * @return
//	 */
//	 @PostMapping("/insertBlog")
//	public String insertBlog(BlogDto blogDto) {
//		blogService.insertBlog(blogDto);
//		return "blogPage";
//	}

//	/**
//	 * 블로그 폼
//	 * @return
//	 */
//	@RequestMapping("/blogForm")
//	public String blogForm(@ModelAttribute BlogDto blogDto, Model model) {
//		
//		// 조회
//		return "blogForm"; 
//	}

}
