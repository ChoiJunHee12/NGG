import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { logout } from "../authSlice";
import { useDispatch } from "react-redux";
import "./Header.css";

interface HeaderProps {
  toggleSidebar: () => void;
}

const Header: React.FC<HeaderProps> = ({ toggleSidebar }) => {
  const [isHidden, setIsHidden] = useState(false);
  const dispatch = useDispatch();

  const handleScroll = () => {
    setIsHidden(window.scrollY !== 0);
  };

  const handleLogout = () => {
    dispatch(logout());
  };

  useEffect(() => {
    window.addEventListener("scroll", handleScroll);
    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, []);

  return (
    <div className={`header ${isHidden ? "hidden" : ""}`}>
      <h1 className="header-h1">
        <Link to="/" style={{ color: "black", textDecoration: "none" }}>
          네가, 꿈
        </Link>
        <div>
          <Link to="" className="custom-underline" onClick={handleLogout}>
            <h5 className="header-h5" style={{ paddingRight: "20px" }}>
              로그아웃
            </h5>
          </Link>
        </div>
      </h1>
    </div>
  );
};

export default Header;
