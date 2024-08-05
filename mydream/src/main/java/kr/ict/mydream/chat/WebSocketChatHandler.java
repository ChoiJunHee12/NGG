package kr.ict.mydream.chat;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketChatHandler extends TextWebSocketHandler {
    private static final ConcurrentHashMap<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, String> SESSION_ROOMS = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 임의로 방 번호 '50'에 세션을 할당
        System.out.println("Client connected: " + session.getId());
        // 세션 속성에서 프로토콜 값 가져오기
        String protocol = (String) session.getAttributes().get("protocol");
        if (protocol != null) {
            System.out.println("Received protocol in afterConnectionEstablished: " + protocol);
        }
        System.out.println("연결되었습니다.");

        SESSION_ROOMS.put(session.getId(), "50");
        CLIENTS.put(session.getId(), session);
        System.out.println("여기오나?");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("연결이 끊겼습니다.");
        SESSION_ROOMS.remove(session.getId());
        CLIENTS.remove(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String sessionId = session.getId();
        String room = SESSION_ROOMS.get(sessionId);

        System.out.println("Message from " + sessionId + " in room " + room + ": " + message.getPayload());

        CLIENTS.entrySet().stream()
                .filter(entry -> SESSION_ROOMS.get(entry.getKey()).equals(room) && !entry.getKey().equals(sessionId))
                .forEach(entry -> {
                    try {
                        entry.getValue().sendMessage(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}
