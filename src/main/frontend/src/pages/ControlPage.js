import React, { useState } from "react";
import axios from "axios";

function Control() {
    const [iotData, setIotData] = useState([
        { id: 1, status: "ACTIVE" },
        { id: 2, status: "ACTIVE" },
        { id: 3, status: "ACTIVE" },
        { id: 4, status: "ACTIVE" },
    ]);

    const [tempStatus, setTempStatus] = useState({});

    const handleStatusChange = (id, newStatus) => {
        setTempStatus((prev) => ({
            ...prev,
            [id]: newStatus,
        }));
    };

    const handleActive = async () => {
        try {
            const updatedData = iotData.map((item) => ({
                ...item,
                status: tempStatus[item.id] || item.status,
            }));

            await Promise.all(
                updatedData.map((item) =>
                    axios.put(`http://ceprj.gachon.ac.kr:60010/api/v1/${item.id}/status`, { status: item.status })
                        .then((response) => {
                            console.log('서버 응답:', response);  // 응답 확인
                        })
                        .catch((error) => {
                            console.error('개별 요청 실패:', error);
                        })
                )
            );
            setIotData(updatedData);
            setTempStatus({});
            alert("수정되었습니다.");
        } catch (error) {
            console.error("서버 요청 실패:", error);
            alert("상태 변경 실패");
        }
    };

    return (
        <div style={styles.container}>
            <header style={{ textAlign: "center", marginBottom: "20px" }}>
                <h1 style={styles.title}>AIoT 활성 상태 제어</h1>
            </header>
            <table style={styles.table}>
                <thead>
                <tr>
                    <th style={styles.th}>AIoT 번호</th>
                    <th style={styles.th}>AIoT 제어 정보</th>
                    <th style={styles.th}>상태 변경</th>
                </tr>
                </thead>
                <tbody>
                {iotData.map((item) => (
                    <tr key={item.id}>
                        <td style={styles.td}>{item.id}</td>
                        <td style={styles.td}>{item.status}</td>
                        <td style={styles.td}>
                            <select
                                value={tempStatus[item.id] || item.status}
                                onChange={(e) => handleStatusChange(item.id, e.target.value)}
                            >
                                <option value="INACTIVE">INACTIVE</option>
                                <option value="ACTIVE">ACTIVE</option>
                            </select>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
            <button onClick={handleActive} style={{ ...styles.button, width: "10%" }}>
                확인
            </button>
            <footer
                style={{
                    textAlign: "center",
                    marginTop: "40px",
                    color: "#888",
                }}
            >
                <img src="../ParkingLogo.png" alt="logo" />
                <p>
                    © 2024 가천대학교 P-실무프로젝트 무한이 주차비서 관리자 대시보드 <br />
                    Dev : Team SSHG
                </p>
            </footer>
        </div>
    );
}

const styles = {
    container: { padding: "20px", textAlign: "center", backgroundColor: "#F9F9F9" },
    title: { marginBottom: "20px" },
    table: {
        width: "100%",
        borderCollapse: "collapse",
        margin: "0 auto",
        maxWidth: "800px",
    },
    th: {
        border: "1px solid #FFF",
        padding: "8px",
        color: "#FFFFFF",
        backgroundColor: "#014f9e",
        fontWeight: "bold",
    },
    td: {
        border: "1px solid #ddd",
        padding: "8px",
        textAlign: "center",
    },
    button: {
        padding: "10px 15px",
        fontSize: "16px",
        color: "#fff",
        background: "#00C6FF",
        border: "none",
        borderRadius: "4px",
        cursor: "pointer",
        margin: "10px",
    },
};

export default Control;
