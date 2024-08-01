// AddId.tsx
import React, { useState } from 'react';
import './AddId.css';
import AddIdModal from './AddIdModal';

interface User {
  cname: string;
  id: string;
  pw: string;
  skill: string;
}

const AddId: React.FC = () => {
  const [users, setUsers] = useState<User[]>([
    { cname: '김드림', id: 'consultant1', pw: 'consultant1', skill: '교육' },
    { cname: '나드림', id: 'consultant2', pw: 'consultant2', skill: '회계/재무' },
    { cname: '박드림', id: 'consultant3', pw: 'consultant3', skill: 'IT/개발' },
    { cname: '이드림', id: 'consultant4', pw: 'consultant4', skill: '생산' },
    { cname: '최드림', id: 'consultant5', pw: 'consultant5', skill: '유통/물류' },
  ]);

  const [showModal, setShowModal] = useState(false);
  const [currentUser, setCurrentUser] = useState<User | null>(null);
  const [isEditing, setIsEditing] = useState(false);

  const handleAddClick = () => {
    setCurrentUser({ cname: '', id: '', pw: '', skill: '' });
    setIsEditing(false);
    setShowModal(true);
  };

  const handleEditClick = (user: User) => {
    setCurrentUser(user);
    setIsEditing(true);
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleSaveUser = () => {
    if (isEditing && currentUser) {
      setUsers(users.map(user => (user.id === currentUser.id ? currentUser : user)));
    } else {
      setUsers([...users, currentUser!]);
    }
    setShowModal(false);
  };

  return (
    <div className="table-container">
      <table className="user-table">
        <thead>
          <tr>
            <th colSpan={6}>✅Consultant ID 관리</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <th>번호</th>
            <th>이름</th>
            <th>스킬</th>
            <th>아이디</th>
            <th>비밀번호</th>
            <th>수정/삭제</th>
          </tr>
          {users.map((user, index) => (
            <tr key={index}>
              <td>{index + 1}</td>
              <td>{user.cname}</td>
              <td>{user.skill}</td>
              <td>{user.id}</td>
              <td>{user.pw}</td>
              <td>
                <button className='btn-up' onClick={() => handleEditClick(user)}>수정</button>
                <button className='btn-del'>삭제</button>
              </td>
            </tr>
          ))}
          <tr>
            <td colSpan={6}>
              <button className='btn-add' onClick={handleAddClick}> + Consultant 추가</button>
            </td>
          </tr>
        </tbody>
      </table>

      {showModal && (
        <AddIdModal
          show={showModal}
          handleClose={handleCloseModal}
          handleSave={handleSaveUser}
          user={currentUser!}
          setUser={setCurrentUser}
        />
      )}
    </div>
  );
};

export default AddId;
