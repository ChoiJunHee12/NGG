<template>
  <div class="container">
    <div class="ProfileTitle">
      <blockquote class="blockquote-profile">
        <b> <p>내 정보</p></b>
      </blockquote>
    </div>
    <div class="user-container">
      <div class="user-profile-header">
        <img
          class="profile-image"
          src="/img/MakeUp_image/남_summer_2.png"
          :alt="myprofile.mname"
        />
        <div class="header-info">
          <div>
            <h1 class="name">
              {{ myprofile.mname }}
              <span class="gender">({{ myprofile.mgender }})</span>
              <h2 class="title">{{ myprofile.mnick }}</h2>
            </h1>
          </div>
          <button class="edit-user-profile-button" @click="handleEditClick">
            프로필 수정
          </button>
        </div>
      </div>

      <div class="user-profile-tab-container">
        <button
          :class="['tab', { active: activeTab === 'info' }]"
          @click="activeTab = 'info'"
        >
          기본 정보
        </button>
      </div>

      <section v-if="activeTab === 'info'">
        <div class="user-profile-section">
          <h3 class="user-profile-section-title">기본 정보</h3>
          <div class="info-grid">
            <div class="info-item">
              <span class="info-icon">📞</span>
              <span class="info-text">{{ myprofile.mphoneno }}</span>
            </div>
            <div class="info-item">
              <span class="info-icon">✉️</span>
              <span class="info-text">{{ myprofile.memail }}</span>
            </div>
            <div class="info-item">
              <span class="info-icon">🎂</span>
              <span class="info-text">{{ myprofile.mbirth }}</span>
            </div>
            <div class="info-item">
              <span class="info-icon">📍</span>
              <span class="info-text">{{ myprofile.prefarea }}</span>
            </div>
            <div class="info-item">
              <span class="info-icon">👔</span>
              <span class="info-text">{{ myprofile.applpart }}</span>
            </div>
          </div>
        </div>
        <div class="user-profile-section">
          <h3 class="user-profile-section-title">학력</h3>
          <div class="education-item">
            <span class="year">{{ myedu.ugraduate }}</span>
            <div class="details">
              <div class="school">{{ myedu.university }}</div>
              <div class="degree">학사 in {{ myedu.umajor }}</div>
            </div>
          </div>
          <div class="education-item">
            <span class="year">{{ myedu.mgraduate }}</span>
            <div class="details">
              <div class="school">{{ myedu.master }}</div>
              <div class="degree">석사 in {{ myedu.mmajor }}</div>
            </div>
          </div>
        </div>
      </section>
    </div>

    <div :class="['user-profile-modal', { open: isModalOpen }]">
      <div class="modal-content">
        <div class="modal-header">
          <h2>프로필 수정</h2>
          <button @click="handleCloseModal">&times;</button>
        </div>
        <div class="modal-body">
          <form @submit.prevent="handleSubmit">
            <div class="form-group">
              <label for="name">🍀 이름</label>
              <input
                type="text"
                id="name"
                name="name"
                v-model="myprofile.mname"
                disabled
              />
            </div>
            <div class="form-group">
              <label for="nickname">❤️ 닉네임</label>
              <input
                type="text"
                id="nickname"
                name="nickname"
                v-model="tempProfile.mnick"
              />
            </div>
            <div class="form-group">
              <label for="title">👔 지원 분야</label>
              <input
                type="text"
                id="title"
                name="title"
                v-model="tempProfile.applpart"
              />
            </div>
            <div class="form-group">
              <label for="pwd">✅비밀번호</label>
              <input
                type="password"
                id="pwd"
                name="pwd"
                v-model="tempProfile.mpwd"
              />
            </div>
            <div class="form-group">
              <label for="phone">📞 전화번호</label>
              <input
                type="tel"
                id="phone"
                name="phone"
                v-model="tempProfile.mphoneno"
              />
            </div>
            <div class="form-group">
              <label for="email">✉️ 이메일</label>
              <input
                type="email"
                id="email"
                name="email"
                v-model="tempProfile.memail"
              />
            </div>
            <div class="form-group">
              <label for="birthYear">🎂 생년월일</label>
              <input
                type="text"
                id="birthYear"
                name="birthYear"
                v-model="myprofile.mbirth"
                disabled
              />
            </div>
            <div class="form-group">
              <label for="area">📍 선호 지역</label>
              <input
                type="text"
                id="area"
                name="area"
                v-model="tempProfile.prefarea"
              />
            </div>
            <div class="modal-footer">
              <button type="submit" class="user-profile-button">저장</button>
              <button
                type="button"
                class="cancel-user-profile-button"
                @click="handleCloseModal"
              >
                취소
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      activeTab: "info",
      isModalOpen: false,
      myprofile: {},
      myedu: {},
      tempProfile: {},
    };
  },
  mounted() {
    this.fetchMemberDetails();
    this.fetchMemberEduDetails();
  },
  methods: {
    fetchMemberDetails() {
      axios
        .get("http://localhost/mydream/mypage/profile?mnum=3")
        .then((response) => {
          this.myprofile = response.data;
          console.log(response.data);
        })
        .catch((error) => {
          console.error("에러발생 에러발생");
        });
    },
    fetchMemberEduDetails() {
      axios
        .get("http://localhost/mydream/mypage/eduprofile?mnum=3")
        .then((response) => {
          this.myedu = response.data;
          console.log(response.data);
        })
        .catch((error) => {
          console.log("학력에러발생");
        });
    },
    handleEditClick() {
      this.tempProfile = { ...this.myprofile };
      this.isModalOpen = true;
    },
    handleCloseModal() {
      this.isModalOpen = false;
    },
    handleSubmit() {
      axios
        .put("http://localhost/mydream/mypage/profile?mnum=3", this.tempProfile)
        .then((response) => {
          console.log("Profile updated successfully:", response.data);
          alert("프로필이 성공적으로 업데이트되었습니다.");

          this.myprofile = { ...this.tempProfile };
        })
        .catch((error) => {
          console.error("Error updating profile:", error);
          alert("프로필 업데이트 중 오류가 발생했습니다.");
        });
      // 여기에서 form 데이터를 제출하는 로직을 추가하세요.
      // console.log("Profile updated:", this.profileData);
      this.isModalOpen = false;
    },
  },
};
</script>

