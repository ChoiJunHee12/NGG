import { createRouter, createWebHistory } from "vue-router";
import InterviewRes from "../views/InterviewRes.vue";
import OneToOne from "../views/OneToOne.vue";

const routes = [
  {
    path: "/",
    name: "Landing",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/Landing.vue"),
    meta: { hideLayout: true }  // 메타 필드 추가
  },

  {
    path: "/ResPersonality",
    name: "ResPersonality",

    component: () =>
      import(
        /* webpackChunkName: "about" */ "../views/ai/ResPersonalityView.vue"
      ),
  },
  {
    path: "/ResDuty",
    name: "ResDuty",

    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ai/ResDutyView.vue"),
  },
  {
    path: "/ResumeList",
    name: "ResumeList",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/resume/ResumeView.vue"),
  },
  {
    path: "/ResumeWrite",
    name: "ResumeWrite",

    component: () =>
      import(/* webpackChunkName: "about" */ "../views/resume/ResumeWriteView.vue"),
  },
  {
    path: "/ResumeWrite2",
    name: "ResumeWrite2",

    component: () =>
      import(/* webpackChunkName: "about" */ "../views/resume/ResumeWriteView.vue"),
  },
  {

    path: '/ResumeWrite',
    name: 'ResumeUpdate',
    component: () => import(/* webpackChunkName: "about" */ '../views/resume/ResumeWriteView.vue')
  },
  {
    path: '/MyPage',
    name: 'MyPage',
    component: () => import( '../views/profile/MyPage.vue')
  },
  {
    path: "/personalCol_Test",
    name: "PersonalCol_Test",
    component: () =>
      import(
        /* webpackChunkName: "about" */ "../views/personal/PersonalCol_Test.vue"
      ),
  },
  {
    path: "/personal_Result",
    name: "PersonalCol_Result",
    component: () =>
      import(
        /* webpackChunkName: "about" */ "../views/personal/PersonalCol_Result.vue"
      ),
  },
  {
    path: "/personal_MakeUp",
    name: "PersonalCol_MakeUp",
    component: () =>
      import(
        /* webpackChunkName: "about" */ "../views/personal/PersonalCol_MakeUp.vue"
      ),
  },
  {
    path: "/InterviewRes",
    name: "InterviewRes",
    component: InterviewRes,
  },
  {
    path: "/OneToOne",
    name: "OneToOne",
    component: OneToOne,
  },
  {
    path: "/AISetting",
    name: "AISetting",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ai/AISettingView.vue"),
  },
  {
    path: "/AIFaceTest",
    name: "AIFaceTest",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ai/AIFaceTestView.vue"),
  },
  {
    path: "/AIMicTest",
    name: "AIMicTest",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ai/AIMicTestView.vue"),
  },
  {
    path: "/AIInterviewChoice",
    name: "AIInterviewChoice",
    component: () =>
      import(
        /* webpackChunkName: "about" */ "../views/ai/AIInterviewChoiceView.vue"
      ),
  },
  {
    path: "/AIInterview1",
    name: "AIInterview1",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ai/AIInterview1.vue"),
  },
  {
    path: "/AIInterview2",
    name: "AIInterview2",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ai/AIInterview2.vue"),
  },
  {
    path: "/AIInterview3",
    name: "AIInterview3",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ai/AIInterview3.vue"),
  },
  {
    path: "/AIInterview4",
    name: "AIInterview4",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ai/AIInterview4.vue"),
  },
  {
    path: "/AIInterview5",
    name: "AIInterview5",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ai/AIInterview5.vue"),
  },
  {
    path: "/AIInterview6",
    name: "AIInterview6",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ai/AIInterview6.vue"),
  },
  {
    path: "/AIInterview7",
    name: "AIInterview7",
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/ai/AIInterview7.vue"),
  },
  {
    path: "/login",
    name: "login",
    component: () => import("../views/Login.vue"),
  },
  {
    path: "/ConsultantInfo",
    name: "ConsultantInfo",
    component: () => import("../views/ConsultantInfo.vue"),
  },
  {
    path: "/Calendar",
    name: "Calendar",
    component: () => import("../views/CalendarView.vue"),
  },
  {
    path: "/Main",
    name: "Main",
    component: () => import("../views/Main.vue"),
  },
  {
    path: "/test",
    name: "test",
    component: () => import("../views/TestVue.vue"),
  },
  {
    path: "/MyConsultantProfile",
    name: "MyConsultantProfile",
    component: () => import("../views/MyConsultantProfile.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
