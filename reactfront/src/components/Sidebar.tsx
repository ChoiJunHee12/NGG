import React, { useRef, useState } from "react";
import { Link } from "react-router-dom";
import "./Sidebar.css";

interface SidebarProps {
  isVisible: boolean;
}

const Sidebar: React.FC<SidebarProps> = ({ isVisible }) => {
  const [imagePreview, setImagePreview] = useState<string | null>(null);
  const fileInputRef = useRef<HTMLInputElement>(null);

  const changeImage = () => {
    if (fileInputRef.current) {
      fileInputRef.current.click();
    }
  };

  const handleFileUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        setImagePreview(e.target?.result as string);
      };
      reader.readAsDataURL(file);
    }
  };

  return (
    <div
      className={`sidebar ${isVisible ? "slide-enter-active" : "slide-leave-to"}`}
    >
      <div>
        <div className="my-profile devPhotoBtns btns">
          <div className="pie-chart">
            <div className="profile-img">
              <img
                src={imagePreview || "/img/consult_img/noimage.png"}
                alt="Preview"
                className="preview-image"
              />
            </div>
          </div>
          <button
            className="profile-btn btnModify"
            type="button"
            onClick={changeImage}
          >
            <span className="skip">사진편집</span>
          </button>
          <input
            type="file"
            ref={fileInputRef}
            onChange={handleFileUpload}
            style={{ display: "none" }}
            accept="image/*"
          />
        </div>
        <br />
        <br />
        <h3 style={{ paddingBottom: "40px" }}>침착맨 님</h3>
        <Link to="/consultant/consultant-profile" className="main-router">
          <p className="bar-p">개인정보 수정</p>
        </Link>
        <Link to="/consultant/feedback/main" className="main-router">
          <p className="bar-p">피드백 관리</p>
        </Link>
        <Link to="/consultant/OneToOne" className="main-router">
          <p className="bar-p">면접자 1:1 상담</p>
        </Link>
      </div>
      <hr />
      <div>
        <Link to="/consultant/Consult_Question" className="main-router">
          <p className="bar-p">직무 면접 질문 관리</p>
        </Link>
        <hr />
        <Link to="/admin/User_Dash" className="main-router">
          <p className="bar-p">User DashBoard</p>
        </Link>
        <Link to="/admin/Consultant_Dash" className="main-router">
          <p className="bar-p">Consultant DashBoard</p>
        </Link>
        <hr />
        <Link to="/admin/AddId" className="main-router">
          <p className="bar-p">Consultant Admin DashBoard</p>
        </Link>
        <hr />
        <Link to="/member/MemberList" className="main-router">
          <p className="bar-p">Member Admin DashBoard</p>
        </Link>
        <hr />
        <i className="bi bi-power power-icon"></i>
      </div>
    </div>
  );
};

export default Sidebar;
