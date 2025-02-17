<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.ac.kku.cs.wp.nangkongmu.user.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사용자 목록</title>
    <h2>사용자 목록</h2>

    <!-- 필터 입력 필드 -->
    <div class="filter-container">
        <input type="text" id="user-filter" placeholder="이름, 이메일, 역할 또는 사번으로 검색" onkeyup="filterUsers()">
    </div>

    <!-- 검색 기능 -->
    <form action="/userlist/search" method="post" id="searchForm">
        <input type="text" name="searchKeyword" placeholder="사용자 검색">
        <button type="submit">검색</button>
    </form>

    <div id="user-count" style="margin-bottom: 15px;">전체 : <strong>${fn:length(userList)}</strong></div>

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
            grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
            gap: 20px;
        }

        .user-card {
            background-color: white;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            text-align: center;
            transition: transform 0.3s ease;
            width: 180px;
            height: 300px;
        }

        .user-card:hover {
            transform: scale(1.05);
        }

        .user-card img {
            width: 100%;
            height: 150px;
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
            line-height: 0;
            margin-bottom: 15px;
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

        @media (max-width: 768px) {
            .user-card-container {
                grid-template-columns: repeat(auto-fit, minmax(120px, 1fr));
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
</head>
<body>
<%
    // 사용자 목록 생성 코드
    List<User> users = new ArrayList<>();
    
    String[] names = {"김철수", "안영호", "안종균", "유관순", "황동호", "김좌진", "남자현", "윤봉길", "이창행", "김원희"};
    String[] emails = {"kim1@kku.ac.kr", "an1@kku.ac.kr", "an2@kku.ac.kr", "yu@kku.ac.kr", "hwang@kku.ac.kr", "kim2@kku.ac.kr", "nam@kku.ac.kr", "yun@kku.ac.kr", "lee@kku.ac.kr", "kim3@kku.ac.kr"};
    String[] roles = {"관리자", "개발자", "일반 사용자", "시스템 관리자"};

    for(int i = 0; i < 10; i++) {
        User user = new User();
        user.setPhotoSRC("https://via.placeholder.com/150");
        user.setName(names[i % names.length]);
        user.setEmail(emails[i % emails.length]);
        user.setId("kku-" + (1000 + i));
        List<String> userRoles = new ArrayList<String>();
        userRoles.add(roles[i % roles.length]);
        userRoles.add(roles[(i + 1) % roles.length]);
        user.setRoles(userRoles);
        user.setStatus(i % 2 == 0 ? "활성" : "비활성");
        users.add(user);
    }

    request.setAttribute("userList", users);
%>

<div class="user-card-container" id="user-list">
    <c:forEach var="user" items="${userList}">
        <div class="user-card" data-name="${user.name}" data-email="${user.email}" data-role="${fn:join(user.roles, ', ')}" data-id="${user.id}">
            <img src="${user.photoSRC}" alt="${user.name} 사진">
            <div class="user-info">
                <h3>${user.name}</h3>
                <p><strong>이메일:</strong> ${user.email}</p>
                <p><strong>역할:</strong> ${fn:join(user.roles, ', ')}</p>
                <p><strong>사번:</strong> ${user.id}</p>
                <p><strong>상태:</strong> ${user.status}</p>
                <button onclick="alert('${user.name}의 상세 정보')">상세 보기</button>
            </div>
        </div>
    </c:forEach>
</div>

<script>
    // 검색 버튼 클릭 시 사용자 목록을 fetch
    document.getElementById('searchForm').onsubmit = function(event) {
        event.preventDefault();
        const formData = new FormData(this);
        fetch(this.action, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams(formData)
        }).then(response => response.json())
          .then(data => {
            const userListDiv = document.getElementById('user-list');
            userListDiv.innerHTML = '';
            data.forEach(user => {
                // 사용자 카드 HTML 동적 생성
                const userCard = document.createElement('div');
                userCard.classList.add('user-card');
                userCard.innerHTML = `
                    <img src="${user.photoSRC}" alt="${user.name} 사진">
                    <div class="user-info">
                        <h3>${user.name}</h3>
                        <p><strong>이메일:</strong> ${user.email}</p>
                        <p><strong>역할:</strong> ${user.roles.join(', ')}</p>
                        <p><strong>사번:</strong> ${user.id}</p>
                        <p><strong>상태:</strong> ${user.status}</p>
                        <button onclick="alert('${user.name}의 상세 정보')">상세 보기</button>
                    </div>
                `;
                userListDiv.appendChild(userCard);
            });
          });
    };
</script>
</body>
</html>
