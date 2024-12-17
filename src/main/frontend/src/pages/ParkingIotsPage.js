import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';

function ParkingIots() {
    const [timestamp, setTimestamp] = useState(Date.now());
    const [images, setImages] = useState({}); // Store images dynamically for each space
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    // Fetch image for a given space
    const fetchImage = async (space) => {
        setLoading(true);
        try {
            const response = await axios.get(`/api/v1/admin/image_info?space=${space}`, { responseType: "arraybuffer" });
            const imageBlob = new Blob([response.data], { type: "image/jpeg" });
            const imageUrl = URL.createObjectURL(imageBlob);
            setImages((prevImages) => ({ ...prevImages, [space]: imageUrl }));
        } catch (err) {
            setError(`이미지 로딩 실패: ${err}`);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        const spaces = ['AI_A_1', 'AI_A_2', 'AI_B_일반', 'AI_B_장애인', 'AI_C']; 
        spaces.forEach(space => fetchImage(space));
        const interval = setInterval(() => {
            spaces.forEach(space => fetchImage(space));
        }, 60000);

        return () => clearInterval(interval);
    }, []);

    const getImageUrl = (imagePath) => {
        return `${imagePath}?t=${timestamp}`;
    };

    const handleAnalyze = () => {
        navigate('/analyze');
    }

    return (
        <div style={styles.container}>
            <header style={{ textAlign: 'center', marginBottom: '20px' }}>
                <h1>주차 공간 모니터링</h1>
                <button style={{ ...styles.button, width: '10%' }}>모니터링</button>
                <button onClick={handleAnalyze} style={{ ...styles.button, width: '10%' }}>통계 및 분석</button>
            </header>
            {loading && <p>데이터를 불러오는 중...</p>}
            {error && <p style={styles.error}>{error}</p>}
            <table style={styles.table}>
                <thead>
                    <tr>
                        <th style={styles.th}>공간 번호</th>
                        <th style={styles.th}>상태</th>
                    </tr>
                </thead>
                <tbody>
                    {['AI_A_1', 'AI_A_2', 'AI_B_일반', 'AI_B_장애인', 'AI_C'].map((space) => (
                        <tr key={space}>
                            <td style={styles.td}>{space}</td>
                            <td style={styles.td}>
                                {/* Render image for each space dynamically */}
                                {images[space] ? (
                                    <img src={images[space]} alt={space} style={{ width: "600px", height: "480px" }} />
                                ) : (
                                    <p>이미지 로딩 중...</p>
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
            <footer style={{ textAlign: 'center', marginTop: '40px', color: '#888' }}>
                <img src='ParkingLogo.png' alt="Parking Logo" />
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
    table: {
        width: '100%',
        borderCollapse: 'collapse',
        margin: '0 auto',
        maxWidth: '800px',
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

export default ParkingIots;
