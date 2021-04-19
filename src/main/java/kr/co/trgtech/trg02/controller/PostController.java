package kr.co.trgtech.trg02.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.trgtech.trg02.dto.BlogDto;
import kr.co.trgtech.trg02.dto.FileInfo;
import kr.co.trgtech.trg02.dto.FollowDto;
import kr.co.trgtech.trg02.dto.PostDto;
import kr.co.trgtech.trg02.dto.UserDto;
import kr.co.trgtech.trg02.service.BlogService;
import kr.co.trgtech.trg02.service.FileService;
import kr.co.trgtech.trg02.service.FollowService;
import kr.co.trgtech.trg02.service.PostService;
import kr.co.trgtech.trg02.service.UserService;

@Controller
public class PostController {
	private final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private PostService postService;

	@Autowired
	private FollowService followService;

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FileService fileService;
	

	// 포스트 목록 (로그인한 사람의 포스팅 리스트)
	@RequestMapping("/postList")
	public String postList(@ModelAttribute PostDto postDto, Model model, Principal principal, FollowDto followDto) {
		logger.info("postList begin - postDto[{}]", postDto.toString());

		if (principal != null) {
			postDto.setBlogId(principal.getName());
			model.addAttribute("principal", principal.getName());
		}
		List<PostDto> postList = postService.findAllPost(postDto);
		model.addAttribute("postList", postList);
		logger.debug("postList[{}]", postList);
		
		BlogDto blogInfo = blogService.findBlogNameByLoginId(principal.getName());
		model.addAttribute("blogInfo", blogInfo);
		model.addAttribute("postingId", postDto.getBlogId());

		int postCount = postService.postCount(postDto);
		logger.debug("postCount[{}]", postCount);
		model.addAttribute("postCount", postCount);

		followDto.setFollowingId(postDto.getBlogId());
		followDto.setFollowerId(postDto.getBlogId());

		// 팔로잉개수
		int followingCount = followService.followingCount(followDto);
		logger.debug("followingCount[{}]", followingCount);
		model.addAttribute("followingCount", followingCount);
		// 팔로워개수
		int followerCount = followService.followerCount(followDto);
		logger.debug("followerCount[{}]", followerCount);
		model.addAttribute("followerCount", followerCount);

		//파일아이디
		UserDto userInfo = userService.findByLoginId(principal.getName());
		System.out.println(">>>>>>>>>>id" + userInfo.getId());
		FileInfo fileInfo = new FileInfo();
		fileInfo.setRefKey(userInfo.getId());
		fileInfo.setDivision("user");
		FileInfo FileInfo = fileService.selectFileByLoginId(fileInfo);
		System.out.println("FileInfo.getNo()" + FileInfo.getNo());
		model.addAttribute("FileNo", FileInfo.getNo());
		
		
		
		
		logger.debug("blogInfo[{}]", blogInfo);
		logger.info("postList end");
		return "postList";
	}

	// 포스트 아이디맵핑
	@GetMapping("/postList/{blogId}")
	public String postListByBlogId(@ModelAttribute PostDto postDto, Model model, Principal principal,
			FollowDto followDto) {
		logger.info("postListByBlogId begin - postDto[{}]", postDto.toString());

		List<PostDto> postList = postService.findAllPost(postDto);
		if (principal != null) {
			logger.debug("postList[{}]", postList, principal.getName());
			model.addAttribute("principal", principal.getName());
		}
		model.addAttribute("postList", postList);

		// 로그인한 사람이 아닌 포스팅한 블로그아이디와 블로그 네임가져오기
		BlogDto blogInfo = blogService.findBlogNameByLoginId(postDto.getBlogId());
		model.addAttribute("blogInfo", blogInfo);
		model.addAttribute("postingId", postDto.getBlogId());
		logger.debug("blogId[{}]", postDto.getBlogId()); // 글쓴사람의 아이디가 필요

		// 게시글 수
		int postCount = postService.postCount(postDto);
		logger.debug("postCount[{}]", postCount);
		model.addAttribute("postCount", postCount);

		followDto.setFollowingId(postDto.getBlogId());
		// 팔로잉, 팔로워 개수
		logger.debug("followDto[{}]", followDto);
		int followingCount = followService.followingCount(followDto);
		logger.debug("followingCount[{}]", followingCount);
		model.addAttribute("followingCount", followingCount);

		followDto.setFollowerId(postDto.getBlogId());
		int followerCount = followService.followerCount(followDto);
		logger.debug("followerCount[{}]", followerCount);
		model.addAttribute("followerCount", followerCount);

		followDto.setFollowingId(principal.getName());
		FollowDto followerId = followService.getPostDetailFollowerIdByLoginId(followDto);
		logger.debug("followerId[{}]", followerId);
		model.addAttribute("followerId", followerId);

		logger.info("postListByBlogId end");
		return "postList";
	}

