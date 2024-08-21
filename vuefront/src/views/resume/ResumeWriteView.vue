<template>
  <div class="container">
    <div class="cv-container">
      <div class="CVtitle">
        <blockquote class="blockquote-CV">
          <b> <p>이력서작성</p></b>
        </blockquote>
      </div>
      <div class="cv-notice">
        <div class="cvwirte-upload-con">
          <!-- <img class="cvwrite-tipicon" src="\img\res_tip.png"/> -->
          <div class="cvwirte-upload-con1">
            📢 기존에 작성한 이력서를 업로드하시면 업로드한 파일을 기반하여
            아래의 이력서 형식에 맞게 데이터가 들어가게 됩니다.
          </div>
          <div class="cvwirte-upload-con2">
            입력값이 올바르게 들어가지 않는 경우가 있으므로 회원님께서 한번 더
            확인을 부탁드립니다.
          </div>
        </div>
        <div class="cvwirte-btn">
          <label className="cvwrite-input-file-button" for="input-file">
            {{ upload_text }}
          </label>
        </div>
        <input
          type="file"
          id="input-file"
          style="display: none"
          @change="uploadchange"
        />
      </div>
      <div class="form-container">
        <table class="cvwrite-cv-table">
          <tr>
            <td class="cvwrite-cv-td1">이력서 제목</td>
            <td class="cvwrite-cv-td2">
              <input
                class="cvwrite-cv-input"
                placeholder="이력서 제목을 입력하세요"
                v-model="basic.title"
              />
            </td>
          </tr>
        </table>
        <div class="cvwrite-cv1">
          <div class="cvwrite-cv1-title">인적사항</div>
          <table class="cvwrite-cv1-table">
            <tr class="cvwrite-cv1-tr">
              <td rowspan="4" class="cvwrite-cv1-td1">
                <label
                  for="inputImg"
                  style="cursor: pointer; width: 100%; height: 100%"
                >
                  <img :src="imageSrc" style="width: 100%; height: 100%" />
                </label>
                <input
                  type="file"
                  id="inputImg"
                  style="display: none"
                  @change="upprofile"
                />
              </td>

              <td class="cvwrite-cv1-td2">성명</td>
              <td>
                <input
                  type="text"
                  class="cvwrite-cv1-input"
                  placeholder="성명"
                  v-model="basic.name"
                />
              </td>
              <td class="cvwrite-cv1-td4">출생년도</td>
              <td>
                <input
                  type="text"
                  class="cvwrite-cv1-input"
                  placeholder="YYYYMMDD"
                  v-model="basic.birthymd"
                  maxlength="8"
                />
              </td>
            </tr>
            <tr class="cvwrite-cv1-tr">
              <td class="cvwrite-cv1-td2">주소</td>
              <td colspan="3">
                <input
                  type="text"
                  class="cvwrite-cv1-input"
                  placeholder="주소"
                  v-model="basic.addr"
                />
              </td>
            </tr>
            <tr class="cvwrite-cv1-tr">
              <td class="cvwrite-cv1-td2">일반 전화</td>
              <td>
                <input
                  type="text"
                  class="cvwrite-cv1-input"
                  placeholder="일반 전화"
                  v-model="basic.mphonenum"
                />
              </td>
              <td class="cvwrite-cv1-td4">휴대 전화</td>
              <td>
                <input
                  type="text"
                  class="cvwrite-cv1-input"
                  placeholder="휴대 전화"
                  v-model="basic.hphonenum"
                />
              </td>
            </tr>
            <tr>
              <td class="cvwrite-cv1-td2">이메일</td>
              <td colspan="3">
                <input
                  type="text"
                  class="cvwrite-cv1-input"
                  placeholder="이메일"
                  v-model="basic.email"
                />
              </td>
            </tr>
          </table>
        </div>

        <div class="cvwrite-cv2">
          <div class="cvwrite-cv-title">
            <div class="cvwrite-cv2-title">학력사항</div>
            <button class="cvwrite-add-button" @click="addcv2()">추가</button>
          </div>
          <table class="cvwrite-cv2-table">
            <tr class="cvwrite-cv2-tr">
              <th class="cvwrite-cv2-th1">재학기간</th>
              <th class="cvwrite-cv2-th2">학교명</th>
              <th class="cvwrite-cv2-th3">전공</th>
              <th class="cvwrite-cv2-th4">졸업여부</th>
              <th class="cvwrite-cv2-th5">학점</th>
              <th class="cvwrite-cv2-th6"></th>
            </tr>
            <tr v-for="(ed, index) in edu" :key="index">
              <!-- 여기서 데이터 받을시 for문 -->
              <td class="cvwrite-cv2-td1">
                <input
                  type="text"
                  class="cvwrite-cv-input-half-top"
                  placeholder="입학일시"
                  v-model="ed.enrollymd"
                />
              </td>
              <td class="cvwrite-cv2-td2">
                <input
                  type="text"
                  class="cvwrite-cv-input"
                  placeholder="학교명"
                  v-model="ed.schoolname"
                />
              </td>
              <td class="cvwrite-cv2-td3">
                <input
                  type="text"
                  class="cvwrite-cv-input"
                  placeholder="전공"
                  v-model="ed.major"
                />
              </td>
              <td class="cvwrite-cv2-td4">
                <input
                  type="text"
                  class="cvwrite-cv-input"
                  placeholder="졸업구분"
                  v-model="ed.gradeuateclass"
                />
              </td>
              <td class="cvwrite-cv2-td5">
                <input
                  type="text"
                  class="cvwrite-cv-input"
                  placeholder="학점"
                  v-model="ed.gradescore"
                />
              </td>
              <td class="cvwrite-cv2-td6">
                <button class="cvwrite-delbtn" @click="delcv2(index, ed.seqno)">
                  삭제
                </button>
              </td>
            </tr>
          </table>
        </div>
        <div class="cvwrite-cv3">
          <div class="cvwrite-cv-title">
            <div class="cvwrite-cv3-title">경력사항</div>
            <button class="cvwrite-add-button" @click="addcv3()">추가</button>
          </div>
          <table class="cvwrite-cv3-table">
            <tr class="cvwrite-cv3-tr">
              <th class="cvwrite-cv3-th1">재직기간</th>
              <th class="cvwrite-cv3-th2">회사명</th>
              <th class="cvwrite-cv3-th3">직종</th>
              <th class="cvwrite-cv3-th4">주요 직무</th>
              <th class="cvwrite-cv3-th5">직급/직책</th>
              <th class="cvwrite-cv3-th6"></th>
            </tr>
            <tr v-for="(ca, index) in car" :key="index">
              <!-- 여기서 데이터 받을시 for문 -->
              <td class="cvwrite-cv3-td1">
                <input
                  type="text"
                  class="cvwrite-cv-input-half-top"
                  placeholder="입사일시"
                  v-model="ca.entymd"
                />
              </td>
              <td class="cvwrite-cv3-td2">
                <input
                  type="text"
                  class="cvwrite-cv-input"
                  placeholder="회사명"
                  v-model="ca.compname"
                />
              </td>
              <td class="cvwrite-cv3-td3">
                <input
                  type="text"
                  class="cvwrite-cv-input"
                  placeholder="직종"
                  v-model="ca.jobclass"
                />
              </td>
              <td class="cvwrite-cv3-td4">
                <input
                  type="text"
                  class="cvwrite-cv-input"
                  placeholder="주요 직무"
                  v-model="ca.maintask"
                />
              </td>
              <td class="cvwrite-cv3-td5">
                <input
                  type="text"
                  class="cvwrite-cv-input"
                  placeholder="직급/직책"
                  v-model="ca.jobposition"
                />
              </td>
              <td class="cvwrite-cv3-td6">
                <button class="cvwrite-delbtn" @click="delcv3(index, ca.seqno)">
                  삭제
                </button>
              </td>
            </tr>
          </table>
        </div>

        <div class="cvwrite-cv4">
          <div class="cvwrite-cv-title">
            <div class="cvwrite-cv4-title">자기 소개서</div>
            <button class="cvwrite-add-button" @click="addcv4()">추가</button>
          </div>

          <table class="cvwrite-cv4-table">
            <tr class="cvwrite-cv4-tr">
              <th class="cvwrite-cv4-th1">제목</th>
              <th class="cvwrite-cv4-th2">내용</th>
              <th class="cvwrite-cv3-th6"></th>
            </tr>
            <tr v-for="(int, index) in intro" :key="index">
              <!-- 여기서 데이터 받을시 for문 -->
              <td class="cvwrite-cv4-td1">
                <textarea
                  class="auto-resize"
                  placeholder="질문을 입력하세요"
                  style="text-align: center"
                  v-model="int.title"
                >
                </textarea>
              </td>
              <td class="cvwrite-cv4-td2">
                <textarea
                  class="auto-resize"
                  placeholder="750자 이내로 입력하세요"
                  v-model="intro"
                >
                </textarea>
              </td>
              <td class="cvwrite-cv3-td6">
                <button
                  class="cvwrite-delbtn"
                  @click="delcv4(index, int.seqno)"
                >
                  삭제
                </button>
              </td>
            </tr>
          </table>
        </div>
        <div class="cvwrite-button-container">
          <router-link to="resumeList"
            ><button class="cvwrite-remove">이전으로</button></router-link
          >
          <!-- 저장버튼이 눌리면 모달이 켜지는데 axios요청 완료된 후에 켜지게 만들어 주시면 됩니다-->
          <button class="cvwrite-save" @click="complete($event)">저장</button>
        </div>
      </div>
      <resumeSave v-show="savecom" />
    </div>
  </div>
