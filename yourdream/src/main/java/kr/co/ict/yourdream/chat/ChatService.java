package kr.co.ict.yourdream.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.co.ict.yourdream.vo.ChatRoomVO;

@Service
@Transactional
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public List<ChatRoomVO> getall() {
        return chatRepository.findAll();
    }
}