	// 나와 팔로잉한사람을 제외한 블로그 컨텐츠, 나중에 페이징처리
	@RequestMapping("/postListByExMeAndFolId")
	public String findAllBContentExcidAndFolId(Model model, Principal principal, PostDto postDto, FollowDto followDto) {
		logger.info("findAllBContentExcidAndFolId begin - ");
		postDto.setBlogId(principal.getName());
		logger.debug("postDto[{}]", postDto);
		followDto.setFollowingId(principal.getName());
		followDto.setBlogId(principal.getName());
		logger.debug("followDto[{}]", followDto);

		List<FollowDto> findAllBContentExcidAndFolId = postService
				.findAllBContentExcidAndFolId(followDto);

		logger.debug("findAllBContentExcidAndFolId[{}]", findAllBContentExcidAndFolId);
		model.addAttribute("findAllBContentExcidAndFolId", findAllBContentExcidAndFolId);
		model.addAttribute("userId", principal.getName());


		logger.info("findAllBContentExcidAndFolId end");
		return "postListByExMeAndFolId";
	}

	// 포스트 상세
	@GetMapping("/postDetail/{no}")
	public String postDetail(@PathVariable("no") int no, Model model, Principal principal, FollowDto followDto) {
		logger.info("postdetail begin - no[{}]", no);

		PostDto postDetail = postService.findByPostId(no);
		logger.debug("postDetail[{}]", postDetail);
		model.addAttribute("postDetail", postDetail);
		model.addAttribute("principal", principal.getName());
		postDetail.getBlogId();
		followDto.setFollowingId(principal.getName());
		followDto.setFollowerId(postDetail.getBlogId());
		logger.debug("followDto[{}]", followDto);
		FollowDto followerId = followService.getPostDetailFollowerIdByLoginId(followDto);
		logger.debug("followerId[{}]", followerId);
		model.addAttribute("followerId", followerId);

		logger.info("postdetail end");
		return "postDetail";
	}

	// 포스트 등록폼
	@RequestMapping("/postForm")
	public String insertPostForm(@ModelAttribute PostDto postDto, Model model, Principal principal) {
		logger.info("insertPostForm begin - postDto[{}]", postDto.toString());
		model.addAttribute("principal", principal.getName());
		logger.info("insertPostForm end");
		return "postForm";
	}

	// 포스트 등록
	@PostMapping("/insertPost")
	public String insertPost(PostDto postDto, Principal principal) {
		logger.info("insertPost begin - postDto[{}]", postDto);
		postDto.setBlogId(principal.getName());
		postService.insertPost(postDto);
		logger.info("insertPost end");
		return "redirect:/postList";
	}

	// 포스트 수정폼
	@GetMapping("/post/update/{no}")
	public String updateFormPost(@PathVariable("no") int no, Model model) {
		logger.info("updateFormPost begin - no[{}]", no);

		PostDto postDetail = postService.findByPostId(no);
		logger.debug("postDetail[{}]", postDetail);
		model.addAttribute("postDetail", postDetail);

		logger.info("updateFormPost end");
		return "postUpdate";
	}

	// 포스트 수정
	@RequestMapping("/post/update/{no}")
	public String updatePost(PostDto postDto) {
		logger.info("updatePost begin - postDto[{}]", postDto);
		postService.updatePost(postDto);
		logger.info("updatePost end");
		return "redirect:/postList";
	}

	// 포스트 삭제
	@RequestMapping("/post/delete/{no}")
	public String deletePost(@PathVariable("no") int no) {
		logger.info("deletePost begin - no[{}]", no);
		postService.deletePost(no);
		logger.info("deletePost end");
		return "redirect:/postList";
	}

	//json안될 때 , html로 할 때
//	@RequestMapping(value = "/infiniteScrollDown5", method = RequestMethod.POST)
//	@ResponseBody
//	public ModelAndView infiniteScrollDown5(PostDto postDto, Model model) {
//		String idToStart = postDto.getId();
//		List<PostDto> FindContentByFollowingId = postService.infiniteScrollDown(postDto);
//
//		ModelAndView ModelAndView = new ModelAndView();
//
////		model.addAttribute("FindContentByFollowingId", FindContentByFollowingId);
//		ModelAndView.setViewName("test");
//		ModelAndView.addObject("FindContentByFollowingId", FindContentByFollowingId);
//		return ModelAndView;
//	}

}
