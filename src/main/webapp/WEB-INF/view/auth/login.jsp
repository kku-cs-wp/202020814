<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>로그인 페이지</title>
    <!-- 스타일 및 스크립트 추가 -->
    <style>
        .login-form {
            width: 300px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
        }
        .error-message {
            color: red;
        }
    </style>
</head>
<body>
    <div class="login-form">
        <!-- 시스템 이름 -->
        <h1>WebApp yunho</h1>
        <!-- 로그인 -->
        <h2>로그인</h2>
        <!-- 에러 메시지 출력 -->
        <c:if test="${not empty error}">
            <p class="error-message">${error}</p>
        </c:if>
        
        <form action="login" method="post">
            <input type="text" name="username" placeholder="Username" required>
            <input type="password" name="password" placeholder="Password" required>
            <input type="submit" value="Login">
        </form>
    </div>
</body>
</html>