<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>에러 페이지</title>
</head>
<body>
    <h1>오류가 발생했습니다</h1>
    <p>에러 메시지: ${exception.message}</p>
    <p>예외 유형: ${exception.getClass().getName()}</p>
</body>
</html>