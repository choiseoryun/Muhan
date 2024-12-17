import React, {useState} from "react";

const RegisterPage = () => {
    const [formData, setFormData] = useState({
      username: '',
      email: '',
      password: '',
      fullName: '',
      phone: '',
      address: '',
      bankAccount: '',
      gender: '',
      studentId: '',
      department: '',
      birthDate: ''
    });
  
    const [error, setError] = useState('');
  
    const handleChange = (e) => {
      const { name, value } = e.target;
      setFormData((prevState) => ({
        ...prevState,
        [name]: value
      }));
    };
  
    const handleSubmit = (e) => {
      e.preventDefault();
      const { username, email, password, fullName, phone, address, bankAccount, gender, studentId, department, birthDate } = formData;
  
      // Validation (예시)
      if (!username || !email || !password || !fullName || !phone || !address || !bankAccount || !gender || !studentId || !department || !birthDate) {
        setError('모든 필드를 입력해주세요.');
        return;
      }
  
      // 회원가입 로직 (예: API 호출)
      console.log('회원가입 시도:', formData);
      setError('');
      alert('회원가입 성공!');
    };
  
    return (
      <div style={styles.pageContainer}>
        <div style={styles.container}>
            <h2>회원가입</h2>
            {error && <p style={styles.error}>{error}</p>}
            <form onSubmit={handleSubmit} style={styles.form}>
            <div style={styles.gridContainer}>
                <div style={styles.inputGroup}>
                <label htmlFor="username">아이디</label>
                <input
                    type="text"
                    id="username"
                    name="username"
                    value={formData.username}
                    onChange={handleChange}
                    placeholder="아이디 입력"
                    style={styles.input}
                />
                </div>
                <div style={styles.inputGroup}>
                <label htmlFor="email">이메일</label>
                <input
                    type="email"
                    id="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    placeholder="이메일 입력"
                    style={styles.input}
                />
                </div>
                <div style={styles.inputGroup}>
                <label htmlFor="password">비밀번호</label>
                <input
                    type="password"
                    id="password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                    placeholder="비밀번호 입력"
                    style={styles.input}
                />
                </div>
                <div style={styles.inputGroup}>
                <label htmlFor="fullName">이름</label>
                <input
                    type="text"
                    id="fullName"
                    name="fullName"
                    value={formData.fullName}
                    onChange={handleChange}
                    placeholder="이름 입력"
                    style={styles.input}
                />
                </div>
                <div style={styles.inputGroup}>
                <label htmlFor="phone">전화번호</label>
                <input
                    type="text"
                    id="phone"
                    name="phone"
                    value={formData.phone}
                    onChange={handleChange}
                    placeholder="전화번호 입력"
                    style={styles.input}
                />
                </div>
                <div style={styles.inputGroup}>
                <label htmlFor="address">주소</label>
                <input
                    type="text"
                    id="address"
                    name="address"
                    value={formData.address}
                    onChange={handleChange}
                    placeholder="주소 입력"
                    style={styles.input}
                />
                </div>
                <div style={styles.inputGroup}>
                <label htmlFor="bankAccount">계좌번호</label>
                <input
                    type="text"
                    id="bankAccount"
                    name="bankAccount"
                    value={formData.bankAccount}
                    onChange={handleChange}
                    placeholder="계좌번호 입력"
                    style={styles.input}
                />
                </div>
                <div style={styles.inputGroup}>
                <label htmlFor="gender">성별</label>
                <select
                    id="gender"
                    name="gender"
                    value={formData.gender}
                    onChange={handleChange}
                    style={styles.input}
                >
                    <option value="">성별 선택</option>
                    <option value="male">남성</option>
                    <option value="female">여성</option>
                    <option value="other">기타</option>
                </select>
                </div>
                <div style={styles.inputGroup}>
                <label htmlFor="studentId">학번</label>
                <input
                    type="text"
                    id="studentId"
                    name="studentId"
                    value={formData.studentId}
                    onChange={handleChange}
                    placeholder="학번 입력"
                    style={styles.input}
                />
                </div>
                <div style={styles.inputGroup}>
                <label htmlFor="department">학과</label>
                <input
                    type="text"
                    id="department"
                    name="department"
                    value={formData.department}
                    onChange={handleChange}
                    placeholder="학과 입력"
                    style={styles.input}
                />
                </div>
                <div style={styles.inputGroup}>
                <label htmlFor="birthDate">생년월일</label>
                <input
                    type="date"
                    id="birthDate"
                    name="birthDate"
                    value={formData.birthDate}
                    onChange={handleChange}
                    style={styles.input}
                />
                </div>
            </div>
            <button type="submit" style={{ ...styles.button, width: '100%' }}>회원가입</button>
            </form>
        </div>
      </div>
    );
  };

  const styles = {
    pageContainer: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        height: '100vh', // 화면 세로 길이를 꽉 채워서 수직 중앙 정렬
        margin: '0', // 기본 여백 제거
        backgroundColor: '#f4f4f4', // 배경색 추가 (원하는 색으로 변경 가능)
    },
    container: {
      maxWidth: '600px',
      margin: '0 auto',
      padding: '20px',
      border: '1px solid #ddd',
      borderRadius: '10px',
      backgroundColor: '#f9f9f9',
      textAlign: 'center',
    },
    form: {
      display: 'flex',
      flexDirection: 'column'
    },
    gridContainer: {
      display: 'grid',
      gridTemplateColumns: '1fr 1fr',
      gap: '15px',
      marginBottom: '15px',
      textAlign: 'left',
    },
    inputGroup: {
      display: 'flex',
      flexDirection: 'column'
    },
    input: {
      padding: '8px',
      borderRadius: '5px',
      border: '1px solid #ccc',
      fontSize: '16px'
    },
    button: {
      padding: '10px',
      backgroundColor: '#4CAF50',
      color: 'white',
      border: 'none',
      borderRadius: '5px',
      cursor: 'pointer',
      background: 'linear-gradient(90deg, #007BFF 0%, #00C6FF 100%)',
      fontSize: '16px'
    },
    error: {
      color: 'red',
      marginBottom: '10px',
    }
};
  
export default RegisterPage;