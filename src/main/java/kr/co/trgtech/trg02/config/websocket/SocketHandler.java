package kr.co.trgtech.trg02.config.websocket;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.trgtech.trg02.dto.AlramHistoryDto;
import kr.co.trgtech.trg02.dto.UserDto;
import kr.co.trgtech.trg02.service.AlramHistoryService;

//import kr.co.trgtech.trg01.dto.TalkDto;
//import kr.co.trgtech.trg01.service.TalkService;

@Component
public class SocketHandler extends TextWebSocketHandler {

	@Autowired
	private AlramHistoryService alramHistoryService;

	private static final Logger logger = LoggerFactory.getLogger(SocketHandler.class);

	Map<String, WebSocketSession> sessionsMap = new HashMap<String, WebSocketSession>();

	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * @title 소켓연결
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionsMap.put(session.getId(), session);
		super.afterConnectionEstablished(session);
	}

	/**
	 * 메세지 수신
	 */
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws JsonMappingException, JsonProcessingException {
		AlramHistoryDto alramHistoryDto = objectMapper.readValue(message.getPayload(), AlramHistoryDto.class);
		String count = alramHistoryService.alramHistoryCount(alramHistoryDto);
		alramHistoryDto.setCount(count);
		String msg = objectMapper.writeValueAsString(alramHistoryDto);
		for (String key : sessionsMap.keySet()) { //
			WebSocketSession ws = (WebSocketSession) sessionsMap.get(key);
			try {
				// 메세지보냄
				ws.sendMessage(new TextMessage(msg));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @title 소켓종료
	 */
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		// 저장된 소켓 제거
		sessionsMap.remove(session.getId());
	}
}