</template>

<script>
import resumeSave from "../../components/ResumeSave.vue";
import axios from "axios";
export default {
  data() {
    return {
      upload_text: "업로드",
      savecom: false,
      name: null,
      basic: {},
      edu: [],
      car: [],
      intro: [],
      memno: parseInt(localStorage.getItem("memno")),
      rsmno: 0,
      imageSrc: "/img/resumePhoto/default.png",
      selectedFile: null,
      selectedResume: null,
      resumeData:{}
    };
  },
  components: {
    resumeSave,
  },
  created() {
    const num = this.$route.query.num; // URL 파라미터 접근
    this.fetchData(num);
  },
  methods: {
    fetchData(num) {
      if (num) {
        console.log(num);
        axios
          .get(
            `${process.env.VUE_APP_BACK_END_URL}/resume/resumeDetail?num=${num}`
          )
          .then((resp) => {
            console.log(resp.data);
            this.rsmno = num;
            this.basic = resp.data[0];
            this.edu = resp.data[1];
            this.car = resp.data[2];
            this.intro = resp.data[3];
            this.imageSrc = "/img/resumePhoto/" + this.basic.imgname;
            console.log(this.basic);
            console.log(this.edu);
            console.log(this.car);
            console.log(this.intro);
            console.log(this.imageSrc);
            console.log(this.memno);
            console.log(typeof this.memno);
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        console.log("이력서 작성");
      }
    },
    complete() {
      const num = this.$route.query.num; // URL 파라미터 접근
      if (num != null) {
        axios.post(
          `${process.env.VUE_APP_BACK_END_URL}/resume/resumeUpdate?num=${num}`,
          {
            basic: this.basic,
            education: this.edu,
            career: this.car,
            intro: this.intro,
            memno: this.memno,
          },
          {
            headers: { "Content-Type": "application/json" },
          }
        );

        // 사진 추가
        if (this.selectedFile != null) {
          const formData = new FormData();
          formData.append("file", this.selectedFile);
          formData.append("rsmno", this.rsmno);
          formData.append("memno", this.memno);
          console.log(formData);
          console.log(typeof formData);
          axios
            .post(
              `${process.env.VUE_APP_BACK_END_URL}/resume/resumeImgUp`,
              formData,
              { headers: { "Content-Type": "multipart/form-data" } }
            )
            .then(() => {
              this.savecom = !this.savecom;
            });
        } else {
          console.log("사진 없음");
          this.savecom = !this.savecom;
        }
      } else {
        axios
          .post(
            `${process.env.VUE_APP_BACK_END_URL}/resume/resumeAdd`,
            {
              basic: this.basic,
              education: this.edu,
              career: this.car,
              intro: this.intro,
              memno: this.memno,
            },
            {
              headers: { "Content-Type": "application/json" },
            }
          )
          .then((res) => {
            console.log("반환값", res.data);
            this.rsmno = res.data;

            // 사진 추가
            if (this.selectedFile != null) {
              const formData = new FormData();
              formData.append("file", this.selectedFile);
              formData.append("rsmno", this.rsmno);
              formData.append("memno", this.memno);
              console.log(formData);
              console.log(typeof formData);
              axios.post(
                `${process.env.VUE_APP_BACK_END_URL}/resume/resumeImgUp`,
                formData,
                { headers: { "Content-Type": "multipart/form-data" } }
              );
            } else {
              console.log("사진 없음");
            }
          });
        this.savecom = !this.savecom;
      }
    },
    uploadchange(event) {
  this.upload_text = "진행중";
  this.selectedResume = event.target.files[0];

  if (this.selectedResume != null) {
    const formData = new FormData();
    formData.append("file", this.selectedResume);

    axios.post(
      `${process.env.VUE_APP_DJANGO_APP_BACK_END_URL}resume/upload/`,
      formData,
      { headers: { "Content-Type": "multipart/form-data" } }
    )
    .then((res) => {
      console.log("반환값", res.data.resume_data.intro);
      this.basic = res.data.resume_data;
      this.edu = res.data.resume_data.education_data;
      this.car = res.data.resume_data.career_data;
      this.intro = res.data.resume_data.intro;
      this.upload_text = "업로드완료";
    })
    .catch((error) => {
      console.error("파일 업로드 중 오류 발생:", error);
      this.upload_text = "업로드 실패";
    });
  } else {
    console.log("파일 없음");
  }
},
    async submitFile() {
      if (!this.selectedFile) {
        alert("Please select a file first!");
        return;
      }

      let formData = new FormData();
      formData.append('pdf', this.selectedResume);

      try {
        const response = await axios.post('http://localhost:9000/resume/', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });
        console.log('File uploaded successfully', response.data);
      } catch (error) {
        console.error('Error uploading file:', error);
      }
    },
  
    upprofile(event) {
      event.preventDefault();
      const file = event.target.files[0];
      console.log(file);
      if (file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          this.imageSrc = e.target.result;
        };
        reader.readAsDataURL(file);
        this.selectedFile = file;
      }
    },
    addcv2() {
      this.edu.push({ td1: "" });
      console.log(this.cvlist2);
    },
    delcv2(i, seqno) {
      console.log("seqno:", seqno);
      console.log("rsmno:", this.rsmno);
      console.log(i);
      if (seqno) {
        axios
          .post(`${process.env.VUE_APP_BACK_END_URL}/resume/resumeDeleteEdu`, {
            seqno: seqno,
            rsmno: this.rsmno,
          })
          .then((res) => {
            console.log("삭제완료");
          });
        this.edu.splice(i, 1);
      } else {
        this.edu.splice(i, 1);
      }
    },
    addcv3() {
      this.car.push({ td1: "" });
    },
    delcv3(i, seqno) {
      console.log("seqno:", seqno);
      console.log("rsmno:", this.rsmno);
      console.log(i);
      if (seqno) {
        axios
          .post(`${process.env.VUE_APP_BACK_END_URL}/resume/resumeDeleteCar`, {
            seqno: seqno,
            rsmno: this.rsmno,
          })
          .then((res) => {
            console.log("삭제완료");
          });
        this.car.splice(i, 1);
      } else {
        this.car.splice(i, 1);
      }
    },
    addcv4() {
      this.intro.push({ td1: "", td2: "" });
    },
    delcv4(i, seqno) {
      console.log("seqno:", seqno);
      console.log("rsmno:", this.rsmno);
      console.log(i);
      if (seqno) {
        axios
          .post(`${process.env.VUE_APP_BACK_END_URL}/resume/resumeDeleteSelf`, {
            seqno: seqno,
            rsmno: this.rsmno,
          })
          .then((res) => {
            console.log("삭제완료");
          });
        this.intro.splice(i, 1);
      } else {
        this.intro.splice(i, 1);
      }
    },
  },
};
</script>
