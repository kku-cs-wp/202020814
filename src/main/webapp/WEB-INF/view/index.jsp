<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Webapp nangkongmu</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        header {
            background-color: #007bff;
            color: white;
            padding: 20px;
            font-size: 24px;
            position: sticky;
            top: 0;
            display: flex;
            align-items: center;
            z-index: 1001;
        }

        header button {
            background-color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 18px;
            color: #007bff;
            margin-right: 10px;
        }

        header button:hover {
            background-color: #0056b3;
            color: white;
        }

        .layout {
            display: flex;
            flex: 1;
        }

        .sidebar-container {
            width: 250px;
            background-color: #343a40;
            color: white;
            padding: 20px;
            display: flex;
            flex-direction: column;
            height: 100%;
        }

        .sidebar-container.hidden {
            width: 0;
            padding: 0;
            overflow: hidden;
        }

        nav ul {
            list-style: none;
            padding-left: 0;
        }

        nav ul li {
            margin-bottom: 15px;
        }

        nav ul li a {
            color: white;
            text-decoration: none;
            font-size: 18px;
            display: block;
        }

        nav ul li a:hover {
            color: #007bff;
        }

        .submenu {
            margin-left: 20px;
            display: none;
        }

        .submenu.active {
            display: block;
        }

        main {
            flex: 1;
            padding: 20px;
            position: relative;
            overflow-y: auto; /* 메인 콘텐츠에서 스크롤이 가능하게 설정 */
        }

        .page-card {
            display: none;
            width: 100%;
            min-height: 100%; /* 페이지 카드의 높이를 부모 요소에 맞추어 설정 */
            padding: 20px;
            background-color: white;
            overflow-y: auto; /* 페이지 카드 내에서 스크롤 가능 */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        
        .page-card-home {
            display: none;
            width: 100%;
            min-height: 100%; /* 페이지 카드의 높이를 부모 요소에 맞추어 설정 */
            /* padding: 20px; */
            background-color: white;
            overflow-y: auto; /* 페이지 카드 내에서 스크롤 가능 */
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .page-card.active {
            display: block;
        }

        footer {
            background-color: #007bff;
            color: white;
            text-align: center;
            padding: 10px;
            margin-top: auto;
            width: 100%;
        }

        @media (max-width: 768px) {
            .sidebar-container {
                position: relative;
                width: 100%;
                height: auto;
                padding: 20px;
                overflow: hidden;
                transition: height 0.3s ease;
                z-index: 1000;
            }

            .sidebar-container.hidden {
                display: none;
            }

            .layout {
                flex-direction: column;
            }

            main {
                padding: 20px;
            }
        }
    </style>

    <script>
        // 페이지를 로드하고 카드를 동적으로 생성하는 함수
        function loadPage(pageId, pageUrl) {
            const mainContent = document.querySelector('main');
            const existingPage = document.getElementById(pageId);

            // 이미 로드된 페이지가 있으면 그 페이지를 활성화
            if (existingPage) {
                setActivePage(pageId);
                return;
            }

            // 새로운 page-card 생성
            fetch(pageUrl)
                .then(response => response.text())
                .then(data => {
                    const newPageCard = document.createElement('div');
                    newPageCard.id = pageId;
                    newPageCard.classList.add('page-card');
                    newPageCard.innerHTML = data;
                    mainContent.appendChild(newPageCard);
                    setActivePage(pageId);
                    adjustPaddingForHome(pageId); 
                })
                .catch(error => {
                    console.error('페이지 로드 중 오류 발생:', error);
                });
        }
        
     // padding을 home 페이지일 때만 0으로 설정하는 함수
        function adjustPaddingForHome(pageId) {
            const pageElement = document.getElementById(pageId);
            
            if (pageId === 'home') {
                pageElement.style.padding = '0px';  // home 페이지의 padding을 0으로 설정
            } else {
                pageElement.style.padding = '20px';  // 다른 페이지의 padding을 기본값으로 설정
            }
        }

        // 특정 페이지를 활성화하는 함수
        function setActivePage(pageId) {
            const pages = document.querySelectorAll('.page-card');
            pages.forEach(page => {
                page.classList.remove('active');
            });

            const targetPage = document.getElementById(pageId);
            if (targetPage) {
                targetPage.classList.add('active');
                localStorage.setItem('currentPage', pageId); // 현재 페이지 상태 저장
            }
        }

        // 서브메뉴 토글 함수
        function toggleSubmenu() {
            const submenu = document.getElementById('submenu');
            submenu.classList.toggle('active');
        }

        // 메뉴 접기/펼치기 버튼 동작
        function toggleSidebar() {
            const sidebar = document.querySelector('.sidebar-container');
            sidebar.classList.toggle('hidden');
            sidebar.classList.toggle('active');
        }

        document.addEventListener('DOMContentLoaded', function () {
            loadPage('home', 'home.html'); // 기본 페이지를 로드
        });
    </script>
</head>
<body>

    <header>
        <button onclick="toggleSidebar()">☰</button>
        <span>Webapp</span>
    </header>

    <div class="layout">
        <div class="sidebar-container hidden">
            <nav>
                <ul>
                    <li><a href="home.html" onclick="loadPage('home', 'home.html')">홈</a></li>
                    <li><a href="#" onclick="toggleSubmenu()">사용자 관리</a></li>
                    <ul id="submenu" class="submenu">
                        <li><a href="#" onclick="loadPage('userList', 'user-list.html')">사용자 목록</a></li>
                        <li><a href="userList.html" onclick="loadPage('userForm', 'user-form.html')">사용자 입력</a></li>
                    </ul>
                    <li><a href="#" onclick="loadPage('service', 'service.html')">서비스</a></li>
                    <li><a href="#" onclick="loadPage('contact', 'contact.html')">연락처</a></li>
                </ul>
            </nav>
        </div>

        <main>
            <!-- 페이지 카드들이 동적으로 여기에 생성됩니다 -->
        </main>
    </div>

    <footer>
        © 2024 내 웹사이트 - 모든 권리 보유
    </footer>

</body>
</html>