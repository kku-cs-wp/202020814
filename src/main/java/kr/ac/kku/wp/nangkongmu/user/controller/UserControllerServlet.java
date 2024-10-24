/*
 * 저작권 (C) 2024 202020814 강윤호 모든 권리 보유.
 *
 * 이 소프트웨어는 고급웹프로그래밍 중간고사 코딩 시험 제출용입니다.
 * 이 소프트웨어는 개인적, 교육적 또는 비상업적 목적으로 자유롭게 사용할 수 있습니다.
 * 상업적 사용을 위해서는 타인의 권리를 침해하지 않도록 주의해야 합니다.
 *
 * 연락처: gadinson2@naver.com
 */
package kr.ac.kku.wp.nangkongmu.user.controller;
/**
 * UserControllerServlet
 * 
 * @author 강윤호 학번-202020814
 * @since 2024.10.21
 * @version 1.0
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

// UserControllerServlet은 HttpServlet을 상속
@WebServlet("/user/*")
public class UserControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(UserControllerServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getPathInfo();

        if (action == null || action.equals("/")) {
            // 기본 실행 화면으로 index.jsp 포워딩
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } else if (action.equals("/logout")) {
            // 로그아웃 처리
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
                logger.info("User has logged out at " + java.time.LocalDateTime.now());
            }
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        } else {
            // 로그인 페이지로 포워딩
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // 로그인 인증 처리
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 예시: 간단한 인증 로직 (실제로는 데이터베이스 조회)
        if ("kku_1001".equals(username) && "password".equals(password)) {
            // 인증 성공
            HttpSession session = request.getSession();
            session.setAttribute("user", username);
            logger.info(username + " has logged in at " + java.time.LocalDateTime.now());
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            // 인증 실패
            logger.info("Failed to log in at " + java.time.LocalDateTime.now() + " for user: " + username);
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
