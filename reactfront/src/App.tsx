import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Footer from "./components/Footer";
import Header from "./components/Header";
import Sidebar from "./components/Sidebar";
import "./App.css";
import "./Style.css";
import Consult_Question from "./consultant/Consult_Question";
import User_Dash from "./admin/User_Dash";
import Consultant_Dash from "./admin/Consultant_Dash";
import ConsultantProfile from "./consultant-profile/ConsultantProfile";
import OTO_detail from "./OneToOne/OTO_detail";
import OTO_main from "./OneToOne/OTO_main";
import Feedback from "./feedback/Feedback";
import FeedbackDetail from "./feedback/FeedbackDetail";
import AddId from "./admin/AddId";
import MemberList from "./member/MemberList";
import FeedbackMain from "./feedback/FeedbackMain";
import FeedbackResume from "./feedback/FeedbackResume";

const App: React.FC = () => {
  const [isSidebarVisible, setIsSidebarVisible] = useState(true);

  const toggleSidebar = () => {
    setIsSidebarVisible(!isSidebarVisible);
  };

  const scrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  return (
    <Router>
      <div id="app">
        <Header toggleSidebar={toggleSidebar} />
        <i className="bi bi-list fixed-menu-icon" onClick={toggleSidebar}></i>
        <Sidebar isVisible={isSidebarVisible} />
        <main
          className={`main-content ${isSidebarVisible ? "sidebar-open" : ""}`}
        >
          <Routes>
            {/* User DashBoard */}
            <Route path="/admin/User_Dash" element={<User_Dash />} />

            {/* Consultant DashBoard */}
            <Route path="/admin/Consultant_Dash" element={<Consultant_Dash />} />

            {/* Member Admin DashBoard */}
            <Route path="/member/MemberList" element={<MemberList />} />

            {/* Consultant Admin */}
            <Route path="/admin/AddId" element={<AddId />} />

            {/*<Route path="/mypage" element={<YourMypageComponent />} />
            <Route path="/InterviewRes" element={<YourInterviewResComponent />} /> */}

            {/* ConsultantProfile */}
            <Route
              path="/consultant/consultant-profile"
              element={<ConsultantProfile />}
            />

            {/* Consult_Question */}
            <Route
              path="/consultant/Consult_Question"
              element={<Consult_Question />}
            />

            {/* OneToOne */}
            <Route path="/consultant/OneToOne" element={<OTO_main />} />
            <Route
              path="/consultant/OneToOne/detail"
              element={<OTO_detail />}
            />

            {/* feedbackMain */}
            <Route
            path="/consultant/feedback/main"
            element={<FeedbackMain />}
            />
            {/* feedbackList */}
            <Route
            path="/consultant/feedback/list"
            element={<Feedback />}
            />
            {/* feedbackDetail */}
            <Route
            path="/consultant/feedback/detail"
            element={<FeedbackDetail />}
            />
            {/* feedbackResume */}
            <Route
            path="/consultant/feedback/resume"
            element={<FeedbackResume />}
            />
          </Routes>
        </main>
        <i className="bi bi-caret-up scroll-to-top" onClick={scrollToTop}>
          <p style={{ display: "inline", fontSize: "20px" }}>TOP</p>
        </i>
        <Footer />
      </div>
    </Router>
  );
};

export default App;
