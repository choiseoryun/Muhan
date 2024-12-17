import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

function UserPage() {
  const [users, setUsers] = useState([]); // 초기 상태를 빈 배열로 설정

  useEffect(() => {
    axios.get('/api/v1/admin/users')
      .then(response => {
        setUsers(response.data);
        console.log(response.data)
      })
      .catch(error => {
        console.error("서버 요청 중 오류 발생:", error);
      });
  }, []);

  return (
    <div>
      <h2>유저 정보</h2>
      <pre>
        {Object.keys(users).map((key, index) => (
          <div>
            이름: {users[key].name} <br />
            학번: {users[key].studentId}
            <form action={`/api/v1/admin/users/${users[key].id}?_method=DELETE`} method="POST" style={{ display: 'inline' }}>
              <input type="submit" className="btn delete" title="삭제" value="X" />
            </form>
            <Link to={`/api/v1/admin/users/${users[key].id}`}>수정</Link>
          </div>
        ))}
      </pre>
    </div>
  );

}

export default UserPage;