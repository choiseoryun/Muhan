import { useState, useEffect } from 'react';
import Cookies from 'js-cookie';

// Token validity check 훅
const useTokenValidity = () => {
  const [isValid, setIsValid] = useState(null);

  useEffect(() => {
    const token = Cookies.get('token');
    console.log(token);
    if (token) {
      setIsValid(true);  // 토큰이 있을 경우 유효
    } else {
      setIsValid(false);  // 토큰이 없을 경우 유효하지 않음
    }
  }, []);  // 컴포넌트가 마운트될 때 한 번만 실행

  return isValid;  // 토큰의 유효성 결과를 반환
};

export default useTokenValidity;