import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import Cookies from "js-cookie";

function LoginPage() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();


    const handleSubmit = (event) => {
        event.preventDefault();

        const data = { username: username, password: password };
        console.log(data)

        fetch('/api/v1/user/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                const token = data.data.token;
                Cookies.set('token', token, { expires: 7, path: '' });
                navigate('/mainPage');
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    };

    const handleSignUp = () => {
        navigate('/register');
    };

    const handleFindEmail = () => {
        alert('아이디(이메일) 찾기 페이지로 이동합니다.');
    };

    const handleFindPassword = () => {
        alert('비밀번호 찾기 페이지로 이동합니다.');
    };

    return (
        <div style={styles.outerContainer}>
            <div style={styles.container}>
                <h2>로그인</h2>
                {error && <p style={styles.error}>{error}</p>}
                <form onSubmit={handleSubmit} style={styles.form}>
                    <div style={styles.inputGroup}>
                        <label htmlFor="username">아이디</label>
                        <input
                            type="text"
                            id="username"
                            name="username"
                            value={setUsername.username}
                            onChange={(e) => setUsername(e.target.value)}
                            placeholder="아이디 입력"
                            style={styles.input}
                        />
                    </div>
                    <div style={styles.inputGroup}>
                        <label htmlFor="password">비밀번호</label>
                        <input
                            type="password"
                            id="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            placeholder="비밀번호 입력"
                            style={styles.input}
                        />
                    </div>
                    <button type="submit" style={{ ...styles.button, width: '100%' }}>로그인</button>
                </form>
            </div>
        </div>
    );
}

const styles = {
    outerContainer: {
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center',
        height: '100vh', // 화면 전체 높이를 차지
        margin: 0,
        backgroundColor: '#f8f9fa', // 배경색 (선택 사항)
    },
    container: {
        maxWidth: '400px',
        width: '100%', // 반응형: 작은 화면에서도 잘 보이도록
        margin: '0 auto',
        padding: '20px',
        border: '1px solid #ddd',
        borderRadius: '8px',
        textAlign: 'center',
        boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
        backgroundColor: '#fff', // 흰색 배경 (선택 사항)
    },
    form: {
        display: 'flex',
        flexDirection: 'column',
    },
    inputGroup: {
        marginBottom: '15px',
        textAlign: 'left',
    },
    input: {
        width: '100%',
        padding: '10px',
        fontSize: '16px',
        marginTop: '5px',
        borderRadius: '4px',
        border: '1px solid #ccc',
        boxSizing: 'border-box',
    },
    button: {
        padding: '10px 15px',
        fontSize: '16px',
        color: '#fff',
        background: 'linear-gradient(90deg, #007BFF 0%, #00C6FF 100%)', // 그라디언트 색상
        border: 'none',
        borderRadius: '4px',
        cursor: 'pointer',
        transition: 'background 0.3s',
    },
    error: {
        color: 'red',
        marginBottom: '15px',
    },
    linkContainer: {
        marginTop: '20px',
        display: 'flex',
        justifyContent: 'space-between',
        gap: '10px',
    },
    linkButton: {
        backgroundColor: 'transparent',
        border: 'none',
        color: '#007BFF',
        fontSize: '14px',
        cursor: 'pointer',
        textDecoration: 'underline',
        padding: 0,
    },
};

export default LoginPage;