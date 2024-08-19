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
import Feedback from "./feedback/feedback";
import FeedbackDetail from "./feedback/feedbackDetail";

import ConsultantAdmin from "./admin-consultant/ConsultantAdmin";
import MemberList from "./admin/member/MemberList";
import MemberModal from "./admin/member/MemberModal";


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
            <Route path="/admin-consultant/ConsultantAdmin" element={<ConsultantAdmin />} />

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

            {/* feedback */}
            <Route path="/consultant/feedback" element={<Feedback />} />
            <Route
              path="/consultant/feedback/detail"
              element={<FeedbackDetail />}
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