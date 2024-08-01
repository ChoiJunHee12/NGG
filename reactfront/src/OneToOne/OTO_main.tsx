import React from 'react';
import { Link } from 'react-router-dom';
import './OTO_main.css';

interface Item {
  name: string;
  link: string;
  imgSrc: string;
}

const items: Item[] = [
  { name: '침착맨 님', link: '/consultant/OneToOne/detail', imgSrc: '/img/OneToOne_img/noimage.png' },
  { name: '침착맨 님', link: '/consultant/OneToOne/detail', imgSrc: '/img/OneToOne_img/noimage.png' },
  { name: '침착맨 님', link: '/consultant/OneToOne/detail', imgSrc: '/img/OneToOne_img/noimage.png' },
  { name: '침착맨 님', link: '/consultant/OneToOne/detail', imgSrc: '/img/OneToOne_img/noimage.png' },
  { name: '침착맨 님', link: '/consultant/OneToOne/detail', imgSrc: '/img/OneToOne_img/noimage.png' },
  { name: '침착맨 님', link: '/consultant/OneToOne/detail', imgSrc: '/img/OneToOne_img/noimage.png' },
  { name: '침착맨 님', link: '/consultant/OneToOne/detail', imgSrc: '/img/OneToOne_img/noimage.png' },
  { name: '침착맨 님', link: '/consultant/OneToOne/detail', imgSrc: '/img/OneToOne_img/noimage.png' },
  { name: '침착맨 님', link: '/consultant/OneToOne/detail', imgSrc: '/img/OneToOne_img/noimage.png' },
  // 필요한 만큼 더미 데이터 추가
];

const OTO_main: React.FC = () => {
  const rows = items.reduce<Item[][]>((acc, item, index) => {
    if (index % 3 === 0) acc.push([]);
    acc[Math.floor(index / 3)].push(item);
    return acc;
  }, []);

  return (
    <div className="container">
      <div className="OTO-con-1">
        <div className="OTO-con-2">
          <div className="OTO-con-3">
            <h3 style={{ fontWeight: 'bold' }}>면접자 1:1 상담</h3>
          </div>
        </div>
      </div>
      <div className="OTO-main-con">
        {rows.map((row, rowIndex) => (
          <div className="OTO-main-con-row" key={rowIndex}>
            {row.map((item, colIndex) => (
              <div className="OTO-main-profileImgBox" key={colIndex}>
                <div>
                  <Link to={item.link}>
                    <img src={item.imgSrc} alt="프로필" className="OTO-main-profileImg" />
                  </Link>
                </div>
                <div className="OTO-main-txtbox">
                  <div>
                    <h5 className="OTO-main-h5">{item.name}</h5>
                    <p>
                      새로운 메시지 <Link to={item.link} className="OTO-main-msg">✉️</Link>
                    </p>
                  </div>
                </div>
              </div>
            ))}
          </div>
        ))}
      </div>
    </div>
  );
};

export default OTO_main;
