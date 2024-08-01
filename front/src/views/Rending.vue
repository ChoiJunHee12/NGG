<template>
  
    <div class="section">
        <div class="video-section">
      <img src="img/Main_image/Nervous.gif" class="landing-img">
    </div>
    <div class="image-section">

<div class="container1">
  <div data-aos="fade-up">
  <blockquote class="blockquote-main"><b>
    <p>어린 시절 소중히 간직해온 <span class="highlight">나의 꿈</span></p>
    <p>소중한 꿈을 이루기 위한 한걸음을 <span class="highlight">내가 꿈</span>과 함께 내딛으세요</p></b>
  </blockquote>
</div>
  <div class="talk">
<div class="talk_left" data-aos="fade-right" data-aos-easing="linear"
data-aos-duration="1000">
  <img src="/img/Main_image/2.png" class="info-img">

  <div class="t_person_txt">
	<p>어디부터 어떤 준비를 해야할지 너무 막막해</p>
</div>
</div>
<div class="talk_right" data-aos="fade-left" data-aos-easing="linear"
data-aos-duration="1000">
<div class="t_person_txt">
  <p>간편하면서 경쟁력있게 면접 준비를 할 수는 없을까?</p>
</div>
<img src="/img/Main_image/1.png" class="info-img" >
</div>
</div>

<div class="interview" data-aos="fade-up">
    <img src="/img/Main_image/3.png" class="info-img" data-aos-easing="linear"
    data-aos-duration="600">
</div>

<div class="p-style"  data-aos="zoom-in" data-aos-easing="linear"
data-aos-duration="1000">

<blockquote class="blockquote-main">
    <p>내 가장 소중한 꿈을 향한 도전!</p>
    <p>내가꿈 AI와 함께 준비하세요</p>
  </blockquote>
  <p>처음부터 단계별로 차근차근 준비해가는 면접 준비</p>
  <p>내가꿈에서 당신의 꿈을 향한 여정을 함께합니다.</p>
</div>
<div class="information info-left" data-aos="fade-right" data-aos-easing="linear"
data-aos-duration="900">
    <p class="p-point"> point 1. </p>
    <p class="p-text"> 1:1로 꼼꼼하게 관리해주는 전문 컨설턴트</p>
    <img src="/img/Main_image/main2.png" class="info-img">
</div>

<div class="information info-right" data-aos="fade-left" 
      data-aos-easing="linear"
      data-aos-duration="900">
    <p class="p-point"> point 2. </p>
    <p class="p-text"> 전문 컨설턴트가 제공하는 인성면접/직무면접 질문을 캠으로 바로 연습해보는 AI 모의면접</p>
    <img src="/img/Main_image/main1.png" class="info-img">
</div>

<div class="information info-left" data-aos="fade-right" 
            data-aos-easing="linear"
            data-aos-duration="1500">
    <p class="p-point"> point 3. </p>
    <p class="p-text"> AI가 빠르고 정확하게 분석해주는 면접결과</p>
    <img src="/img/Main_image/main4.png" class="info-img">
</div>

<div class="information info-right" data-aos="fade-left" data-aos-easing="linear"
data-aos-duration="1500">
    <p class="p-point"> point 4. </p>
    <p class="p-text"> AI를 활용한 이미지메이킹</p>
    <img src="/img/Main_image/main3.png" class="info-img">
</div>

<div class="information"  
data-aos="fade-up"
data-aos-anchor-placement="top-bottom">
    <p class="p-point-end" style="text-align: center;">지금, 내가꿈에서 당신의 꿈을 꾸어보세요. </p>
    <div class="btns">
      <router-link to="login">
    <button class="joinAndLogin" id="join-btn" type="button">
      가입 / 로그인하기</button></router-link>
    </div>
</div>
<br>
</div>
</div>
  
</div>
  </template>
  
  <script>
  import AOS from 'aos';
  import "aos/dist/aos.css";
  export default {
    created() {
        AOS.init();
    },
  };
  </script>
  
  <style>
  </style>
  