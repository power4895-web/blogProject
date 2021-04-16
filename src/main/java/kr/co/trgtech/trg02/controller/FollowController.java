package kr.co.trgtech.trg02.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.trgtech.trg02.dto.AlramHistoryDto;
import kr.co.trgtech.trg02.dto.FollowDto;
import kr.co.trgtech.trg02.service.AlramHistoryService;
import kr.co.trgtech.trg02.service.BlogService;
import kr.co.trgtech.trg02.service.FollowService;

@Controller
public class FollowController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private FollowService followService;
	@Autowired
	private BlogService blogService;
	@Autowired
	private AlramHistoryService alramHistoryService;

	// 팔로우 카운트
	@RequestMapping("/followCount")
	public String followCount(Model model, FollowDto followDto, String id, Principal principal) {
		logger.info("followCount begin - {[],[]}", followDto, id);
		followDto.setFollowerId(principal.getName());
		followDto.setFollowingId(principal.getName());
		String followerId = followDto.getFollowerId();
		String followCount = followService.followCount(followerId, id);

		logger.debug("followCount[{}]", followCount);
		model.addAttribute("followCount", followCount);

		logger.info("followCount end");
		return followCount;
	}
	
	
	@RequestMapping(value = "/alramCount", method = RequestMethod.GET)
	@ResponseBody
	public String alramCount(Model model, AlramHistoryDto alramHistoryDto,Principal principal) {
		logger.info("alramCount begin - {[]}", alramHistoryDto.getSendingId());
		alramHistoryDto.setGetingId(principal.getName());
		logger.info("followCount end");
		String count = alramHistoryService.alramHistoryCount(alramHistoryDto);
		return count; 
	}	
	
	// 포스팅아이디가 팔로잉사람들의 목록을 볼 수 있는 페이지
	@RequestMapping("/followingList/{followingId}")
	public String followingList(Model model, Principal principal, FollowDto followDto) {
		logger.info("followList begin - followDto[{}]", followDto);
		followDto.setFollowingId(followDto.getFollowingId());
		List<FollowDto> followingList = followService.FindAllByFollowing(followDto);
		logger.debug("followingList[{}]", followingList);
		model.addAttribute("followingList", followingList);

		// 팔로잉페이지에서 팔로워 페이지로 이동할 때 포스팅 아이디 필요
		model.addAttribute("postingId", followDto.getFollowingId());

		logger.info("followingList end");
		return "followingList";
	}

	// 포스팅아이디를 팔로잉한 사람들의 목록을 볼 수 있는 페이지(팔로워 페이지)
	@RequestMapping("/followerList/{followerId}")
	public String followerList(Model model, Principal principal, FollowDto followDto) {
		logger.info("followerList begin - followDto[{}]", followDto);
		followDto.setFollowerId(followDto.getFollowerId());
		List<FollowDto> followerList = followService.FindAllByFollower(followDto);

		logger.debug("followerList[{}]", followerList);
		model.addAttribute("followerList", followerList);

		// 팔로워 페이지(현재페이지)에서 팔로잉페이지 갈 때 포스팅 아이디 필요
		model.addAttribute("postingId", followDto.getFollowerId());

		logger.info("followerList end");
		return "followerList";
	}

	// 로그인한 아이디가 팔로잉한 팔로잉리스트
	// : 2를 만든 이유는, 같은 페이지안에서 following아이디를 로그인한사람으로 보낼 때도 있고 포스팅한 아이디로 보낼 때도 있어서,
	// 분기처리가 안될 것 같아서 나눔
	@RequestMapping("/followingList")
	public String followingList2(Model model, Principal principal, FollowDto followDto) {
		logger.info("followList begin - followDto[{}]", followDto);
		followDto.setFollowingId(principal.getName());

		// 포스팅아이디가 팔로잉한 사람의 리스트
		List<FollowDto> followingList = followService.FindAllByFollowing(followDto);
		logger.debug("followingList[{}]", followingList);
		model.addAttribute("followingList", followingList);

		// 팔로워 페이지로 이동할 때 포스트 아이디 필요
		model.addAttribute("postingId", followDto.getFollowingId());

		logger.info("followingList end");
		return "followingList";
	}

	// 로그인한 팔로워 페이지 페이지리스트
	// : 2를 만든 이유는, 같은 페이지안에서 follower아이디를 로그인한사람으로 보낼 때도 있고 포스팅한 아이디로 보낼 때도 있어서,
	// 분기처리가 안될 것 같아서 나눔
	@RequestMapping("/followerList")
	public String followerList2(Model model, Principal principal, FollowDto followDto) {
		logger.info("followList begin - followDto[{}]", followDto);
		followDto.setFollowerId(followDto.getFollowerId());

		List<FollowDto> followerList = followService.FindAllByFollower(followDto);

		logger.debug("followerList[{}]", followerList);
		model.addAttribute("followerList", followerList);

		// 팔로잉 페이지 갈 때 포스팅 아이디 필요
		model.addAttribute("postingId", followDto.getFollowerId());

		logger.info("followerList end");
		return "followerList";
	}

	// 로그인한 사람이 팔로잉한 사람들의 컨텐츠만을 볼 수 있는 페이지(팔로잉 하지 않은 회원들의 정보는 볼 수 없음)
	@RequestMapping("/followingContentList") // 블로그id랑 postid 내가 팔로잉한 아이디
	public String FindContentByFollowingId(Model model, Principal principal, FollowDto followDto) {
		logger.info("FindContentByFollowingId begin - ");
		System.out.println("principal.getName()" + principal.getName());
		followDto.setFollowingId(principal.getName());
		List<FollowDto> FindContentByFollowingId = followService.FindContentByFollowingId(followDto);

		logger.debug("FindContentByFollowingId[{}]", FindContentByFollowingId);
		model.addAttribute("FindContentByFollowingId", FindContentByFollowingId);
		model.addAttribute("userId", principal.getName());

		logger.info("followingList end");
		return "followingContentList";
	}
	
	// 무한스크롤 에이작스, 현재 페이지에서 마지막 번호를 받아 그 다음 10개의 게시글 추출
	@RequestMapping(value = "/infiniteScrollDown", method = RequestMethod.POST)
	@ResponseBody
	public List<FollowDto> infiniteScrollDown(FollowDto followDto, Model model, Principal principal) {
		logger.info("infiniteScrollDown begin - ");
		String idToStart = followDto.getId();
		followDto.setFollowingId(principal.getName());
		List<FollowDto> FindContentByFollowingId = followService.infiniteScrollDown(followDto);
		model.addAttribute("FindContentByFollowingId", FindContentByFollowingId);
		return followService.infiniteScrollDown(followDto);
	}
	//무한스크롤 2 테스트중
	@RequestMapping(value = "/infiniteScrollDown2", method = RequestMethod.POST)
	@ResponseBody
	public List<FollowDto> infiniteScrollDown2(FollowDto followDto, Model model, Principal principal) {
		logger.info("infiniteScrollDown2 begin - ");
		String idToStart = followDto.getId();
		followDto.setFollowingId(principal.getName());
		List<FollowDto> FindContentByFollowingId = followService.infiniteScrollDown(followDto);
		model.addAttribute("FindContentByFollowingId", FindContentByFollowingId);
		return followService.infiniteScrollDown(followDto);
	}

	//팔로잉 추가
	@RequestMapping(value = "/insertFollowingAjax", method = RequestMethod.POST)
	@ResponseBody
	public int insertFollowingAjax(FollowDto followDto, Model model, Principal principal) {
		logger.info("insertFollowingAjax begin - followDto[{},blogId]", followDto);
		followDto.setFollowingId(principal.getName());
		followDto.setFollowerId(followDto.getFollowerId());

		logger.debug("followDto[{}]", followDto);
		AlramHistoryDto alramHistoryDTo = new AlramHistoryDto();
		alramHistoryDTo.setGetingId(followDto.getFollowerId());
		alramHistoryDTo.setSendingId(principal.getName());
		alramHistoryDTo.setContent(principal.getName() + "님이 팔로잉을 시작하였습니다.");
		alramHistoryService.insertFollowingHistory(alramHistoryDTo);
		return followService.insertFollowing(followDto);
	}
	//팔로우 삭제
	@RequestMapping(value = "/deleteFollowingAjax", method = RequestMethod.DELETE)
	@ResponseBody
	public int deleteFollowingAjax(FollowDto followDto, Model model, Principal principal) {
		logger.info("deleteFollowingAjax begin - followDto[{},blogId]", followDto);
		followDto.setFollowingId(principal.getName());
		followDto.setFollowerId(followDto.getFollowerId());
		
		AlramHistoryDto alramHistoryDTo = new AlramHistoryDto();
		alramHistoryDTo.setGetingId(followDto.getFollowerId());
		alramHistoryDTo.setSendingId(principal.getName());
		alramHistoryService.deleteFollowingHistory(alramHistoryDTo);
		
		
		return followService.deleteFollowing(followDto);
	}
		
	
	/**
	 * 본인이 팔로잉 등록
	 * 
	 * @return
	 */
	@RequestMapping("/insertfollowing/{blogId}")
	public String insertfollowing(FollowDto followDto, RedirectAttributes redirect, Principal principal,
			@PathVariable("blogId") String blogId, Model model) {
		logger.info("insertfollowing begin - blogId[{}]", blogId);
		followDto.setFollowingId(principal.getName());
		followDto.setFollowerId(blogId);

		logger.debug("followDto[{}]", followDto);
		followService.insertFollowing(followDto);
		followDto.setFollowerId(principal.getName());
		redirect.addFlashAttribute("principal", followDto.getFollowingId());
		
		logger.info("insertfollowing end");
		return "redirect:/followingList";
	}

	// 자신이 팔로잉한 사람들의 목록을 볼 수 있는 페이지에서 팔로잉한 사람들을 취소하는 메소드
	@RequestMapping("/deleteFollowing/{blogId}")
	public String deleteFollowing(FollowDto followDto, Principal principal, @PathVariable("blogId") String blogId) {
		logger.info("deleteFollowing begin - followDto[{}]", followDto);
		logger.debug("blogId[{}]", blogId);
		followDto.setFollowingId(principal.getName());
		followDto.setFollowerId(blogId);
		logger.debug("followDto[{}]", followDto);
		followService.deleteFollowing(followDto);
		logger.info("deleteFollowing end");
		return "redirect:/followingList";
	}

//		팔로워를 볼 수 있는 페이지에서 로그인한사람이 자신의 팔로워들을 해제 하는 메소드
	@RequestMapping("/deleteFollowerByMe/{blogId}")
	public String deleteFollowerByMe(FollowDto followDto, Principal principal, @PathVariable("blogId") String blogId) {
		logger.info("deleteFollowerByMe begin - followDto[{}]", followDto);
		followDto.setFollowingId(blogId);
		followDto.setFollowerId(principal.getName());
		followService.deleteFollowerByMe(followDto);
		logger.info("deleteFollowerByMe end");
		return "redirect:/followerList";
	}
	
	
//	@RequestMapping("/chating")
//	public String chat(FollowDto followDto, Principal principal, Model model ) {
//		
//		followDto.setFollowingId(principal.getName());
//		model.addAttribute("userId", followDto.getFollowingId());
//		return "chat";
//	}
}
