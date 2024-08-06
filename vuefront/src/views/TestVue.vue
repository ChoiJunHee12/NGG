<template>
    <div>
      <textarea v-model="text" placeholder="Enter text here"></textarea>
      <button @click="convertTextToSpeech">Convert to Speech</button>
      <!-- <audio> 태그를 숨김 -->
      <audio ref="audio" style="display: none;"></audio>
      <button @click="audio">TTS 실행</button>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        text: '',
      };
    },
    methods: {
      async convertTextToSpeech() {
        try {
          const response = await axios.post(
            'http://192.168.0.26:9000/tts/text_to_speech/',
            { text: this.text },
            { responseType: 'blob' }
          );
  
          const url = window.URL.createObjectURL(new Blob([response.data], { type: 'audio/mpeg' }));
          this.$refs.audio.src = url;
          this.$refs.audio.play();
        } catch (error) {
          console.error('Error converting text to speech:', error);
          alert('Failed to convert text to speech');
        }
      },
    },
  };
  </script>
  