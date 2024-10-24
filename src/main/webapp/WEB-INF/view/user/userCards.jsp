
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags" prefix="custom" %>
<custom:userCard user="${user}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>사용자 목록</title>
</head>
<body>
    <h1>사용자 목록</h1>

    <!-- 검색 기능 -->
    <form action="/userlist/search" method="get" id="searchForm">
        <input type="text" name="searchKeyword" placeholder="사용자 검색">
        <button type="submit">검색</button>
    </form>

    <!-- 사용자 카드 목록 출력 -->
    <div id="user-list">
        <c:forEach var="user" items="${userList}">
            <custom:userCard user="${user}" />
        </c:forEach>
    </div>

    <script>
        // 검색 버튼 클릭 시 사용자 목록을 fetch
        document.getElementById('searchForm').onsubmit = function(event) {
            event.preventDefault();
            const formData = new FormData(this);
            fetch(this.action, {
                method: this.method,
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


<!-- userCards.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/tags" prefix="custom" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ tag pageEncoding="UTF-8" %>

<!-- 사용자 카드 JSP 부분 -->
<custom:userCard user="${user}" />


<!-- custom userCard.tag 파일 정의 -->
<%@ tag description="사용자 카드 태그" pageEncoding="UTF-8"%>
<%@ attribute name="user" required="true" type="kr.ac.kku.cs.wp.nangkongmu.user.entity.User" %>

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
