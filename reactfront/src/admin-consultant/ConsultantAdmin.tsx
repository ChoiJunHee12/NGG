import React, { useState, useEffect } from 'react';
import './ConsultantAdmin.css';
import ConsultantAdminModal from './ConsultantAdminModal';

interface Career {
  cnsno: number;
  seqno: number;
  term: string;
  content: string;
  detail: string;
  careerdiv: string;
  credt: string;
  upddt: string;
}

interface User {
  cnsno: number;
  password: string;
  name: string;
  gendercd: string;
  categcd: string;
  phonenum: string;
  email: string;
  birthymd: string;
  cnscareer: string;
  cnsproject: string;
  cnscareer_vo: Career[];
}

const ConsultantAdmin: React.FC = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [showModal, setShowModal] = useState(false);
  const [currentUser, setCurrentUser] = useState<User | null>(null);
  const [isEditing, setIsEditing] = useState(false);

  useEffect(() => {
    fetchUsers();
  }, []);

  const fetchUsers = () => {
    fetch(`${process.env.REACT_APP_BACK_END_URL}/adminConsult`, {
      method: 'GET',
    })
      .then(response => response.json())
      .then(data => {
        setUsers(data);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  };

  const handleAddClick = () => {
    setCurrentUser({
      cnsno: 0,
      password: '',
      name: '',
      gendercd: '',
      categcd: '',
      phonenum: '',
      email: '',
      birthymd: '',
      cnscareer: '',
      cnsproject: '',
      cnscareer_vo: [],
    });
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

  const handleSaveUser = (user: User) => {
    const url = isEditing
      ? `${process.env.REACT_APP_BACK_END_URL}/adminConsult/${user.cnsno}/updateConsultant`
      : `${process.env.REACT_APP_BACK_END_URL}/adminConsult/addConsultant`;

    const method = isEditing ? 'PUT' : 'POST';

    fetch(url, {
      method: method,
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(user),
    })
      .then(response => response.json())
      .then(data => {
        fetchUsers();  // 사용자 목록 새로고침
        setShowModal(false);
      })
      .catch(error => {
        console.error('Error saving consultant:', error);
      });
  };

  const handleDeleteUser = (cnsno: number) => {
    fetch(`${process.env.REACT_APP_BACK_END_URL}/adminConsult/${cnsno}/delConsultant`, {
      method: 'DELETE',
    })
      .then(response => {
        if (response.ok) {
          fetchUsers();  // 사용자 목록 새로고침
        } else {
          throw new Error('Failed to delete consultant');
        }
      })
      .catch(error => {
        console.error('Error deleting consultant:', error);
      });
  };

  const getGenderText = (gendercd: string) => {
    switch (gendercd) {
      case 'F':
        return '여성';
      case 'M':
        return '남성';
      default:
        return '알 수 없음';
    }
  };

  const getCategoryText = (categcd: string) => {
    switch (categcd) {
      case '1':
        return 'IT/개발';
      case '2':
        return '교육';
      case '3':
        return '영업/마케팅';
      case '4':
        return '기획/전략';
      case '5':
        return '경영';
      default:
        return '알 수 없음';
    }
  };

  return (
    <div className="table-container">
      <div className="table-title">🪪 Consultant List</div>
      <button className='btn-add' onClick={handleAddClick}>➕ Consultant 추가</button>
      <table className="user-table">
        <thead>
        </thead>
        <tbody>
          <tr>
            <th>번호</th>
            <th>컨설턴트번호</th>
            <th>이름</th>
            <th>성별</th>
            <th>전문분야</th>
            <th>연락처</th>
            <th>이메일</th>
            <th>생년월일</th>
            <th>컨설팅경력</th>
            <th>프로젝트수행</th>
            <th>수정/삭제</th>
          </tr>
          {users.map((user, index) => {
            const educationItems = user.cnscareer_vo.filter(item => item.careerdiv === '1');
            const careerItems = user.cnscareer_vo.filter(item => item.careerdiv === '2');

            return (
              <React.Fragment key={user.cnsno}>
                {/* 기본 정보 */}
                <tr>
                  <td rowSpan={educationItems.length + careerItems.length + 3}>{index + 1}</td>
                  <td>{user.cnsno}</td>
                  <td>{user.name}</td>
                  <td>{getGenderText(user.gendercd)}</td>
                  <td>{getCategoryText(user.categcd)}</td>
                  <td>{user.phonenum}</td>
                  <td>{user.email}</td>
                  <td>{user.birthymd}</td>
                  <td>{user.cnscareer}</td>
                  <td>{user.cnsproject}</td>
                  <td rowSpan={educationItems.length + careerItems.length + 3}>
                    <button className='btn-up' onClick={() => handleEditClick(user)}>수정</button>
                    <button className='btn-del' onClick={() => handleDeleteUser(user.cnsno)}>삭제</button>
                  </td>
                </tr>

                {/* 학력사항 */}
                {educationItems.length > 0 && (
                  <tr className="education">
                    <td colSpan={9}><strong>학력사항</strong></td>
                  </tr>
                )}
                {educationItems.map(item => (
                  <tr key={`edu-${user.cnsno}-${item.seqno}`}>
                    <td colSpan={3}>{item.term}</td>
                    <td colSpan={2}>{item.content}</td>
                    <td colSpan={4}>{item.detail}</td>
                  </tr>
                ))}

                {/* 경력사항 */}
                {careerItems.length > 0 && (
                  <tr className="career">
                    <td colSpan={9}><strong>경력사항</strong></td>
                  </tr>
                )}
                {careerItems.map(item => (
                  <tr key={`career-${user.cnsno}-${item.seqno}`}>
                    <td colSpan={3}>{item.term}</td>
                    <td colSpan={2}>{item.content}</td>
                    <td colSpan={4}>{item.detail}</td>
                  </tr>
                ))}
              </React.Fragment>
            );
          })}
        </tbody>
      </table>
      {showModal && currentUser && (
        <ConsultantAdminModal
          show={showModal}
          handleClose={handleCloseModal}
          handleSave={handleSaveUser}
          user={currentUser}
          setUser={setCurrentUser}
        />
      )}
    </div>
  );
};

export default ConsultantAdmin;