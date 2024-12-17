import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, Link } from 'react-router-dom';

function UserDetail({ match }) {
    const [user, setUser] = useState({});
    const { id } = useParams();
    console.log(id)
    useEffect(() => {
        axios.get(`/api/v1/admin/users/${id}`)
            .then(response => {
                setUser(response.data.data);
                console.log(response.data.data)
            })
            .catch(error => {
                console.error("서버 요청 중 오류 발생:", error);
            });
    }, []);
    return (
        <div style={styles.container}>
            <h1 style={styles.title}>사용자 상세정보 조회</h1>
            {/* {loading && <p>데이터를 불러오는 중...</p>}
            {error && <p style={styles.error}>{error}</p>} */}
            <table style={styles.table}>
                <thead>
                    <tr>
                        <th style={styles.th}>아이디</th>
                        <th style={styles.th}>이름</th>
                        <th style={styles.th}>성별</th>
                        <th style={styles.th}>핸드폰번호</th>
                        <th style={styles.th}>학과</th>
                        <th style={styles.th}>이메일</th>
                        <th style={styles.th}></th>
                    </tr>
                </thead>
            <tbody>
                <tr>
                    <td style={styles.td}>{user.name}</td>
                    <td style={styles.td}>{user.username}</td>
                    <td style={styles.td}>{user.gender}</td>
                    <td style={styles.td}>{user.phone}</td>
                    <td style={styles.td}>{user.department}</td>
                    <td style={styles.td}>{user.email}</td>
                    <td style={styles.td}>
                    <Link to={`/users/edit/${user.user_id}`}>수정</Link>
                    </td>
                </tr>
            </tbody>
            </table>
            <footer style={{
                textAlign: 'center',
                marginTop: '40px',
                color: '#888',
                }}>
                <img src='../ParkingLogo.png'></img>
                <p>
                    © 2024 가천대학교 P-실무프로젝트 무한이 주차비서 관리자 대시보드 <br />
                    Dev : Team SSHG
                </p>
        </footer>
        </div>
        
    );
}

const styles = {
    container: { padding: '20px', textAlign: 'center', backgroundColor: '#F9F9F9' },
    title: { marginBottom: '20px' },
    table: {
      width: '100%',
      borderCollapse: 'collapse',
      margin: '0 auto',
      maxWidth: '800px',
    },
    th: {
      border: '1px solid #FFF',
      padding: '8px',
      color: '#FFF',
      backgroundColor: '#014f9e',
      fontWeight: 'bold',
    },
    td: {
      border: '1px solid #ddd',
      padding: '8px',
      textAlign: 'center',
    },
    error: {
      color: 'red',
      fontWeight: 'bold',
    },
};

export default UserDetail;