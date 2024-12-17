import React from 'react';
import { BrowserRouter as Router, Route, Routes, useLocation } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import UserInfo from './pages/UserInfo';
import Navbar from './components/nav';
import MainPage from './pages/MainPage';
import UserDetail from './pages/UserDetail';
import ParkingIots from './pages/ParkingIotsPage';
import IoTPage from './pages/IoTPage';
//import ParkingModel from './pages/ParkingModel';
import TrafficPage from './pages/TrafficPage';
import ControlPage from './pages/ControlPage';
import AdminEditPage from './pages/AdminEditPage';
import UserEdit from './pages/UserEdit';
import AIAnalyze from './pages/AIAnalyze';

const App = () => {
    return (
        <Router> {/* 전체 애플리케이션을 Router로 감쌈 */}
            <ConditionalNavigation>
                <Routes>
                    <Route path="/" element={<LoginPage />} />
                    <Route path="/register" element={<RegisterPage />} /> 
                    <Route path='/mainPage' element={<MainPage />} />
                    <Route path="/users" element={<UserInfo />} />
                    <Route path='/users/:id' element={<UserDetail />} />
                    <Route path='/users/edit/:id' element={<UserEdit />} />
                    <Route path='/parking-Iots' element={<ParkingIots />} />
                    <Route path='/analyze' element={<AIAnalyze />} />
                    <Route path='/iot' element={<IoTPage />} />
                    <Route path='/iot/traffic-stats' element={<TrafficPage />} />
                    <Route path='/iot/control' element={<ControlPage />} />
                    <Route path='/admin/edit' element={<AdminEditPage />} />
                </Routes>
            </ConditionalNavigation>
        </Router>
    );
};

// 조건부 네비게이션 렌더링
const ConditionalNavigation = ({ children }) => {
    const location = useLocation();
    const excludePaths = ['/', '/register']; // 네비게이션 바를 제외할 경로

    return (
        <>
            {!excludePaths.includes(location.pathname) && <Navbar />}
            {children}
        </>
    );
};

export default App;