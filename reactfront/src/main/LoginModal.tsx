// src/components/LoginModal.tsx
import React from "react";
import { useNavigate } from "react-router-dom";
import "./LoginModal.css";

interface LoginModalProps {
  isOpen: boolean;
  onClose: () => void;
  loginType: "admin" | "consul";
}

const LoginModal: React.FC<LoginModalProps> = ({
  isOpen,
  onClose,
  loginType,
}) => {
  const navigate = useNavigate();

  const handleLogin = (event: React.FormEvent) => {
    event.preventDefault();

    if (loginType === "admin") {
      navigate("/admin/User_Dash");
    } else if (loginType === "consul") {
      navigate("/consultant/consultant-profile");
    }
  };

  if (!isOpen) return null;

  return (
    <div className="login-modal-overlay">
      <div className="login-modal">
        <h2 className="loginform-h2">
          {loginType === "admin" ? "관리자 로그인" : "컨설턴트 로그인"}
        </h2>
        <form className="login-form" onSubmit={handleLogin}>
          <label htmlFor="username">아이디</label>
          <input type="text" id="username" name="username" required />

          <label htmlFor="password">비밀번호</label>
          <input type="password" id="password" name="password" required />

          <button type="submit" className="submit-button">
            로그인
          </button>
          <button type="button" className="cancel-button" onClick={onClose}>
            취소
          </button>
        </form>
      </div>
    </div>
  );
};

export default LoginModal;