<style>
.container {
  max-width: 900px;
  margin: 0 auto;
  padding: 40px 20px;
  font-family: "NanumSquare", sans-serif;
  background-color: #f8f9fa;
  border-radius: 5px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.blockquote-profile {
  display: block;
  background: #fff;
  padding: 20px 20px 10px 45px;
  margin: 0% 5%;
  position: relative;

  /*Font*/
  font-size: 0.35em;
  line-height: 1.5;
  color: #11045e;

  /*Box Shadow - (Optional)*/
  -moz-box-shadow: 2px 2px 15px #ccc;
  -webkit-box-shadow: 2px 2px 15px #ccc;
  box-shadow: 2px 2px 15px #ccc;

  /*Borders - (Optional)*/
  border-left-style: solid;
  border-left-width: 15px;
  /* border-right-style: solid; */
  border-right-width: 0px;
}

.ProfileTitle {
  width: 100%;
  margin: auto;
  margin-bottom: 4%;
  text-align: left;
  color: #ffffff;
  font-size: 5em;
}

.user-profile-header {
  width: 80%;
  display: flex;
  align-items: center;
  margin-bottom: 30px;
}

.profile-image {
  width: 180px;
  height: 180px;
  border-radius: 50%;
  object-fit: cover;
  margin-left: 40px;
  margin-right: 30px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.header-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 610px;
}

.name {
  font-size: 1.5rem;
  font-weight: bold;
  color: #2c3e50;
  margin: 0 0 5px 0;
}

.gender {
  font-size: 1.2rem;
  color: #7f8c8d;
}

.title {
  font-size: 1.2rem;
  font-weight: bold;
  color: #9da2a8;
  margin: 0;
}

.user-profile-tab-container {
  width: 90%;
  display: flex;
  margin-bottom: 30px;
  border-bottom: 2px solid #e0e0e0;
  /* width: 860px; */
}

.tab {
  padding: 10px 20px;
  font-size: 1.2rem;
  font-weight: 600;
  background: none;
  border: none;
  color: #7f8c8d;
  cursor: pointer;
  transition: all 0.3s ease;
}

.tab.active {
  color: #1659de;
  font-weight: 900;
  border-bottom: 5px solid #003d8c;
}

.tab:hover {
  color: #3498db;
}

section {
  width: 90%;
}

.user-profile-section {
  background-color: white;
  padding: 25px;
  margin-bottom: 25px;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
  /* width: 810px; */
}

.user-profile-section-title {
  font-size: 1.1rem;
  font-weight: bold;
  color: #2c3e50;
  margin-bottom: 20px;
  padding-bottom: 10px;
  border-bottom: 2px solid #ecf0f1;
}

.info-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 20px;
}

