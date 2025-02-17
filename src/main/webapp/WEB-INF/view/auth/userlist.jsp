<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h2>사용자 목록</h2>



<!-- 필터 입력 필드 -->
<div class="filter-container">
    <input type="text" id="user-filter" placeholder="이름, 이메일, 역할 또는 사번으로 검색" onkeyup="filterUsers()">
</div>

<div id="user-count" style="margin-bottom: 15px;">전체 :<strong>20</strong></div>

<div class="user-card-container" id="user-list">
    <!-- 사용자 카드 1 (여러 역할 및 사번 추가) -->
    <div class="user-card" data-name="홍길동" data-email="hong@example.com" data-role="관리자, 개발자" data-id="1001">
        <img src="https://via.placeholder.com/150" alt="홍길동 사진">
        <div class="user-info">
            <h3>홍길동</h3>
            <p><strong>이메일:</strong> hong@example.com</p>
            <p><strong>역할:</strong> 관리자, 개발자</p>
            <p><strong>사번:</strong> 1001</p>
            <p><strong>상태:</strong> 활성</p>
            <button onclick="alert('홍길동의 상세 정보')">상세 보기</button>
        </div>
    </div>

    <!-- 사용자 카드 2 -->
    <div class="user-card" data-name="이순신" data-email="lee@example.com" data-role="일반 사용자, 운영자" data-id="1002">
        <img src="https://via.placeholder.com/150" alt="이순신 사진">
        <div class="user-info">
            <h3>이순신</h3>
            <p><strong>이메일:</strong> lee@example.com</p>
            <p><strong>역할:</strong> 일반 사용자, 운영자</p>
            <p><strong>사번:</strong> 1002</p>
            <p><strong>상태:</strong> 비활성</p>
            <button onclick="alert('이순신의 상세 정보')">상세 보기</button>
        </div>
    </div>

    <!-- 사용자 카드 3 -->
    <div class="user-card" data-name="유관순" data-email="yoo@example.com" data-role="관리자, 디자이너, 마케팅" data-id="1003">
        <img src="https://via.placeholder.com/150" alt="유관순 사진">
        <div class="user-info">
            <h3>유관순</h3>
            <p><strong>이메일:</strong> yoo@example.com</p>
            <p><strong>역할:</strong> 관리자, 디자이너, 마케팅</p>
            <p><strong>사번:</strong> 1003</p>
            <p><strong>상태:</strong> 활성</p>
            <button onclick="alert('유관순의 상세 정보')">상세 보기</button>
        </div>
    </div>
    
    <!-- 사용자 카드 1 (여러 역할 및 사번 추가) -->
    <div class="user-card" data-name="홍길동" data-email="hong@example.com" data-role="관리자, 개발자" data-id="1001">
        <img src="https://via.placeholder.com/150" alt="홍길동 사진">
        <div class="user-info">
            <h3>홍길동</h3>
            <p><strong>이메일:</strong> hong@example.com</p>
            <p><strong>역할:</strong> 관리자, 개발자</p>
            <p><strong>사번:</strong> 1001</p>
            <p><strong>상태:</strong> 활성</p>
            <button onclick="alert('홍길동의 상세 정보')">상세 보기</button>
        </div>
    </div>

    <!-- 사용자 카드 2 -->
    <div class="user-card" data-name="이순신" data-email="lee@example.com" data-role="일반 사용자, 운영자" data-id="1002">
        <img src="https://via.placeholder.com/150" alt="이순신 사진">
        <div class="user-info">
            <h3>이순신</h3>
            <p><strong>이메일:</strong> lee@example.com</p>
            <p><strong>역할:</strong> 일반 사용자, 운영자</p>
            <p><strong>사번:</strong> 1002</p>
            <p><strong>상태:</strong> 비활성</p>
            <button onclick="alert('이순신의 상세 정보')">상세 보기</button>
        </div>
    </div>

    <!-- 사용자 카드 3 -->
    <div class="user-card" data-name="유관순" data-email="yoo@example.com" data-role="관리자, 디자이너, 마케팅" data-id="1003">
        <img src="https://via.placeholder.com/150" alt="유관순 사진">
        <div class="user-info">
            <h3>유관순</h3>
            <p><strong>이메일:</strong> yoo@example.com</p>
            <p><strong>역할:</strong> 관리자, 디자이너, 마케팅</p>
            <p><strong>사번:</strong> 1003</p>
            <p><strong>상태:</strong> 활성</p>
            <button onclick="alert('유관순의 상세 정보')">상세 보기</button>
        </div>
    </div>
    <!-- 사용자 카드 1 (여러 역할 및 사번 추가) -->
    <div class="user-card" data-name="홍길동" data-email="hong@example.com" data-role="관리자, 개발자" data-id="1001">
        <img src="https://via.placeholder.com/150" alt="홍길동 사진">
        <div class="user-info">
            <h3>홍길동</h3>
            <p><strong>이메일:</strong> hong@example.com</p>
            <p><strong>역할:</strong> 관리자, 개발자</p>
            <p><strong>사번:</strong> 1001</p>
            <p><strong>상태:</strong> 활성</p>
            <button onclick="alert('홍길동의 상세 정보')">상세 보기</button>
        </div>
    </div>

    <!-- 사용자 카드 2 -->
    <div class="user-card" data-name="이순신" data-email="lee@example.com" data-role="일반 사용자, 운영자" data-id="1002">
        <img src="https://via.placeholder.com/150" alt="이순신 사진">
        <div class="user-info">
            <h3>이순신</h3>
            <p><strong>이메일:</strong> lee@example.com</p>
            <p><strong>역할:</strong> 일반 사용자, 운영자</p>
            <p><strong>사번:</strong> 1002</p>
            <p><strong>상태:</strong> 비활성</p>
            <button onclick="alert('이순신의 상세 정보')">상세 보기</button>
        </div>
    </div>

    <!-- 사용자 카드 3 -->
    <div class="user-card" data-name="유관순" data-email="yoo@example.com" data-role="관리자, 디자이너, 마케팅" data-id="1003">
        <img src="https://via.placeholder.com/150" alt="유관순 사진">
        <div class="user-info">
            <h3>유관순</h3>
            <p><strong>이메일:</strong> yoo@example.com</p>
            <p><strong>역할:</strong> 관리자, 디자이너, 마케팅</p>
            <p><strong>사번:</strong> 1003</p>
            <p><strong>상태:</strong> 활성</p>
            <button onclick="alert('유관순의 상세 정보')">상세 보기</button>
        </div>
    </div>
    
    <!-- 사용자 카드 1 (여러 역할 및 사번 추가) -->
    <div class="user-card" data-name="홍길동" data-email="hong@example.com" data-role="관리자, 개발자" data-id="1001">
        <img src="https://via.placeholder.com/150" alt="홍길동 사진">
        <div class="user-info">
            <h3>홍길동</h3>
            <p><strong>이메일:</strong> hong@example.com</p>
            <p><strong>역할:</strong> 관리자, 개발자</p>
            <p><strong>사번:</strong> 1001</p>
            <p><strong>상태:</strong> 활성</p>
            <button onclick="alert('홍길동의 상세 정보')">상세 보기</button>
        </div>
    </div>

    <!-- 사용자 카드 2 -->
    <div class="user-card" data-name="이순신" data-email="lee@example.com" data-role="일반 사용자, 운영자" data-id="1002">
        <img src="https://via.placeholder.com/150" alt="이순신 사진">
        <div class="user-info">
            <h3>이순신</h3>
            <p><strong>이메일:</strong> lee@example.com</p>
            <p><strong>역할:</strong> 일반 사용자, 운영자</p>
            <p><strong>사번:</strong> 1002</p>
            <p><strong>상태:</strong> 비활성</p>
            <button onclick="alert('이순신의 상세 정보')">상세 보기</button>
        </div>
    </div>

    <!-- 사용자 카드 3 -->
    <div class="user-card" data-name="유관순" data-email="yoo@example.com" data-role="관리자, 디자이너, 마케팅" data-id="1003">
        <img src="https://via.placeholder.com/150" alt="유관순 사진">
        <div class="user-info">
            <h3>유관순</h3>
            <p><strong>이메일:</strong> yoo@example.com</p>
            <p><strong>역할:</strong> 관리자, 디자이너, 마케팅</p>
            <p><strong>사번:</strong> 1003</p>
            <p><strong>상태:</strong> 활성</p>
            <button onclick="alert('유관순의 상세 정보')">상세 보기</button>
        </div>
    </div>
