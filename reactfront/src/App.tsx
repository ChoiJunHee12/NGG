import React, { useState } from "react";
import {
  BrowserRouter as Router,
  Route,
  Routes,
  Navigate,
} from "react-router-dom";
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
import FeedbackMain from "./feedback/FeedbackMain";
import FeedbackResume from "./feedback/FeedbackResume";
import ConsultantAdmin from "./admin-consultant/ConsultantAdmin";
import MemberList from "./admin/member/MemberList";
import MemberModal from "./admin/member/MemberModal";
import Login from "./main/Login";

const App: React.FC = () => {
  const [isSidebarVisible, setIsSidebarVisible] = useState(true);

  const toggleSidebar = () => {
    setIsSidebarVisible(!isSidebarVisible);
  };

  return (
    <Router>
      <Routes>
        {/* Redirect / to /main/login */}
        <Route path="/" element={<Navigate to="/main/login" replace />} />

        {/* Login route */}
        <Route path="/main/login" element={<LoginPageLayout />} />

        {/* Routes that require Header and Sidebar */}
        <Route
          path="/*"
          element={
            <MainLayout
              toggleSidebar={toggleSidebar}
              isSidebarVisible={isSidebarVisible}
            />
          }
        />
      </Routes>
    </Router>
  );
};

const MainLayout: React.FC<{
  toggleSidebar: () => void;
  isSidebarVisible: boolean;
}> = ({ toggleSidebar, isSidebarVisible }) => {
  return (
    <div id="app">
      <Header toggleSidebar={toggleSidebar} />
      <i className="bi bi-list fixed-menu-icon" onClick={toggleSidebar}></i>
      <Sidebar isVisible={isSidebarVisible} />
      <main
        className={`main-content ${isSidebarVisible ? "sidebar-open" : ""}`}
      >
        <Routes>
          <Route path="/admin/User_Dash" element={<User_Dash />} />
          <Route path="/admin/Consultant_Dash" element={<Consultant_Dash />} />
          <Route path="/member/MemberList" element={<MemberList />} />
          <Route
            path="/admin-consultant/ConsultantAdmin"
            element={<ConsultantAdmin />}
          />
          <Route
            path="/consultant/consultant-profile"
            element={<ConsultantProfile />}
          />
          <Route
            path="/consultant/Consult_Question"
            element={<Consult_Question />}
          />
          <Route path="/consultant/OneToOne" element={<OTO_main />} />
          <Route path="/consultant/OneToOne/detail" element={<OTO_detail />} />
          <Route path="/consultant/feedback" element={<Feedback />} />
          <Route
            path="/consultant/feedback/detail"
            element={<FeedbackDetail />}
          />
          <Route path="/consultant/feedback/main" element={<FeedbackMain />} />
          <Route
            path="/consultant/feedback/resume"
            element={<FeedbackResume />}
          />
        </Routes>
      </main>
      <i
        className="bi bi-caret-up scroll-to-top"
        onClick={() => window.scrollTo({ top: 0, behavior: "smooth" })}
      >
        <p style={{ display: "inline", fontSize: "20px" }}>TOP</p>
      </i>
      <Footer />
    </div>
  );
};

const LoginPageLayout: React.FC = () => {
  return (
    <div className="login-page">
      <Login />
    </div>
  );
};

export default App;
