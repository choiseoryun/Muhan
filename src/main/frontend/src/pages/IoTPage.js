import React from "react";
import { useNavigate } from "react-router-dom";

function IoTPage() {
    const navigate = useNavigate();

    const handleLocation = () => {
        navigate('/iot/location');
    }

    const handleTraffic = () => {
        navigate('/iot/traffic-stats');
    }

    const handleControl = () => {
        navigate('/iot/control');
    }

    return (
        <div style={styles.container}>
            <header style={{
                textAlign: 'center',
                marginBottom: '20px',
            }}>
                <h1>AIoT 관련 메뉴</h1>
            </header>
            
            <button onClick={handleTraffic} style={{ ...styles.button, width: '10%' }}>IoT 트래픽 모니터링</button>
            <button onClick={handleControl} style={{ ...styles.button, width: '10%' }}>AIoT 활성 상태 제어</button>

            <footer style={{
                textAlign: 'center',
                marginTop: '40px',
                color: '#888',
                }}>
                <img src='ParkingLogo.png'></img>
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
    button: {
        padding: '10px 15px',
        fontSize: '16px',
        color: '#fff',
        background: '#00C6FF',
        border: 'none',
        borderRadius: '4px',
        cursor: 'pointer',
        margin: '10px'
    },
};

export default IoTPage;