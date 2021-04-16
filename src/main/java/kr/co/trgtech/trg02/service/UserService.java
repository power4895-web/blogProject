package kr.co.trgtech.trg02.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.trgtech.trg02.dto.UserDto;
import kr.co.trgtech.trg02.repository.UserMapper;

@Service
public class UserService implements UserDetailsService {
	private final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserMapper userMapper;

	// 비밀번호 암호화
	@Transactional
	public int insertUser(UserDto userDto) {
		logger.info("insertUser Service begin - userDto[{}]", userDto);

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		logger.debug("userDto.getPassword[{}]", userDto.getPassword());

		logger.info("insertUser Service end");
		return userMapper.insertUser(userDto);
	}

	public List<UserDto> findAllUser(UserDto userDto) {
		return userMapper.findAllUser(userDto);
	}

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		// user정보
		logger.info("loadUserByUsername begin - loginId[{}]", loginId);
		UserDto userInfo = userMapper.findByLoginId(loginId);
		System.out.println(">>>>>>>>userInfo.getLoginId()" + userInfo.getLoginId());
		System.out.println(">>>>>>>>userInfo.getPassword()" + userInfo.getPassword());
		// 권한
		List<GrantedAuthority> authorities = new ArrayList<>();
		logger.debug("authorities[{}]", authorities);
		logger.info("loadUserByUsername end");
		return new User(userInfo.getLoginId(), userInfo.getPassword(), authorities);
	}

}
