<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h2>사용자 정보 입력</h2>

<div class="form-container">
    <form id="userForm">
        <label for="name">이름:</label>
        <input type="text" id="name" name="name" placeholder="이름을 입력하세요" required>

        <label for="email">이메일:</label>
        <input type="email" id="email" name="email" placeholder="이메일을 입력하세요" required>

        <label for="id">사번:</label>
        <input type="text" id="id" name="id" placeholder="사번을 입력하세요" required>

        <label for="role">역할:</label>
        <input type="text" id="role" name="role" placeholder="역할을 입력하세요 (쉼표로 구분)" required>

        <label for="status">상태:</label>
        <select id="status" name="status" required>
            <option value="활성">활성</option>
            <option value="비활성">비활성</option>
        </select>

        <button type="submit">사용자 추가</button>
    </form>
</div>

<style>
    /* 폼 스타일 */
    .form-container {
        background-color: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        max-width: 400px;
        margin: 0 auto;
    }

    .form-container h2 {
        text-align: center;
        color: #007bff;
        margin-bottom: 20px;
    }

    .form-container label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
    }

    .form-container input,
    .form-container select {
        width: 100%;
        padding: 10px;
        margin-bottom: 15px;
        border: 1px solid #ccc;
        border-radius: 5px;
        font-size: 16px;
    }

    .form-container button {
        width: 100%;
        padding: 10px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 5px;
        font-size: 16px;
        cursor: pointer;
    }

    .form-container button:hover {
        background-color: #0056b3;
    }
</style>

<script>
    // 사용자 정보 폼 제출 시 동작
    document.getElementById('userForm').addEventListener('submit', function(event) {
        event.preventDefault(); // 폼 제출 시 새로고침 방지

        const name = document.getElementById('name').value;
        const email = document.getElementById('email').value;
        const id = document.getElementById('id').value;
        const role = document.getElementById('role').value;
        const status = document.getElementById('status').value;

        // 콘솔에 입력된 정보 출력 (실제 구현에서는 서버로 전송하거나 저장)
        console.log(`사용자 추가: 이름=${name}, 이메일=${email}, 사번=${id}, 역할=${role}, 상태=${status}`);

        // 폼 초기화
        document.getElementById('userForm').reset();
    });
</script>