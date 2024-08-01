import React, { useState } from 'react';
import { MemberType } from './MemberList';
import './Member.css';  // CSS 파일을 불러옴

interface ModalPropType {
  member: MemberType;
  onClose: () => void;
}

const MemberModal: React.FC<ModalPropType> = ({ member, onClose }) => {
  console.log('MemberModal rendering', member); // 콘솔 로그 추가
  const [updatedMember, setUpdatedMember] = useState<MemberType>({ ...member });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { id, value } = e.target;
    setUpdatedMember((prevMember: MemberType) => ({
      ...prevMember,
      [id]: value,
    }));
  };

  const updateMember = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    // 여기에 업데이트 로직 추가
    onClose();
  };

  return (
    <div className="backdrop" style={{width: '1000px'}}  >
      <div className="mymodal">
        <form onSubmit={updateMember}>
          <div className="mb-3">
            <label htmlFor="memail" className="form-label">아이디</label>
            <input
              type="email"
              className="form-control"
              id="memail"
              value={updatedMember.memail}
              onChange={handleChange}
              readOnly
            />
          </div>
          <div className="mb-3">
            <label htmlFor="mname" className="form-label">이름</label>
            <input
              type="text"
              className="form-control"
              id="mname"
              value={updatedMember.mname}
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="mnick" className="form-label">닉네임</label>
            <input
              type="text"
              className="form-control"
              id="mnick"
              value={updatedMember.mnick}
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="mgender" className="form-label">성별</label>
            <select
              className="form-control"
              id="mgender"
              value={updatedMember.mgender}
              onChange={handleChange}
            >
              <option value="남">남자</option>
              <option value="여">여자</option>
            </select>
          </div>
          <div className="mb-3">
            <label htmlFor="mbirth" className="form-label">생년월일</label>
            <input
              type="date"
              className="form-control"
              id="mbirth"
              value={updatedMember.mbirth}
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="resumeyn" className="form-label">이력서등록여부</label>
            <input
              type="text"
              className="form-control"
              id="resumeyn"
              value={updatedMember.resumeyn}
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="cnsltnm" className="form-label">담당컨설턴트이름</label>
            <input
              type="text"
              className="form-control"
              id="cnsltnm"
              value={updatedMember.cnsltnm}
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="status" className="form-label">회원상태</label>
            <select
              className="form-control"
              id="status"
              value={updatedMember.status}
              onChange={handleChange}
            >
              <option value="active">활성</option>
              <option value="inactive">비활성</option>
            </select>
          </div>
          <div className="mb-3">
            <label htmlFor="mdate" className="form-label">가입날짜</label>
            <input
              type="date"
              className="form-control"
              id="mdate"
              value={updatedMember.mdate}
              onChange={handleChange}
              readOnly
            />
          </div>
          <button type="submit" className="btn btn-primary"  style={{width: '80px', height: '40px', margin:'2px'}}>저장</button>
          <button type="button" className="btn btn-secondary"  style={{width: '80px', height: '40px'}}onClick={() => onClose()}>닫기</button>
        </form>
      </div>
    </div>
  );
};

export default MemberModal;
