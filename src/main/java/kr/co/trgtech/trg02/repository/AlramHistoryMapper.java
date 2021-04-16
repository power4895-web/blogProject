package kr.co.trgtech.trg02.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trgtech.trg02.dto.AlramHistoryDto;

@Mapper
public interface AlramHistoryMapper {

	public String alramHistoryCount(AlramHistoryDto alramHistoryDto);

	public List<AlramHistoryDto> findAllAlramById(AlramHistoryDto alramHistoryDto);

	public int insertFollowingHistory(AlramHistoryDto alramHistoryDto);
	
	public int deleteFollowingHistory(AlramHistoryDto alramHistoryDto);

}
