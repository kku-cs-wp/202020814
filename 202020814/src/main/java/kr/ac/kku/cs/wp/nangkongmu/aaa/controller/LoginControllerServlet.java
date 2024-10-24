/*
 * 저작권 (C) 2024 202020814 강윤호 모든 권리 보유.
 *
 * 이 소프트웨어는 고급웹프로그래밍 중간고사 코딩 시험 제출용입니다.
 * 이 소프트웨어는 개인적, 교육적 또는 비상업적 목적으로 자유롭게 사용할 수 있습니다.
 * 상업적 사용을 위해서는 타인의 권리를 침해하지 않도록 주의해야 합니다.
 *
 * 연락처: gadinson2@naver.com
 */
package kr.ac.kku.cs.wp.nangkongmu.aaa.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kku.cs.wp.nangkongmu.user.entity.Account;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

// LoginControllerServlet은 HttpServlet을 상속
@WebServlet({"/login/*", "/logout"})
public class LoginControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(LoginControllerServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            // 로그인 페이지로 포워딩
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action != null && action.equals("/logout")) {
            // POST 방식 로그아웃 처리
            HttpSession session = request.getSession(false);
            if (session != null) {
                Account account = (Account) session.getAttribute("account");
                if (account != null) {
                    String username = account.getUsername();
                    logger.info(username + " has logged out at " 
                        + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
                }
                session.invalidate();
            }
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else if (action != null && action.equals("/login")) {
            // 로그인 인증 처리
            doLogin(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 예시: 간단한 인증 로직 (실제로는 데이터베이스 조회)
        if ("kku_1001".equals(username) && "password".equals(password)) {
            // 인증 성공
            HttpSession session = request.getSession();
            Account account = new Account(username, "일반 사용자"); // 역할은 임의로 설정
            session.setAttribute("account", account);
            logger.info("안중근(" + username + ") has logged in at " 
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            // 인증 실패
            String clientIp = request.getRemoteAddr();
            logger.info(username + "[" + clientIp + "] failed to log in at " 
                + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}

