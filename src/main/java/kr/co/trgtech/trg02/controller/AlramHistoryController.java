package kr.co.trgtech.trg02.controller;
import java.security.Principal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import kr.co.trgtech.trg02.dto.AlramHistoryDto;
import kr.co.trgtech.trg02.service.AlramHistoryService;

@Controller
public class AlramHistoryController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AlramHistoryService alramHistoryService;
	
	//	나를 제외한 블로그 리스트
	@RequestMapping("/alramHistoryList")
	public String findAllAlramById(Model model, Principal principal, AlramHistoryDto AlramHistoryDto) {
		logger.info("alramHistoryList begin - ");
		AlramHistoryDto.setGetingId(principal.getName());
		logger.debug("AlramHistoryDto[{}]", AlramHistoryDto);
		List<AlramHistoryDto> alramHistoryList = alramHistoryService.findAllAlramById(AlramHistoryDto);

		logger.debug("alramHistoryList[{}]", alramHistoryList);
		model.addAttribute("alramHistoryList", alramHistoryList);

		logger.info("alramHistoryList end");
		return "alramHistoryList";
	}
}
