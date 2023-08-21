package org.example;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {
	
	List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {

		System.out.println("handleTextMessage with message: " + message + " & session: " + session);

		// sending received message to all connected users:
		for(WebSocketSession webSocketSession : sessions) {
			String msg = message.getPayload();
			webSocketSession.sendMessage(new TextMessage("New message " + msg));
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		System.out.println("afterConnectionEstablished with session: " + session);
		sessions.add(session);
	}
}