.info-item {
  display: flex;
  align-items: center;
}

.info-icon {
  font-size: 1.1rem;
  margin-right: 10px;
}

.info-text {
  font-size: 1.1rem;
  color: #34495e;
}

.tag-container {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-bottom: 20px;
}

.tag {
  background-color: #1659de;
  color: #ffffff;
  padding: 5px 10px;
  border-radius: 8px;
}

.education-item {
  display: flex;
  margin-bottom: 20px;
}

.year {
  font-weight: bold;
  margin-right: 20px;
  min-width: 60px;
  font-size: 1.1rem;
}

.details {
  flex: 1;
  text-align: left;
}

.school {
  font-weight: bold;
  margin-bottom: 5px;
  font-size: 1.1rem;
  text-align: left;
}

.degree {
  color: #7f8c8d;
  text-align: left;
}

.introduce {
  font-size: 1.1rem;
  line-height: 1.6;
  color: #34495e;
  text-align: left;
}

.edit-user-profile-button {
  margin-left: 40%;
  padding: 12px 10px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-weight: bold;
  width: 180px;
  height: 50px;
  font-size: 1.1rem;
  background-color: #102669;
  color: #fff;
  transition: background-color 0.5s, color 0.5s;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
}

.edit-user-profile-button:hover {
  background-color: #ffffff;
  color: #102669;
}

.user-profile-modal {
  display: none;
  position: fixed;
  z-index: 1000;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0, 0, 0, 0.5);
  justify-content: center;
  align-items: center;
}

.user-profile-modal.open {
  display: flex;
}

.modal-content {
  background-color: #fefefe;
  padding: 20px;
  border-radius: 12px;
  width: 80%;
  max-width: 700px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.3);
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.modal-header button {
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
  color: #7f8c8d;
}

.modal-header h2 {
  color: #2c3e50;
  text-align: center;
  font-weight: 600;
}

.modal-header user-profile-button {
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
  color: #7f8c8d;
}

.modal-body {
  margin-bottom: 20px;
}

.modal-footer {
  display: flex;
  justify-content: flex-end;
}

.form {
  display: flex;
  flex-direction: column;
}

.form-group {
  margin-bottom: 10px;
  display: flex;
}

.form-group label {
  display: flex;
  margin-bottom: 5px;
  font-weight: bold;
  color: #2c3e50;
  flex: 5 0 30%;
  justify-content: flex-start;
  align-items: center;
}

.form-group input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
}
.form-group textarea {
  width: 100%;
}

.user-profile-button {
  background-color: #1659de;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 8px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-left: 10px;
}

.user-profile-button:hover {
  background-color: #003d8c;
}

.cancel-user-profile-button {
  background-color: #de1616;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 8px;
  font-size: 1rem;
  font-weight: 600;
  cursor: pointer;
  transition: background-color 0.3s ease;
  margin-left: 10px;
}

.cancel-user-profile-button:hover {
  background-color: #8c0000;
}
</style>
