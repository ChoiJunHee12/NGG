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
                    <div class="OTO-profile">
                        <img :src="message.profileImage" :alt="message.type" class="chat-profile-image" >
                        <div class="OTO-name">{{ message.name }}</div>
                    </div>
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
            newMessage: '',
            messages: [
                { name : '최컨설', type: 'consultant', profileImage: '/img/noimage.png', text: '안녕하세요, 어떻게 도와드릴까요?', time: this.getCurrentDateTime() },
                { name : '김면접', type: 'user', profileImage: '/img/noimage.png', text: '안녕하세요, 상담을 받고 싶습니다.', time: this.getCurrentDateTime() },
                { name : '김면접', type: 'user', profileImage: '/img/noimage.png', text: '제가 궁금한 점이 있습니다.', time: this.getCurrentDateTime() },
                { name : '최컨설', type: 'consultant', profileImage: '/img/noimage.png', text: '그게 무엇인가요?', time: this.getCurrentDateTime() },
            ]
        };
    },
    methods: {
        sendMessage() {
            if (this.newMessage.trim() !== '') {
                this.messages.push({ name : '김면접', type: 'user', profileImage: '/img/noimage.png', text: this.newMessage, time: this.getCurrentDateTime()});
                this.newMessage = '';
                this.$nextTick(() => {
                    this.scrollToEnd();
                });
            }
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
        }
    },
    mounted() {
        this.scrollToEnd();
    },
};
</script>