import React from 'react';
import { Link } from 'react-router-dom';
import Cookies from 'js-cookie';

const Navbar = () => {

  const handleLogout = () => {
    //쿠키로 토큰 삭제하는 기능.
    Cookies.remove('token');
  }

  return (
    <nav style={{
        backgroundColor: '#014f9e',
        padding: '10px',
        display: 'flex',
        justifyContent: 'center'
      }}>
        <ul style={{
          display: 'flex',
          listStyle: 'none',
          margin: 0,
          padding: 0,
          width: '100%',
          justifyContent: 'space-evenly',
          alignItems: 'center',
        }}>
          <li style={{
            padding: '5px 10px',
            border: '1px solid #61dafb',
            borderRadius: '5px',
          }}>
            <Link to="/mainPage" style={{ color: '#F9F9F9', textDecoration: 'none' }}>메인 화면</Link>
          </li>
          <li style={{
            padding: '5px 10px',
            border: '1px solid #61dafb',
            borderRadius: '5px',
          }}>
            <Link to="/users" style={{ color: '#F9F9F9', textDecoration: 'none' }}>사용자 정보 조회</Link>
          </li>
          <li style={{
            padding: '5px 10px',
            border: '1px solid #61dafb',
            borderRadius: '5px',
          }}>
            <Link to="/parking-Iots" style={{ color: '#F9F9F9', textDecoration: 'none' }}>주차공간 모니터링</Link>
          </li>
          <li style={{
            padding: '5px 10px',
            border: '1px solid #61dafb',
            borderRadius: '5px',
          }}>
            <Link to="/iot" style={{ color: '#F9F9F9', textDecoration: 'none' }}>AIoT 관련 메뉴</Link>
          </li>
          <li style={{ 
            padding: '5px 10px',
            border: '1px solid #61dafb',
            borderRadius: '5px', }}>
            <Link to="/admin/edit" style={{ color: '#F9F9F9', textDecoration: 'none' }}>정보 수정</Link>
          </li>
          <li style={{
            padding: '5px 10px',
            border: '1px solid #61dafb',
            borderRadius: '5px',
          }}>
            <Link to="/" onClick={handleLogout} style={{ color: '#F9F9F9', textDecoration: 'none' }}>로그아웃</Link>
          </li>
        </ul>
      </nav>
  );
};

export default Navbar;