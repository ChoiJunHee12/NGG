<template>
    <div class="container"  style="font-size:20px;">
        <div class="w3-container w3-light-grey">
            <h2 class="personal-title">개인정보 수정</h2>
            <div style="display:flex;">
                <div class="mypage-right">
                    <form action="">
                        <p><i class="bi bi-person-circle"></i>&nbsp; |&nbsp; 이름</p><hr>
                        <p><i class="bi bi-envelope"></i>&nbsp; |&nbsp; cntjdgh0918@naver.com</p><hr>
                        <p><i class="bi bi-key"></i>&nbsp; |&nbsp; <span> <input type="text" value="*******"></span></p><hr>
                        <p><i class="bi bi-gender-ambiguous"></i>&nbsp; |&nbsp; 남성</p><hr>
                        <p><i class="bi bi-cake2"></i>&nbsp; |&nbsp; 1998-09-18</p><hr>
                        <p><i class="bi bi-geo-alt-fill"></i>&nbsp; |&nbsp; 서울</p><hr>
                        <p><i class="bi bi-palette"></i>&nbsp; |&nbsp; <router-link :to="{ name: 'PersonalCol_Test' }" class="main-router" style="color:black;"  >가을 웜톤</router-link></p><hr>
                        <p><i class="bi bi-pen"></i>&nbsp; |&nbsp; 2024-07-25</p>                                    
                    </form>
                    <div style="text-align:center;">
                    <button class="btn btn-next personal-btn">수정</button>
                    </div>                               
                </div>                
            </div>
        </div>
    </div>
</template>
<style>
.mypage-content {
    display: flex;
    justify-content: space-between;
}

.mypage-left {
    border-right: 2px solid rgba(0, 0, 0, 0.1); /* 선 추가 */
    padding-right: 20px; /* 오른쪽 여백 추가 */
}

.mypage-right {
    width: 750px;
    border-radius: 10px;
    padding: 20px 50px 20px 30px;
    text-align: left;
    background-color: #F1F5FB;
    margin: 50px;
}

.mypage-profile {
    width: 150px;
    height: 150px;
    border-radius: 70%;
    overflow: hidden;
    border: 2px solid #f1ebe7; /* 테두리 추가 */
    margin: 20px;
}

.mypage-profile img {
    width: 150px;
    height: 150px;
}

</style>
<script>
export default {
    data() {
        return {
            imagePreview: null,
            detail: {},
        }
    },
    methods: {
        changeImage(){
            this.$refs.fileInput.click();
        },
        handleFileUpload(event) {
            const file = event.target.files[0];
            if (file) {
                const reader = new FileReader();
                reader.onload = (e) => {
                this.imagePreview = e.target.result;
                };
                reader.readAsDataURL(file);
            }
        },
    },
}
</script>