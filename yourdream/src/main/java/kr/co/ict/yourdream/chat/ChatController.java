package kr.co.ict.yourdream.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.ict.yourdream.vo.ChatRoomVO;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @GetMapping("/get")
    public List<ChatRoomListDTO> chatroomlist() {
        return null;
    }

    @GetMapping("/all")
    public List<ChatRoomVO> getall() {
        return chatService.getall();
    }
}
