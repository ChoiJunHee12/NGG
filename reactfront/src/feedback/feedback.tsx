import React from 'react';
import { Link } from 'react-router-dom';
import './feedback.css';

interface Item {
  name: string;
  date: string;
}

const items: Item[] = [
  { name: '침착맨', date: '24.07.08' },
  { name: '침착맨', date: '24.07.09' },
  { name: '침착맨', date: '24.07.10' },
  { name: '분노맨', date: '24.07.10' },
  { name: '분노맨', date: '24.07.10' },
  { name: '분노맨', date: '24.07.10' },
  { name: '중립맨', date: '24.07.10' },
  { name: '중립맨', date: '24.07.10' },
  { name: '중립맨', date: '24.07.10' },
  { name: '중립맨', date: '24.07.10' },
];

const Feedback = () => {
  return (
    <div className="container">
      <div className="feed-con-1">
        <div className="feed-con-2">
          <div className="feed-con-3">
            <h3 style={{ fontWeight: 'bold' }}>피드백 관리</h3>
          </div>
        </div>
      </div>
      <div className="feed-con">
        {items.map((item, index) => (
          <div className="feed-item" key={index}>
            <table className='feed-table'>
                <thead>
                <tr className='feed-tr'>
                    <td className='feed-td'>
                        <Link to={'detail'} className='feed-link'>
                            <p className='feed-p'>{item.name} 님 직무면접결과 {item.date}</p>
                        </Link>
                    </td>
                </tr>
                </thead>
            </table>
            </div>
        ))}
      </div>
    </div>
  );
}

export default Feedback;
