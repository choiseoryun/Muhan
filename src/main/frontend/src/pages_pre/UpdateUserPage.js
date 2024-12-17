import React, { useState, useEffect } from 'react';
import { useLocation, useParams } from 'react-router-dom';
import axios from 'axios';

const UpdateUserPage = () => {
    const { id } = useParams();
    const [user, setUser] = useState(null);

    useEffect(() => {
        if (id) {
            axios.get(`http://localhost:3001/user/${id}`)
                .then(response => {
                    console.log(response);
                    setUser(response.data);
                })
                .catch(error => {
                    console.error("서버 요청 중 오류 발생:", error);
                });
        }
    }, [id]);
    return (
        <div>
            {user ? (
                <div>
                    <h1>사용자 정보</h1>
                    <p>사용자 ID: {id}</p>
                    {/* 사용자 정보 출력 */}
                    <p>{JSON.stringify(user)}</p>
                </div>
            ) : (
                <p>사용자 정보를 로딩 중...</p>
            )}
        </div>
    );
};

export default UpdateUserPage