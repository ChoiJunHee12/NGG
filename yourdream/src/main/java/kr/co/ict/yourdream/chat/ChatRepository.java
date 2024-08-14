package kr.co.ict.yourdream.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import kr.co.ict.yourdream.vo.ChatRoomVO;

@Repository
@Transactional
public interface ChatRepository extends JpaRepository<ChatRoomVO, Integer> {

}