import React, { useState } from 'react';
import './Consult_Question.css';

const ConsultQuestion: React.FC = () => {
  const [showForm, setShowForm] = useState(false);

  // 질문과 키워드의 타입을 명시적으로 설정
  const [questions, setQuestions] = useState<{ question: string, keywords: string[] }[]>([]);
  const [currentQuestion, setCurrentQuestion] = useState('');
  const [currentKeywords, setCurrentKeywords] = useState(['', '', '']);

  const handleAddQuestion = () => {
    if (currentQuestion && currentKeywords.some(keyword => keyword)) {
      // 질문 목록이 5개 미만일 때만 질문 추가
      if (questions.length < 5) {
        setQuestions([
          ...questions,
          { question: currentQuestion, keywords: currentKeywords },
        ]);
        setCurrentQuestion('');
        setCurrentKeywords(['', '', '']);
      } else {
        alert('질문은 최대 5개까지 등록할 수 있습니다.');
      }
    }
  };

  return (
    <div className="container question-container">
      <div className='question-title'>
        <h2>직무 질문 등록하기</h2>
      </div>
      {showForm && (
        <div className="question-input">
          <input 
            placeholder='질문을 등록하세요'
            className="form-control form-control-lg question-q" 
            type="text"             
            value={currentQuestion}
            onChange={(e) => setCurrentQuestion(e.target.value)}
          />       
          {currentKeywords.map((keyword, index) => (
            <input 
              key={index}
              type='text' 
              className='form-control form-control-sm question-key'
              placeholder='키워드를 등록하세요'
              value={keyword}
              onChange={(e) => {
                const newKeywords = [...currentKeywords];
                newKeywords[index] = e.target.value;
                setCurrentKeywords(newKeywords);
              }}
            />
          ))}
          <div className='question-button'>
            <button className='btn btn-pre' onClick={handleAddQuestion}>등록</button>
          </div>
        </div>
      )}
      {!showForm && (
      <div className='question-button '>
        <button className='btn btn-pre' onClick={() => setShowForm(!showForm)}>질문 추가</button>
      </div>
      )}
      <br/>
      {questions.map((item, index) => (
        <div className='question-list' key={index}>
          <div className='question-list-left'>
            <h1>Q{index + 1}.<br/></h1> {item.question}
          </div>
          <div className='question-list-right'>
            <h1>⭐ Q{index + 1} 키워드 ⭐<br/></h1>{item.keywords.join(', ')}
          </div>
        </div>
      ))}      
    </div>
  );
};

export default ConsultQuestion;
