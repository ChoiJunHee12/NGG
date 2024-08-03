<template>
    <div class="container2">
        <div class="CTtitle" 
            data-aos="zoom-out"
            data-aos-easing="linear"
            data-aos-duration="1500">
            <blockquote class="blockquote-CT"><b>
                <h1><span class="highlight-bold">내가꿈</span>과 함께하는 <span class="highlight">전문 컨설턴트</span></h1>
                <p><span class="highlight">7개 분야</span>의 전문 <span class="highlight-bold">컨설턴트</span>가 당신의 꿈을 향한 </p>
                <p>여정을 함께합니다.</p></b>
            </blockquote>
            </div>
          </div>
    <div class="container">

      
        <div class="section secondary-section" id="portfolio">
          <div class="triangle"></div>
          <div class="info-container">

            
            
            <div v-for="(project, index) in filteredProjects" :key="index" class="toggleDiv row-fluid single-project" :id="'slidingDiv' + index" v-show="activeProjectIndex === index">
              <div class="span consultantImage">
                <img :src="project.image" :alt="'project ' + (index + 1)" />
              </div>
              <div class="span6">
                <div class="project-description">
                  <div class="project-title clearfix">
                    <h2>{{ project.title }}&nbsp;{{ project.name }}</h2>
                    <span class="show_hide close" @click="hideProject">
                      X
                    </span>
                  </div>
                  <div class="project-info">
                    <div><span>Name : &nbsp;</span>{{ project.name }}</div>
                    <div><span>Location : &nbsp;</span>{{ project.loc }}</div>
                    <div><span>Information : &nbsp;</span>{{ project.information }}</div>
                    <div><span>Email : &nbsp;</span><a :href="project.email">{{ project.email }}</a></div>
                    <div><span> [ 약력 ] </span></div>
                  <ul>
                  <li class="project-profile" v-for="(desc, descIndex) in project.description" :key="descIndex">
                        {{ desc }}
                      </li>
                      
                    </ul>
                    <button
                  class="application-btn"
                  id=""
                  type="button"
                  @click="application"
                >신청</button>
                  </div>
                </div>
              </div>
            </div>
            <ul class="nav nav-pills">
              <li class="filter" :class="{ active: currentFilter === 'all' }" @click="setFilter('all')">
                <a href="#noAction">All</a>
              </li>
              <li class="filter" :class="{ active: currentFilter === 'web' }" @click="setFilter('web')">
                <a href="#noAction">회계/재무</a>
              </li>
              <li class="filter" :class="{ active: currentFilter === 'photo' }" @click="setFilter('photo')">
                <a href="#noAction">교육</a>
              </li>
              <li class="filter" :class="{ active: currentFilter === 'identity' }" @click="setFilter('identity')">
                <a href="#noAction">It/개발</a>
              </li>
              <li class="filter" :class="{ active: currentFilter === '' }" @click="setFilter('identity')">
                <a href="#noAction">It/개발</a>
              </li>
              <li class="filter" :class="{ active: currentFilter === '' }" @click="setFilter('identity')">
                <a href="#noAction">It/개발</a>
              </li>
              <li class="filter" :class="{ active: currentFilter === '' }" @click="setFilter('identity')">
                <a href="#noAction">It/개발</a>
              </li>
              <li class="filter" :class="{ active: currentFilter === '' }" @click="setFilter('identity')">
                <a href="#noAction">It/개발</a>
              </li>
            </ul>
            <ul id="portfolio-grid" class="thumbnails row">
              <li v-for="(project, index) in filteredProjects" :key="index" :class="['span4', 'mix', project.category.toLowerCase()]">
                <div class="thumbnail">
                  <img :src="project.image" :alt="'project ' + (index + 1)">
                  <div class="mask">
                    <button class="more show_hide" @click="showProject(index)">
                      + more..
                    </button>
                  </div>
                  <div class="caption">
                    <h3>{{ project.title }}{{ project.name }}</h3>
                    <p>{{ truncateText(project.information, 60) }}</p>
                    <ul class="profile-list"> -{{project.name}} 약력 -
                      <li v-for="(desc, descIndex) in project.description.slice(0, 3)" :key="descIndex">
                        {{ desc }}
                      </li>
                      <li v-if="project.description.length > 3">...</li>
                    </ul>
                  </div>
                </div>
              </li>
            </ul>
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
    data() {
      return {
        currentFilter: 'all',
        projects: [
        {
            image: 'img/ConsultantInfo_image/ct1.png',
            title: '[회계/재무]',
            name: '김드림',
            loc: '서울',
            information: '전문성있는 컨설턴트가 당신의 꿈을 응원합니다.',
            email: 'http://examplecomp.com',
            description: [ '드림대학교 졸업', '드림대학원 졸업', '드림기업 회계담당인데길면어떻게됨 더길어서 넘치면 어떻게 되는지 보는 예시임', '꿈의기업 회계팀장', '내가꿈 회계 전문 컨설턴트'],
            category: 'web'
          },
          {
            image: 'img/ConsultantInfo_image/ct2.png',
            title: '[교육]',
            name: '이드림',
            loc: '경주',
            information: '이드림이라면 믿고 맡길 수 있습니다!',
            email: 'http://examplecomp.com',
            description: ['나의대학교 졸업', '꿈의대학원 졸업','드림학원 강사', '꿈의학원 선임강사', '내가꿈 교육 전문 컨설턴트'],
            category: 'photo'
          },
          {
            image: 'img/ConsultantInfo_image/ct3.png',
            title: '[IT/개발]',
            name: '공드림',
            loc: '경기',
            information: '꼼꼼한 멘토링, 탄탄한 취업길',
            email: 'http://examplecomp.com',
            description: ['꿈의대학교 졸업', '뫄뫄대학원 졸업','드림기업 개발팀', '꿈의기업 개발팀', '내가꿈 IT 전문 컨설턴트'],
            category: 'identity'
          },
          {
            image: 'img/ConsultantInfo_image/ct4.png',
            title: '[회계/재무]',
            name: '박드림',
            loc: '전남',
            information: '당신의 가치를 키워주는 멘토',
            email: 'http://examplecomp.com',
            description: ['드림대학교 졸업', '드림대학원 졸업','드림기업 회계담당', '꿈의기업 회계팀장', '내가꿈 회계 전문 컨설턴트'],
            category: 'web'
          },
          {
            image: 'img/ConsultantInfo_image/ct5.png',
            title: '[교육]',
            name: '최드림',
            loc: '제주',
            information: '함께 꿈을 꾸며 커리어를 쌓을 수 있게 돕습니다.',
            email: 'http://examplecomp.com',
            description: ['나의대학교 졸업', '꿈의대학원 졸업','드림학원 강사', '꿈의학원 선임강사', '내가꿈 교육 전문 컨설턴트'],
            category: 'photo'
          },
          {
            image: 'img/ConsultantInfo_image/ct6.png',
            title: '[IT/개발]',
            name: '나드림',
            loc: '충북',
            information: '등대지기처럼 당신의 꿈을 향한 길을 밝게 비추는 나드림 컨설턴트',
            email: 'http://examplecomp.com',
            description: ['꿈의대학교 졸업', '뫄뫄대학원 졸업','드림기업 개발팀', '꿈의기업 개발팀', '내가꿈 IT 전문 컨설턴트'],
            category: 'identity'
          },
          {
            image: 'img/ConsultantInfo_image/ct7.png',
            title: '[회계/재무]',
            name: '하드림',
            loc: '경기',
            information: '면접의 모든것, 하드림이 낱낱히 파헤쳐드립니다.',
            email: 'http://examplecomp.com',
            description: [ '드림대학교 졸업', '드림대학원 졸업', '드림기업 회계담당', '꿈의기업 회계팀장', '내가꿈 회계 전문 컨설턴트'],
            category: 'web'
          },
          {
            image: 'img/ConsultantInfo_image/ct8.png',
            title: '[교육]',
            name: '장드림',
            loc: '서울',
            information: '당신의 장점을 더욱 키울 수 있는 컨설턴트와 함께 하셔야합니다.',
            email: 'http://examplecomp.com',
            description: ['나의대학교 졸업', '꿈의대학원 졸업', '드림학원 강사', '꿈의학원 선임강사', '내가꿈 교육 전문 컨설턴트'],
            category: 'photo'
          },
          {
            image: 'img/ConsultantInfo_image/ct9.png',
            title: '[IT/개발]',
            name: '전드림',
            loc: '대전',
            information: '목표지점까지 페이스메이커가 되어드리겠습니다.',
            email: 'http://examplecomp.com',
            description: ['꿈의대학교 졸업', '뫄뫄대학원 졸업','드림기업 개발팀', '꿈의기업 개발팀', '내가꿈 IT 전문 컨설턴트'],
            category: 'identity'
          },
          // 나머지 프로젝트 데이터...
        ],
        activeProjectIndex: null,
      };
    },
    computed: {
    filteredProjects() {
      if (this.currentFilter === 'all') {
        return this.projects;
      }
      return this.projects.filter(project => project.category.toLowerCase() === this.currentFilter);
    }
  },
  methods: {
  setFilter(filter) {
    this.currentFilter = filter;
    this.hideProject();
  },
  showProject(index) {
    this.activeProjectIndex = index;
    this.$nextTick(() => {
      const target = document.querySelector(`#slidingDiv${index}`);
      if (target) {
        target.scrollIntoView({ behavior: 'smooth' });
      }
    });
  },
  hideProject() {
    this.activeProjectIndex = null;
  },
  truncateText(text, maxLength) {
    return text.length > maxLength ? text.substring(0, maxLength) + '...' : text;
  },
  application(){
    alert("컨설턴트가 배정되었습니다. 이제 AI 모의면접을 보면 컨설턴트의 코칭을 받을 수 있습니다.")
  }
}
}
</script>
