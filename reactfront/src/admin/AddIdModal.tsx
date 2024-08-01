// AddIdModal.tsx
import React from 'react';
import './AddIdModal.css';

interface User {
  cname: string;
  id: string;
  pw: string;
  skill: string;
}

interface AddIdModalProps {
  show: boolean;
  handleClose: () => void;
  handleSave: () => void;
  user: User | null;
  setUser: React.Dispatch<React.SetStateAction<User | null>>;
}

const AddIdModal: React.FC<AddIdModalProps> = ({ show, handleClose, handleSave, user, setUser }) => {
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    if (user) {
      setUser({ ...user, [name]: value });
    }
  };

  if (!show) {
    return null;
  }

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <h2>Consultant 정보 입력</h2>
        <form>
          <label>
            이름:
            <input type="text" name="cname" value={user?.cname || ''} onChange={handleChange} />
          </label>
          <label>
            스킬:
            <input type="text" name="skill" value={user?.skill || ''} onChange={handleChange} />
          </label>
          <label>
            아이디:
            <input type="text" name="id" value={user?.id || ''} onChange={handleChange} />
          </label>
          <label>
            비밀번호:
            <input type="password" name="pw" value={user?.pw || ''} onChange={handleChange} />
          </label>
          <div className='modal-btn-area'>
          <button type="button" onClick={handleSave} className='modal-btn-up'>저장</button>
          <button type="button" onClick={handleClose} className='modal-btn-close'>닫기</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default AddIdModal;
