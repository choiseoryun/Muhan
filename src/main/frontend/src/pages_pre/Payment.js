import React, { useEffect } from 'react';

const Payment = () => {
  useEffect(() => {
    // 아임포트 라이브러리 로드
    const script = document.createElement('script');
    script.src = "https://cdn.iamport.kr/v1/iamport.js";
    script.async = true; 
    script.onload = () => {
      if (window.IMP) {
        console.log("Iamport 라이브러리 로드 완료");
        window.IMP.init('imp84203221'); // 아임포트 상점 식별자
      } else {
        console.error("IMP 라이브러리 로드 실패");
      }
    };
    document.body.appendChild(script);
    return () => {
      document.body.removeChild(script);
    };
  }, []);

  const onClickPay = () => {
    if (window.IMP) {
      window.IMP.request_pay({
        pg: "uplus", 
        pay_method: "card", 
        merchant_uid: `ORD${new Date().getTime()}`, 
        name: "크레딧 충전", 
        amount: 1,
        buyer_email: "test@naver.com",
        buyer_name: "홍길동",
        buyer_tel: "010-1234-5678",
        buyer_addr: "서울특별시 강남구",
        buyer_postcode: "12345",
        m_redirect_url: "http://localhost:3000/payment-complete", // 리디렉션 URL (HTTP에서 동작)
      }, function (response) {
        const { success, error_msg } = response;
        if (success) {
          alert("결제가 성공적으로 완료되었습니다.");
          console.log(response);
        } else {
          alert(`결제 실패: ${error_msg}`);
          console.error(response);
        }
      });
    } else {
      alert("아임포트가 로드되지 않았습니다.");
    }
  };

  return (
    <div>
      <button onClick={onClickPay}>결제하기</button>
    </div>
  );
};

export default Payment;