</div>

<script>
    // 사용자 필터링 함수
    function filterUsers() {
        const filterValue = document.getElementById('user-filter').value.toLowerCase();
        const users = document.getElementsByClassName('user-card');

        for (let i = 0; i < users.length; i++) {
            const userName = users[i].getAttribute('data-name').toLowerCase();
            const userEmail = users[i].getAttribute('data-email').toLowerCase();
            const userRole = users[i].getAttribute('data-role').toLowerCase();
            const userId = users[i].getAttribute('data-id').toLowerCase();

            if (userName.includes(filterValue) || userEmail.includes(filterValue) || userRole.includes(filterValue) || userId.includes(filterValue)) {
                users[i].style.display = "block";
            } else {
                users[i].style.display = "none";
            }
        }
    }
</script>

<style>
    .filter-container {
        margin-bottom: 20px;
    }

    .filter-container input {
        width: 100%;
        padding: 10px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    .user-card-container {
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(180px, 1fr)); /* 카드 크기를 좁게 설정 */
        gap: 20px;
    }

    .user-card {
        background-color: white;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        overflow: hidden;
        text-align: center;
        transition: transform 0.3s ease;
        width: 180px; /* 카드 폭을 좁게 */
        height: 300px; /* 카드 높이를 길게 */
    }

    .user-card:hover {
        transform: scale(1.05);
    }

    .user-card img {
        width: 100%;
        height: 150px; /* 이미지 높이 늘림 */
        object-fit: cover;
    }

    .user-info {
        padding: 10px;
        font-size: 14px;
    }

    .user-info h3 {
        font-size: 16px;
        margin-bottom: 10px;
        color: #007bff;
    }

     .user-info p {
        font-size: 12px;
        color: #333;
        line-height : 0;
        margin-bottom : 15px;
    }


    .user-info button {
        background-color: #007bff;
        color: white;
        border: none;
        padding: 5px 10px;
        border-radius: 5px;
        cursor: pointer;
        font-size: 12px;
        margin-top: 10px;
    }

    .user-info button:hover {
        background-color: #0056b3;
    }

    /* 반응형 디자인 */
    @media (max-width: 768px) {
        .user-card-container {
            grid-template-columns: repeat(auto-fit, minmax(120px, 1fr)); /* 작은 화면에서 더 좁게 */
        }

        .user-info h3 {
            font-size: 14px;
        }

        .user-info p {
            font-size: 11px;
        }

        .user-info button {
            font-size: 10px;
        }
    }
</style>