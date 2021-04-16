package kr.co.trgtech.trg02.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.trgtech.trg02.dto.UserDto;

@Mapper
public interface UserMapper {

	public UserDto findByLoginId(String loginId);

	public List<UserDto> findAllUser(UserDto userDto);

	public int insertUser(UserDto userDto);

}
