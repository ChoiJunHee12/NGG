import React, { useState, useEffect } from 'react';
import './ConsultantAdminModal.css';

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

interface ConsultantAdminModalProps {
  show: boolean;
  handleClose: () => void;
  handleSave: (updatedUser: User) => void;
  user: User | null;
  setUser: React.Dispatch<React.SetStateAction<User | null>>;
}

const ConsultantAdminModal: React.FC<ConsultantAdminModalProps> = ({ show, handleClose, handleSave, user, setUser }) => {
  const [isCustomCareer, setIsCustomCareer] = useState<boolean>(false);
  const [isCustomProject, setIsCustomProject] = useState<boolean>(false);

  const careerOptions = ['1년~3년', '3년~5년', '5년~10년', '10년 이상'];
  const projectOptions = ['10회 이상', '30회 이상', '50회 이상', '100회 이상'];

  useEffect(() => {
    if (user) {
      setIsCustomCareer(!careerOptions.includes(user.cnscareer));
      setIsCustomProject(!projectOptions.includes(user.cnsproject));
    }
  }, [user]);

  const handleSelectChange = (e: React.ChangeEvent<HTMLSelectElement>, field: 'cnscareer' | 'cnsproject' | 'gendercd' | 'categcd') => {
    const value = e.target.value;
    if (user) {
      if (field === 'cnscareer') {
        setIsCustomCareer(value === '직접 입력');
        setUser({ ...user, cnscareer: value === '직접 입력' ? '' : value });
      } else if (field === 'cnsproject') {
        setIsCustomProject(value === '직접 입력');
        setUser({ ...user, cnsproject: value === '직접 입력' ? '' : value });
      } else {
        setUser({ ...user, [field]: value });
      }
    }
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>, field: keyof User) => {
    const value = e.target.value;
    if (user) {
      setUser({ ...user, [field]: value });
    }
  };

  const handleCareerChange = (e: React.ChangeEvent<HTMLInputElement>, index: number, careerType: 'education' | 'career') => {
    const { name, value } = e.target;
    if (user) {
      const updatedCareers = [...user.cnscareer_vo];
      const careerTypeCode = careerType === 'education' ? '1' : '2';

      // 해당 타입(학력/경력)의 항목들만 필터링
      const filteredCareers = updatedCareers.filter(career => career.careerdiv === careerTypeCode);

      if (index < filteredCareers.length) {
        // 기존 항목 수정
        const careerIndex = updatedCareers.findIndex(career =>
          career.careerdiv === careerTypeCode && career.seqno === filteredCareers[index].seqno
        );
        if (careerIndex !== -1) {
          updatedCareers[careerIndex] = {
            ...updatedCareers[careerIndex],
            [name]: value,
          };
        }
      } else {
        // 새 항목 추가
        const newSeqno = Math.max(0, ...filteredCareers.map(c => c.seqno)) + 1;
        updatedCareers.push({
          cnsno: user.cnsno,
          seqno: newSeqno,
          term: name === 'term' ? value : '',
          content: name === 'content' ? value : '',
          detail: name === 'detail' ? value : '',
          careerdiv: careerTypeCode,
          credt: new Date().toISOString(),
          upddt: new Date().toISOString()
        });
      }

      setUser({ ...user, cnscareer_vo: updatedCareers });
    }
  };

  const addCareerItem = (careerType: 'education' | 'career') => {
    if (user) {
      const careerTypeCode = careerType === 'education' ? '1' : '2';
      const filteredCareers = user.cnscareer_vo.filter(career => career.careerdiv === careerTypeCode);
      const newSeqno = Math.max(0, ...filteredCareers.map(c => c.seqno)) + 1;

      const newCareerItem: Career = {
        cnsno: user.cnsno,
        seqno: newSeqno,
        term: '',
        content: '',
        detail: '',
        careerdiv: careerTypeCode,
        credt: new Date().toISOString(),
        upddt: new Date().toISOString()
      };
      setUser({ ...user, cnscareer_vo: [...user.cnscareer_vo, newCareerItem] });
    }
  };

  const handleSaveClick = () => {
    if (user) {
      handleSave(user);
    }
  };

  if (!show) {
    return null;
  }

  return (
    <div className="modal-overlay">
      <div className="modal-content">
        <h2>⚙️ Consultant 정보 수정</h2>
        <form className="modal-form">
          <div className="form-row">
            <div className="form-group">
              <label htmlFor="cnsno">컨설턴트 번호</label>
              <input type="number" id="cnsno" name="cnsno" value={user?.cnsno || ''} onChange={(e) => handleInputChange(e, 'cnsno')} />
            </div>
            <div className="form-group">
              <label htmlFor="password">비밀번호</label>
              <input type="password" id="password" name="password" value={user?.password || ''} onChange={(e) => handleInputChange(e, 'password')} />
            </div>
          </div>
          <div className="form-row">
            <div className="form-group">
              <label htmlFor="name">이름</label>
              <input type="text" id="name" name="name" value={user?.name || ''} onChange={(e) => handleInputChange(e, 'name')} />
            </div>
            <div className="form-group">
              <label htmlFor="gendercd">성별</label>
              <select id="gendercd" name="gendercd" value={user?.gendercd || ''} onChange={(e) => handleSelectChange(e, 'gendercd')}>
                <option value="">선택</option>
                <option value="F">여성</option>
                <option value="M">남성</option>
              </select>
            </div>
          </div>
          <div className="form-row">
            <div className="form-group">
              <label htmlFor="categcd">전문분야</label>
              <select id="categcd" name="categcd" value={user?.categcd || ''} onChange={(e) => handleSelectChange(e, 'categcd')}>
                <option value="">선택</option>
                <option value="1">IT/개발</option>
                <option value="2">교육</option>
                <option value="3">영업/마케팅</option>
                <option value="4">기획/전략</option>
                <option value="5">경영</option>
              </select>
            </div>
            <div className="form-group">
              <label htmlFor="phonenum">연락처</label>
              <input type="text" id="phonenum" name="phonenum" value={user?.phonenum || ''} onChange={(e) => handleInputChange(e, 'phonenum')} />
            </div>
          </div>
          <div className="form-row">
            <div className="form-group">
              <label htmlFor="email">이메일</label>
              <input type="text" id="email" name="email" value={user?.email || ''} onChange={(e) => handleInputChange(e, 'email')} />
            </div>
            <div className="form-group">
              <label htmlFor="birthymd">생년월일</label>
              <input type="text" id="birthymd" name="birthymd" value={user?.birthymd || ''} onChange={(e) => handleInputChange(e, 'birthymd')} />
            </div>
          </div>
          <div className="form-row">
            <div className="form-group">
              <label htmlFor="cnscareer">컨설팅경력</label>
              <select
                id="cnscareer"
                name="cnscareer"
                value={isCustomCareer ? '직접 입력' : user?.cnscareer || ''}
                onChange={(e) => handleSelectChange(e, 'cnscareer')}
              >
                <option value="">선택</option>
                {careerOptions.map((option) => (
                  <option key={option} value={option}>{option}</option>
                ))}
                <option value="직접 입력">직접 입력</option>
              </select>
              {isCustomCareer && (
                <input
                  type="text"
                  value={user?.cnscareer || ''}
                  onChange={(e) => handleInputChange(e, 'cnscareer')}
                  placeholder="직접 입력"
                />
              )}
            </div>
            <div className="form-group">
              <label htmlFor="cnsproject">프로젝트수행</label>
              <select
                id="cnsproject"
                name="cnsproject"
                value={isCustomProject ? '직접 입력' : user?.cnsproject || ''}
                onChange={(e) => handleSelectChange(e, 'cnsproject')}
              >
                <option value="">선택</option>
                {projectOptions.map((option) => (
                  <option key={option} value={option}>{option}</option>
                ))}
                <option value="직접 입력">직접 입력</option>
              </select>
              {isCustomProject && (
                <input
                  type="text"
                  value={user?.cnsproject || ''}
                  onChange={(e) => handleInputChange(e, 'cnsproject')}
                  placeholder="직접 입력"
                />
              )}
            </div>
          </div>

          <div className="career-section">
            <h3>학력사항</h3>
            {user?.cnscareer_vo.filter(career => career.careerdiv === '1').map((career, index) => (
              <div key={`edu-${index}`} className="career-item">
                <input type="text" name="term" value={career.term} onChange={(e) => handleCareerChange(e, index, 'education')} placeholder="기간" />
                <input type="text" name="content" value={career.content} onChange={(e) => handleCareerChange(e, index, 'education')} placeholder="내용" />
                <input type="text" name="detail" value={career.detail} onChange={(e) => handleCareerChange(e, index, 'education')} placeholder="세부사항" />
              </div>
            ))}
            <button type="button" onClick={() => addCareerItem('education')} className="btn-add-career">학력 추가</button>
          </div>

          <div className="career-section">
            <h3>경력사항</h3>
            {user?.cnscareer_vo.filter(career => career.careerdiv === '2').map((career, index) => (
              <div key={`career-${index}`} className="career-item">
                <input type="text" name="term" value={career.term} onChange={(e) => handleCareerChange(e, index, 'career')} placeholder="기간" />
                <input type="text" name="content" value={career.content} onChange={(e) => handleCareerChange(e, index, 'career')} placeholder="내용" />
                <input type="text" name="detail" value={career.detail} onChange={(e) => handleCareerChange(e, index, 'career')} placeholder="세부사항" />
              </div>
            ))}
            <button type="button" onClick={() => addCareerItem('career')} className="btn-add-career">경력 추가</button>
          </div>

          <div className='modal-btn-area'>
            <button type="button" onClick={handleSaveClick} className='modal-btn-up'>저장</button>
            <button type="button" onClick={handleClose} className='modal-btn-close'>닫기</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default ConsultantAdminModal;