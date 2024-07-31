<template>
<div class="container">
<div class="cv-container">
    <div class="CVtitle">
        <blockquote class="blockquote-CV"><b>
            <p>이력서관리</p></b>
        </blockquote>
    </div>
    <!-- <div class="cv-subtitle">내 이력서</div> -->
<div class="cv-notice">
    <p>📢&nbsp; 답변 시간은 컨설턴트 상황에 따라 다르게 소요되며 욕설 및 비방을 한 사용자는 경고 조치 없이 불이익을 받을 수 있습니다.</p>
</div>

    <div class="cv-listform" v-if="show">
        
        <div class="cv-list">
            <table class="cv-table" >
            <tr class="cv-tr1">
                <th>이력서 제목</th>
                <th>관리</th>
                <th>수정 날짜</th>

            </tr>
<!-- 이쪽 데이터받으면 for 문-->
            <tr class="cv-tr2" >
                
            <td><div class="cv-cvtitle" @click="updatecv()">이거쓴 내 첫번째 이력서여....</div></td>
                <td><button class="cv-upbtn" @click="updatecv()">수정</button>
            <button class="cv-delbtn">삭제</button></td>
            <td><div class="cv-update-date">2024-07-25</div></td>
        </tr>
        <!--  -->
        <tr class="cv-tr2" >
                
            <td><div class="cv-cvtitle" @click="updatecv()">이거쓴 내 두번째 이력서여....</div></td>
                <td><button class="cv-upbtn" @click="updatecv()">수정</button>
            <button class="cv-delbtn">삭제</button></td>
            <td><div class="cv-update-date">2024-07-25</div></td>
        </tr>
        <tr class="cv-tr2">
                
                <td><div class="cv-cvtitle" @click="updatecv()">이거쓴 내 삼력서여....</div></td>
                    <td><button class="cv-upbtn" @click="updatecv()">수정</button>
                <button class="cv-delbtn">삭제</button></td>
                <td><div class="cv-update-date">2024-07-25</div></td>
            </tr>
            <tr class="cv-tr2">
                
                <td><div class="cv-cvtitle" @click="updatecv()">이거쓴 사력서여....</div></td>
                    <td><button class="cv-upbtn" @click="updatecv()">수정</button>
                <button class="cv-delbtn">삭제</button></td>
                <td><div class="cv-update-date">2024-07-25</div></td>
            </tr>
    </table>
        </div>
        
    </div>
    <div class="cv-listform-nolist" v-if="!show">
        <img class="cv-nolist-img" src="\img\cv_nolist.png">
        <div class="cv-nolist-text">
            작성된 이력서가 없습니다.
        </div>
    </div>
    
    <div class="cv-dwrite">
    <router-link to="cvwrite"><button class="cv-writebtn">이력서 작성</button></router-link>
</div>


</div>  
</div>
</template>
<script>
export default {
    data() {
        return {
            show:true,
        }
    },
    methods:{
        updatecv(){
            this.$router.push({ name: "cvwrite" });//나중에 파라미터 넣어주세요
        }
    },
}
</script>