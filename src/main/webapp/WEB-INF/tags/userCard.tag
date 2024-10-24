<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag description="사용자 카드 태그" pageEncoding="UTF-8"%>
<%@ attribute name="user" required="true" type="kr.ac.kku.cs.wp.nangkongmu.user.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="user-card" data-name="${user.name}" data-email="${user.email}" data-role="${fn:join(user.roles, ', ')}" data-id="${user.id}">
    <img src="${user.photoSRC}" alt="${user.name} 사진" />
    <div class="user-info">
        <h3>${user.name}</h3>
        <p><strong>이메일:</strong> ${user.email}</p>
        <p><strong>역할:</strong> ${fn:join(user.roles, ', ')}</p>
        <p><strong>사번:</strong> ${user.id}</p>
        <p><strong>상태:</strong> ${user.status}</p>
        <button onclick="alert('${user.name}의 상세 정보')">상세 보기</button>
    </div>
</div>
