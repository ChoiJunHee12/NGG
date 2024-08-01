import React, { useState, useEffect, useRef } from 'react';
import './OTO_detail.css';

interface Message {
  type: 'user' | 'consultant';
  profileImage: string;
  text: string;
  time: string;
}

const OTO_detail: React.FC = () => {
  const getCurrentDateTime = (): string => {
    const now = new Date();
    const day = now.getDate().toString().padStart(2, '0');
    const month = (now.getMonth() + 1).toString().padStart(2, '0');
    const year = now.getFullYear();
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');
    return `${year}-${month}-${day} ${hours}:${minutes}`;
  };

  const [newMessage, setNewMessage] = useState('');
  const [messages, setMessages] = useState<Message[]>([
    { type: 'consultant', profileImage: '/img/OneToOne_img/noimage.png', text: '안녕하세요, 어떻게 도와드릴까요?', time: getCurrentDateTime() },
    { type: 'user', profileImage: '/img/OneToOne_img/noimage.png', text: '안녕하세요, 상담을 받고 싶습니다.', time: getCurrentDateTime() },
    { type: 'user', profileImage: '/img/OneToOne_img/noimage.png', text: '제가 궁금한 점이 있습니다.', time: getCurrentDateTime() },
    { type: 'consultant', profileImage: '/img/OneToOne_img/noimage.png', text: '그게 무엇인가요?', time: getCurrentDateTime() },
  ]);
  const chatContainerRef = useRef<HTMLDivElement | null>(null);

  useEffect(() => {
    scrollToEnd();
  }, [messages]);

  const sendMessage = () => {
    if (newMessage.trim() !== '') {
      setMessages([...messages, { type: 'consultant', profileImage: '/img/OneToOne_img/noimage.png', text: newMessage, time: getCurrentDateTime() }]);
      setNewMessage('');
    }
  };

  const scrollToEnd = () => {
    if (chatContainerRef.current) {
      chatContainerRef.current.scrollTop = chatContainerRef.current.scrollHeight;
    }
  };

  return (
    <div className="container">
      <div className="OTO-con-1">
        <div className="OTO-con-2">
          <div className="OTO-con-3">
            <h3 style={{fontWeight: "bold"}}>면접자 1:1 상담</h3>
          </div>
        </div>
      </div>
      <div className="OTO-notice">
        📢 답변 시간은 컨설턴트 상황에 따라 다르게 소요되며 욕설 및 비방을 한 사용자는 경고 조치 없이 불이익을 받을 수 있습니다.
      </div>
      <div className="OTO-con">

    <div className="interviewRes-counsel">
        <div className="interviewRes-imgbox">
            <img src="/img/OneToOne_img/noimage.png" alt='noimage' className="interviewRes-img" />
        </div>
        <div className="interviewRes-text">
            <h5 className="interviewRes-h5">면접자 : 침착맨</h5>
        </div>
    </div>
        <div className="chat-container scrollable-div" ref={chatContainerRef}>
          {messages.map((message, index) => (
            <div key={index} className={`chat-message ${message.type}`}>
              <img src={message.profileImage} alt={message.type} className="profile-image" />
              <div className="message-info">
                <div className={`message-text ${message.type}`}>
                  {message.text}
                </div>
                <div className={`message-time ${message.type}`}>
                  {message.time}
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
      <div className="OTO-userWrite">
        <input
          value={newMessage}
          onChange={(e) => setNewMessage(e.target.value)}
          onKeyUp={(e) => e.key === 'Enter' && sendMessage()}
          type="text"
          className="user-input"
          placeholder="메시지를 입력하세요..."
        />
        <button onClick={sendMessage} className="send-button">전송</button>
      </div>
    </div>
  );
};

export default OTO_detail;
