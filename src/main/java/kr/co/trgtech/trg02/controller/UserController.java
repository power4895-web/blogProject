package kr.co.trgtech.trg02.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.trgtech.trg02.dto.BlogDto;
import kr.co.trgtech.trg02.dto.UserDto;
import kr.co.trgtech.trg02.service.BlogService;
import kr.co.trgtech.trg02.service.UserService;

@Controller
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private BlogService blogService;

	@Value("${file.upload.path}") 
	private String fileUploadPath;
	
	
	
	// 메인 페이지
	@GetMapping("/")
	public String index(Principal principal) {
		if (principal != null) {
			return "redirect:/blogList";
		}
		return "/loginPage";
	}
	
//	@RequestMapping("/chating")
//	public ModelAndView chat() {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("chat");
//		return mv;
//	}
	
	@RequestMapping(value="/channelPage/1")
	public String channelTalk() {
//		model.addAttribute("list", talkService.talkList(channelId));
//		model.addAttribute("channelInfo", channelRepository.findById(channelId));
//		model.addAttribute("userInfo", userRepository.findByLoginId(principal.getName()));
		return "channelTalk";
	}
	
	
	

	// 회원가입 페이지
	@GetMapping("/signupForm")
	public String singUp() {
		return "signupForm";
	}

	// 회원가입 처리
	@PostMapping("/signup")
	public String signup(UserDto userDto, BlogDto blogDto) {
		logger.info("signup begin - userDto[{}]", userDto.toString());
		logger.info("getFile[{}]", userDto.getFile());
		userService.insertUser(userDto, blogDto, fileUploadPath);



		logger.info("signup end");
		return "redirect:/loginPage";
	}

	// 로그인 페이지
	@GetMapping("/loginPage")
	public String loginPage(Principal principal) {
		if (principal != null) {
			return "redirect:/blogList";
		}
		return "loginPage";
	}

	// 로그인 결과 페이지
	@GetMapping("/loginSuccess")
	public String loginResult() {
		return "/loginSuccess";
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout() {
		return "/logout";
	}

	// 접근 거부 페이지
	@GetMapping("/user/denied")
	public String denied() {
		return "/denied";
	}

	// 유저리스트
	@RequestMapping("/userList")
	public String postList(@ModelAttribute UserDto userDto, Model model) {
		logger.info("signup begin - userDto[{}]", userDto.toString());
		List<UserDto> userList = userService.findAllUser(userDto);
		model.addAttribute("userList", userList);
		logger.info("userList end");
		return "userList";
	}

}
