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
                프로필 사진 넣어주세요.
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
                  placeholder="출생년도"
                  v-model="basic.birthymd"
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
              <th class="cvwrite-cv2-th4">학력</th>
              <th class="cvwrite-cv2-th5">학점</th>
              <th class="cvwrite-cv2-th6">삭제</th>
            </tr>
            <tr v-for="(ed, index) in edu" :key="index">
              <!-- 여기서 데이터 받을시 for문 -->
              <td class="cvwrite-cv2-td1">
                <input
                  type="text"
                  class="cvwrite-cv-input-half-top"
                  placeholder="입학일시"
                  v-model="ed.entymd"
                />
                ~
                <input
                  type="text"
                  class="cvwrite-cv-input-half-bottom"
                  placeholder="졸업일시"
                  v-model="ed.gradeymd"
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
                  placeholder="학력"
                  v-model="ed.gradeuateyn"
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
              <th class="cvwrite-cv3-th6">삭제</th>
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
                ~
                <input
                  type="text"
                  class="cvwrite-cv-input-half-top"
                  placeholder="퇴사일시"
                  v-model="ca.quitymd"
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
          </div>

          <table class="cvwrite-cv4-table">
            <tr class="cvwrite-cv4-tr">
              <th class="cvwrite-cv4-th1">질문</th>
              <th class="cvwrite-cv4-th2">내용</th>
            </tr>
            <tr v-for="(int, index) in intro" :key="index">
              <!-- 여기서 데이터 받을시 for문 -->
              <td class="cvwrite-cv4-td1">
                <textarea
                  class="auto-resize"
                  placeholder="750자 이내로 입력하세요"
                  style="text-align: center"
                  v-model="int.title"
                >
                </textarea>
              </td>
              <td class="cvwrite-cv4-td2">
                <textarea
                  class="auto-resize"
                  placeholder="750자 이내로 입력하세요"
                  v-model="int.content"
                >
                </textarea>
              </td>
            </tr>
          </table>
        </div>
        <div class="cvwrite-button-container">
          <router-link to="resumeList"
            ><button class="cvwrite-remove">이전으로</button></router-link
          >
          <!-- 저장버튼이 눌리면 모달이 켜지는데 axios요청 완료된 후에 켜지게 만들어 주시면 됩니다-->
          <button class="cvwrite-save" @click="complete()">저장</button>
        </div>
      </div>
      <CVsave
        v-if="savecom"
        @close="complete()"
        v-bind:mData="this.modalData"
      />
    </div>
  </div>
</template>

<script>
import CVsave from "../../components/ResumeSave.vue";
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
      intro: [{ title: "나의 성장과정" }],
      memno: 1,
      rsmno: 0,
    };
  },
  components: {
    CVsave,
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
            if (resp.data[3].length !== 0) {
              this.intro = resp.data[3];
            }
            console.log(this.basic);
            console.log(this.edu);
            console.log(this.car);
            console.log(this.intro);
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

      if (num) {
        axios
          .post(
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
          )
          .then((res) => {
            console.log(this.basic);
            console.log(res.data);
          });
        this.savecom = !this.savecom;
      } else {
        console.log(this.basic);
        console.log(this.edu);
        console.log(this.car);
        console.log(this.intro);
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
            console.log(res.data);
          });
        this.savecom = !this.savecom;
      }
    },
    uploadchange() {
      this.upload_text = "진행중";
    },
    addcv2() {
      this.edu.push({ td1: "", td2: "", td3: "", td4: "", td5: "" });
      console.log(this.cvlist2);
    },
    delcv2(i, seqno) {
      console.log("seqno:", seqno);
      console.log("rsmno:", this.rsmno);
      console.log(i);
      axios
        .post(`${process.env.VUE_APP_BACK_END_URL}/resume/resumeDeleteEdu`, {
          seqno: seqno,
          rsmno: this.rsmno,
        })
        .then((res) => {
          console.log("삭제완료");
        });
      this.edu.splice(i, 1);
    },
    addcv3() {
      this.car.push({ td1: "", td2: "", td3: "", td4: "", td5: "" });
    },
    delcv3(i, seqno) {
      console.log("seqno:", seqno);
      console.log("rsmno:", this.rsmno);
      console.log(i);
      axios
        .post(`${process.env.VUE_APP_BACK_END_URL}/resume/resumeDeleteCar`, {
          seqno: seqno,
          rsmno: this.rsmno,
        })
        .then((res) => {
          console.log("삭제완료");
        });
      this.car.splice(i, 1);
    },
  },
};
</script>