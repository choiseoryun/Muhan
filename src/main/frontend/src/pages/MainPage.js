import axios from 'axios';
import React, { useEffect, useState } from 'react';
import { Link, useNavigate, useParams, useLocation } from 'react-router-dom';

const MainPage = () => {

    return (
        <div style={{ fontFamily: 'Arial, sans-serif', padding: '20px', backgroundColor: '#F9F9F9' }}>
            {/* 헤더 */}
            <header style={{
                textAlign: 'center',
                marginBottom: '20px',
            }}>
                <div>
                    <h1 style={{ color: '#0F0F0F' }}>관리자 대시보드</h1>
                    <p style={{ color: '#0F0F0F' }}>환영합니다! 아래에서 필요한 작업을 선택하세요.</p>
                </div>
            </header>
            <br /><br />
            {/* 주요 메뉴 카드 */}
            <div style={{
                display: 'grid',
                gridTemplateColumns: 'repeat(3, 1fr)', // 3열로 고정
                gridGap: '20px', // 카드 사이 간격
                maxWidth: '1200px', // 중앙 정렬을 위해 최대 너비 설정
                margin: '0 auto', // 가운데 정렬
            }}>
                <Link to="/users" style={cardStyle}>
                    <h2>사용자 정보 조회</h2>
                    <p>등록된 사용자 정보를 확인합니다.</p>
                </Link>
                <Link to="/parking-Iots" style={cardStyle}>
                    <h2>주차공간 모니터링</h2>
                    <p>실시간 주차공간 정보를 확인합니다.</p>
                </Link>
                <Link to="/iot" style={cardStyle}>
                    <h2>AIoT 관련 조회</h2>
                    <p>AI와 IoT 장치 정보를 관리하고 확인합니다.</p>
                </Link>
            </div>

            {/* 푸터 */}
            <footer style={{
                textAlign: 'center',
                marginTop: '40px',
                color: '#888',
            }}>
                <img src='ParkingLogo.png'></img>
                <p>© 2024 가천대학교 P-실무프로젝트 무한이 주차비서 관리자 대시보드</p>
            </footer>
        </div>
    );
};

// 카드 스타일
const cardStyle = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    padding: '20px',
    textDecoration: 'none',
    color: '#0F0F0F',
    backgroundColor: '#F9F9F9',
    border: '4px solid #fcaf17',
    borderRadius: '8px',
    textAlign: 'center',
    transition: 'transform 0.2s, box-shadow 0.2s',
    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
};

export default MainPage;