<template>
    <div class="container">
        <div class="w3-container w3-light-grey">
            <h2 class="personal-title">MakeUp Yourself <i class="bi bi-info-circle" style="font-size: 0.7em;" data-bs-toggle="popover"
            title="메이크업을 해보세요"
            data-bs-content="하단의 MakUp 이미지를 클릭하여 피부톤에 맞는 화장 전 후를 비교해 보세요."></i></h2>
        </div>
        <div class="personal-flex-container">
            <div class="personal-makeup-img">
            <p>Before MakeUp</p>
            <img :src="beforeImage">          
            </div>
            <div class="personal-makeup-img">
            <p>After MakeUp</p>
            <img :src="afterImage">
            </div>
        </div>
        <!-- <div class="personal-btn-grp"> -->
        <button type="button" class="btn btn-next personal-btn" @click="historyUpdate" >
            프로필 사진 업데이트
        </button>
        <!-- </div> -->

        <div id="personal-target" class="personal-makeupSlide">
            <swiper :modules="modules" 
                    :slides-per-view="3" 
                    :space-between="50" navigation
                    :pagination="{ clickable: true, el: '.swiper-pagination-custom' }" 
                    :scrollbar="{ draggable: true }"
                    :loop="true" 
                    @swiper="onSwiper" 
                    @slideChange="onSlideChange"
                    style="padding:20px ">
            <swiper-slide v-for="index in 4" :key="index" >
                <p style="font-size:20px; font-weight:bold;">{{ translatedSeason }} {{ translatedGender }} MakeUp {{ index }}</p><br>
                <img :src="getMakeupImage(index)" class="personal-target-img" >
            </swiper-slide>
            <div class="swiper-pagination-custom"></div> <!-- Pagination을 위한 요소 -->
            </swiper>
        </div>
    </div>
</template>

<script>
import { Navigation, Pagination, A11y } from 'swiper/modules';
import { Swiper, SwiperSlide } from 'swiper/vue';
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';
import { Popover } from 'bootstrap'; // Bootstrap에서 Popover 가져오기

export default {
    data() {
        return {
            files: [],
            croppedImageSrc: localStorage.getItem('croppedImage'),
            modules: [Navigation, Pagination, A11y],
            season: 'summer', 
            mgender: '여', 
            imgpath: '/img/before_image/',
            beforeImage: localStorage.getItem('befimg'),
            afterImage: localStorage.getItem('befimg'),
            loadingImage: '/img/MakeUp_image/loading.gif',
        }
    },
    computed: {
        translatedSeason() {
            const seasonMap = {
                spring: '봄',
                summer: '여름',
                fall: '가을',
                winter: '겨울'
            };
            return seasonMap[this.season] || this.season;
        },
        translatedGender() {
            const genderMap = {
                '남': '남자',
                '여': '여자'
            };
            return genderMap[this.mgender] || this.mgender;
        }
    },
    methods: {
        getMakeupImage(index) {      
            const season = this.season;
            const mgender = this.mgender;
            return `/img/MakeUp_image/${mgender}_${season}_${index}.png`;
        },

    },
    mounted() {
        // Bootstrap Popover 초기화
        const popoverTriggerList = document.querySelectorAll('[data-bs-toggle="popover"]');
        popoverTriggerList.forEach((popoverTriggerEl) => {
        new Popover(popoverTriggerEl);
        });
    },
    components: {
        Swiper,
        SwiperSlide,
    },
    
};
</script>