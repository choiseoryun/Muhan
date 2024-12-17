import React, { useState } from 'react';
import Cookies from 'js-cookie';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = (event) => {
        event.preventDefault();

        const data = {
            username: username,
            password: password
        };

        // JSON 데이터 전송
        fetch('/api/v1/user/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())
            .then(data => {
                console.log('Success:', data);
                const token = data.data.token;
                Cookies.set('token', token, { expires: 7, path: '' });
            })
            .catch((error) => {
                console.error('Error:', error);
            });
    };

    return (
        <div>
            <form id="post-form" onSubmit={handleSubmit}>
                <input
                    autocomplete="off"
                    type="text"
                    name="username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)} // 상태 업데이트
                />
                <input
                    autocomplete="off"
                    type="password"
                    name="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)} // 상태 업데이트
                />
                <input type="submit" />
            </form>
        </div>
    );
}

export default Login;