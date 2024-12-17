import React from "react";
import { useNavigate } from "react-router-dom";

function AdminEditPage() {
    return (
        <div style={styles.container}>
          <h2 style={styles.title}>관리자 정보 수정</h2>
          <form 
            //</div>onSubmit={handleSubmit} 
            style={styles.form}>
            <div style={styles.inputGroup}>
              <label htmlFor="name">이름</label>
              <input
                type="text"
                id="name"
                name="name"
                // value={adminInfo.name}
                // onChange={handleChange}
                placeholder="이름 입력"
                style={styles.input}
              />
            </div>
            <div style={styles.inputGroup}>
              <label htmlFor="email">아이디</label>
              <input
                type="id"
                id="id"
                name="id"
                //value={adminInfo.email}
                //onChange={handleChange}
                placeholder="아이디 입력"
                style={styles.input}
              />
            </div>
            <div style={styles.inputGroup}>
              <label htmlFor="phone">비밀번호</label>
              <input
                type="password"
                id="password"
                name="password"
                //value={adminInfo.phone}
                //onChange={handleChange}
                placeholder="비밀번호 입력"
                style={styles.input}
              />
            </div>
            <button type="submit" style={styles.button}>
              정보 수정
            </button>
          </form>
            <footer
              style={{
                textAlign: "center",
                marginTop: "40px",
                color: "#888",
              }}
            >
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
    container: { padding: "20px", maxWidth: "600px", margin: "0 auto" },
    title: { textAlign: "center", marginBottom: "20px" },
    form: { display: "flex", flexDirection: "column", gap: "15px" },
    inputGroup: { display: "flex", flexDirection: "column", gap: "5px" },
    input: {
      padding: "10px",
      fontSize: "16px",
      borderRadius: "4px",
      border: "1px solid #ccc",
    },
    button: {
      padding: "10px 20px",
      fontSize: "16px",
      color: "#fff",
      background: "#007BFF",
      border: "none",
      borderRadius: "4px",
      cursor: "pointer",
    },
  };
  
  export default AdminEditPage;