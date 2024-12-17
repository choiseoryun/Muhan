import React from "react";
import { useNavigate } from 'react-router-dom';

function Analyze() {
    const navigate = useNavigate();

    const handleMonitoring = () => {
        navigate('/parking-Iots');
    }

    return (
        <div style={styles.container}>
            <header style={{
                textAlign: 'center',
                marginBottom: '20px',
            }}>
                <h1>통계 및 분석</h1>
                <button onClick={handleMonitoring} style={{ ...styles.button, width: '10%' }}>모니터링</button>
                <button style={{ ...styles.button, width: '10%' }}>통계 및 분석</button>
            </header>
            {/* {loading && <p>데이터를 불러오는 중...</p>}
            {error && <p style={styles.error}>{error}</p>} */}
            <table style={styles.table}>
                <thead>
                    <tr>
                        <th style={styles.th}>공간 번호</th>
                        <th style={styles.th}>분석 상태</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td style={styles.td}>AI-A</td>
                        <td style={styles.td}><img src="A_statistics.png" style={{width: "800px", height: "600px" }}/></td>
                    </tr>
                    <tr>
                        <td style={styles.td}>AI-B</td>
                        <td style={styles.td}><img src="B_statistics.png" style={{width: "800px", height: "600px" }}/></td>
                    </tr>
                    <tr>
                        <td style={styles.td}>AI-C</td>
                        <td style={styles.td}><img src="C_statistics.png" style={{width: "800px", height: "600px" }}/></td>
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
      maxWidth: '1000px',
    },
    th: {
      border: '1px solid #FFF',
      padding: '8px',
      color: '#FFFFFF',
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
    button: {
        padding: '10px 15px',
        fontSize: '16px',
        color: '#fff',
        background: '#00C6FF',
        border: 'none',
        borderRadius: '4px',
        cursor: 'pointer',
        margin: '10px',
    },
};

export default Analyze;