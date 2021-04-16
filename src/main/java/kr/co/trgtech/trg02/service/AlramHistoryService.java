package kr.co.trgtech.trg02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.trgtech.trg02.dto.AlramHistoryDto;
import kr.co.trgtech.trg02.dto.FollowDto;
import kr.co.trgtech.trg02.repository.AlramHistoryMapper;

@Service
public class AlramHistoryService {

	@Autowired
	private AlramHistoryMapper alramHistoryMapper;

	public List<AlramHistoryDto> findAllAlramById(AlramHistoryDto alramHistoryDto) {
		return alramHistoryMapper.findAllAlramById(alramHistoryDto);
	}

	public String alramHistoryCount(AlramHistoryDto alramHistoryDto) {
		return alramHistoryMapper.alramHistoryCount(alramHistoryDto);
	}

	public int insertFollowingHistory(AlramHistoryDto alramHistoryDto) {
		return alramHistoryMapper.insertFollowingHistory(alramHistoryDto);
	}
	
	
	public int deleteFollowingHistory(AlramHistoryDto alramHistoryDt) {
		return alramHistoryMapper.deleteFollowingHistory(alramHistoryDt);
	}
	
}
