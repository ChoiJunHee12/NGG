<template>
    <div class="container">
        <div class="OTO-con-1">
            <div class="OTO-con-2">
                <div class="OTO-con-3">
                    <blockquote class="blockquote-mypage"><b>
                    <p>담당 컨설턴트와의 <span class="highlight">1:1</span> 상담</p></b>
                </blockquote>
                </div>
            </div>
        </div>
        <div class="OTO-notice">
            📢&nbsp; 답변 시간은 컨설턴트 상황에 따라 다르게 소요되며 욕설 및 비방을 한 사용자는 경고 조치 없이 불이익을 받을 수 있습니다.
            <div class="chat-container-ex">
            <div class="chat-message consultant">
                <img src="/img/noimage.png" alt="message.type" class="chat-profile-image" >
                <div class="message-info">
                    <div class="message-text">
                        컨설턴트의 메세지입니다.<br>
                        컨설턴트의 답변이 왼쪽에 보입니다.
                    </div>
                    </div>
                </div>
            <div class="chat-message user">
                <img src="/img/noimage.png" alt="message.type" class="chat-profile-image" >
                <div class="message-info">
                    <div class="message-text">
                        당신의 메세지입니다.<br>
                        당신의 메세지는 오른쪽에 보입니다.
                    </div>
                </div>
            </div>
            </div>
        </div>   
        <div class="OTO-con">
            <div class="chat-container scrollable-div" ref="chatContainer">
                <div v-for="(message, index) in messages" :key="index" :class="['chat-message', message.type]">
                    <img :src="message.profileImage" :alt="message.type" class="chat-profile-image" >
                    <div class="message-info">
                        <div :class="['message-text', message.type]">
                            {{ message.text }}
                        </div>
                        <div :class="['message-time', message.type]">
                            {{ message.time }}
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="OTO-userWrite">
            <input v-model="newMessage" @keyup.enter="sendMessage" type="text" class="user-input" placeholder="메시지를 입력하세요...">
            <button @click="sendMessage" class="send-button">전송</button>
        </div>
    </div>
</template>


<script>
export default {
    data() {
        return {
            ws: null, // 웹소켓을 위한 변수
            newMessage: '',
            messages: [
                { type: 'consultant', profileImage: '/img/noimage.png', text: '안녕하세요, 어떻게 도와드릴까요?', time: this.getCurrentDateTime() },
                { type: 'user', profileImage: '/img/noimage.png', text: '안녕하세요, 상담을 받고 싶습니다.', time: this.getCurrentDateTime() },
                { type: 'user', profileImage: '/img/noimage.png', text: '제가 궁금한 점이 있습니다.', time: this.getCurrentDateTime() },
                { type: 'consultant', profileImage: '/img/noimage.png', text: '그게 무엇인가요?', time: this.getCurrentDateTime() },
            ],
            roomnum: null,
            token:null,
        };
    },
    methods: {
        connect() { //웹소켓 연결 시도
            this.token = "your_token_value_here";
            this.ws = new WebSocket('ws://192.168.0.13/ws/chat',this.token); // 서버 URL에 맞게 수정 필요
            this.ws.onmessage = (event) => {
            this.onMessage(event);
            };
            this.ws.onopen = () => this.onOpen();
            this.ws.onerror = (error) => this.onError(error);
            this.ws.onclose = () => this.onClose();
        },
        sendMessage() {
            if (this.newMessage.trim() !== '') {
                this.messages.push({ type: 'user', profileImage: '/img/noimage.png', text: this.newMessage, time: this.getCurrentDateTime()});
                this.ws.send(this.newMessage);
                
                this.newMessage = '';
                this.$nextTick(() => {
                    this.scrollToEnd();
                });
                
            };
        },
        onMessage(event) {
            console.log('Message received: ', event.data);
            const message = {
                text: event.data,
                id: Date.now()
            };
            console.log(message.text)
            this.messages.push({type: 'consultant',profileImage: '/img/noimage.png', text: message.text, time: this.getCurrentDateTime()});
            this.scrollToEnd();
        },
        getCurrentDateTime() {
            const now = new Date();
            const day = now.getDate().toString().padStart(2, '0');
            const month = (now.getMonth() + 1).toString().padStart(2, '0'); // Months are 0-based
            const year = now.getFullYear();
            const hours = now.getHours().toString().padStart(2, '0');
            const minutes = now.getMinutes().toString().padStart(2, '0');
            return `${year}-${month}-${day} ${hours}:${minutes}`;
        },
        scrollToEnd() {
            const container = this.$el.querySelector('.chat-container');
            container.scrollTop = container.scrollHeight;
        },
        onOpen() { //연결 성공시
            console.log('Connected to the WebSocket server.');
            
        },
        onError(error) { //소켓 에러시
            console.error('WebSocket error:', error);
        },
        onClose() { //연결이 끊기는 경우
            console.log('Disconnected from the WebSocket server.');
        },
    },
    mounted() {
        this.connect();
        this.scrollToEnd();
    },
};
</script>