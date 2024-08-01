import React, { useState } from 'react';
import MemberModal from './MemberModal';
import './Member.css';  // CSS 파일을 불러옴

export interface MemberType {
  id: number;
  memail: string;
  mname: string;
  mnick: string;
  mgender: string;
  mbirth: string;
  resumeyn: string;
  cnsltnm: string;
  status: string;
  mdate: string;
}

const MemberList = () => {
  const [loading, setLoading] = useState(false);   
  const [members, setMembers] = useState<MemberType[]>([
    {
      id: 1,
      memail: 'john.doe@example.com',
      mname: '김길동',
      mnick: '김길동',
      mgender: '남',
      mbirth: '1990-01-01',
      resumeyn: '등록',
      cnsltnm: '컨설턴트1',
      status: '일반',
      mdate: '2023-01-15',
    },
    {
      id: 2,
      memail: 'jane.doe@example.com',
      mname: '김길순',
      mnick: '김길순',
      mgender: '여',
      mbirth: '1992-02-02',
      resumeyn: '등록',
      cnsltnm: '컨설턴트2',
      status: '프리미엄',
      mdate: '2023-02-16',
    },
    {
      id: 3,
      memail: 'alice@example.com',
      mname: '이길순',
      mnick: '이길순',
      mgender: '여',
      mbirth: '1988-03-03',
      resumeyn: '등록',
      cnsltnm: '미정',
      status: '일반',
      mdate: '2023-03-17',
    },
    {
      id: 4,
      memail: 'bob@example.com',
      mname: '이길동',
      mnick: '이길동',
      mgender: '남',
      mbirth: '1985-04-04',
      resumeyn: '미등록',
      cnsltnm: '미정',
      status: '일반',
      mdate: '2023-04-18',
    },
    {
      id: 5,
      memail: 'charlie@example.com',
      mname: '박길동',
      mnick: '박길동',
      mgender: '남',
      mbirth: '1995-05-05',
      resumeyn: '등록',
      cnsltnm: '컨설턴트5',
      status: '일반',
      mdate: '2023-05-19',
    },
  ]);

  const [currentMember, setCurrentMember] = useState<MemberType | null>(null);
  const [searchType, setSearchType] = useState(0);
  const [searchValue, setSearchValue] = useState('');
  const [currentPage, setCurrentPage] = useState(1);
  const [itemsPerPage] = useState(10);
  const [pageBlockSize] = useState(10);
  const [popState, setPopState] = useState(false);
  
  const showModal = (member: MemberType) => {
    console.log('showModal called', member); // 콘솔 로그 추가
    setCurrentMember(member);
    setPopState(true);
  };

  const closeModal = () => {
    setPopState(false);   
  };

  return (
    <div className="memberlist-sc1">
      <div className="memberlist-sc2">
        <div className="all-padding"></div>
        <div className="member-container mt-5 membercon">
          <h1 style={{ paddingTop: '30px', marginBottom: '10px' }}>회원 목록</h1>
          <div className="member-search-container member-right-align">
            <select
              className="form-control btn-mini"
              value={searchType}
              onChange={(e) => setSearchType(Number(e.target.value))}
              style={{ width: '100px', marginRight: '13px', fontSize: '1.1rem' }}
              name="searchType"
            >
              <option value="0">검색선택</option>
              <option value="1">이메일</option>
              <option value="2">이름</option>
              <option value="3">닉네임</option>
            </select>
            <input
              className="member-search-input"
              type="text"
              value={searchValue}
              onChange={(e) => setSearchValue(e.target.value)}
            />
            <div className="member-search-button member-small-button" style={{writingMode: 'vertical-lr'}}>검색</div>
          </div>
          <table className="table table-bordered membertable">
            <thead>
              <tr>
                <th>이메일</th>
                <th>이름</th>
                <th>닉네임</th>
                <th>성별</th>
                <th>생년월일</th>
                <th>이력서등록여부</th>
                <th>담당컨설턴트</th>
                <th>회원상태</th>
                <th>가입날짜</th>
                <th>회원관리</th>
              </tr>
            </thead>
            <tbody>             
              {members.map((member) => (
                <tr key={member.id}>
                  <td>{member.memail}</td>
                  <td>{member.mname}</td>
                  <td>{member.mnick}</td>
                  <td>{member.mgender}</td>
                  <td>{member.mbirth}</td>
                  <td>{member.resumeyn}</td>
                  <td>{member.cnsltnm}</td>
                  <td>{member.status}</td>
                  <td>{member.mdate}</td>
                  <td>
                    <button className="btn btn-primary btn-sm" style={{width: '80px', height: '40px', margin:'2px'}} onClick={() => showModal(member)}>수정</button>
                    <button className="btn btn-danger btn-sm" style={{width: '80px', height: '40px'}}>삭제</button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
          <nav aria-label="Page navigation" className="pageblock">
            <ul className="pagination">
              <li >
                <a className="page-link" href="#" >Previous</a>
              </li>              
                <li className="page-item active" >
                  <a className="page-link" href="#" >1</a>
                </li>
                <li className="page-item" >
                  <a className="page-link" href="#" >2</a>
                </li> 
                <li className="page-item" >
                  <a className="page-link" href="#" >3</a>
                </li> 
                <li className="page-item" >
                  <a className="page-link" href="#" >4</a>
                </li> 
                <li className="page-item" >
                  <a className="page-link" href="#" >5</a>
                </li>              
              <li>
                <a className="page-link" href="#">Next</a>
              </li>
            </ul>
          </nav>
          <button className="btn btn-primary mb-3" >새로고침</button>     
          {popState && currentMember && (
            <MemberModal member={currentMember} onClose={closeModal} />
          )}    
        </div>
      </div>
    </div>
  );
}

export default MemberList;
