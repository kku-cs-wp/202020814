/*
 * 저작권 (C) 2024 202020814 강윤호 모든 권리 보유.
 *
 * 이 소프트웨어는 고급웹프로그래밍 중간고사 코딩 시험 제출용입니다.
 * 이 소프트웨어는 개인적, 교육적 또는 비상업적 목적으로 자유롭게 사용할 수 있습니다.
 * 상업적 사용을 위해서는 타인의 권리를 침해하지 않도록 주의해야 합니다.
 *
 * 연락처: gadinson2@naver.com
 */
package kr.ac.kku.cs.wp.nangkongmu.test;
/**
 * LoginControllerServletTest
 * 
 * @author 강윤호 학번-202020814
 * @since 2024.10.23
 * @version 1.0
 */
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import kr.ac.kku.cs.wp.nangkongmu.aaa.controller.LoginControllerServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginControllerServletTest {

    private LoginControllerServlet loginControllerServlet;

    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    private StringWriter responseWriter;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        loginControllerServlet = new LoginControllerServlet();
        responseWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));
    }

    @Test
    public void testLoginSuccess() throws ServletException, IOException {
        // Arrange
        when(request.getPathInfo()).thenReturn("/login");
        when(request.getParameter("username")).thenReturn("kku_1001");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getSession()).thenReturn(session);

        // Act
        loginControllerServlet.doPost(request, response);

        // Assert
        verify(session).setAttribute(eq("account"), any());
        verify(response).sendRedirect(contains("/index.jsp"));
        String logMessage = responseWriter.toString();
        assertTrue(logMessage.contains("has logged in"));
    }

    @Test
    public void testLoginFailure() throws ServletException, IOException {
        // Arrange
        when(request.getPathInfo()).thenReturn("/login");
        when(request.getParameter("username")).thenReturn("kku_1001");
        when(request.getParameter("password")).thenReturn("wrong_password");

        // Act
        loginControllerServlet.doPost(request, response);

        // Assert
        verify(request).setAttribute(eq("errorMessage"), eq("Invalid username or password."));
        verify(request).getRequestDispatcher(contains("/login.jsp"));
        String logMessage = responseWriter.toString();
        assertTrue(logMessage.contains("failed to log in"));
    }

    @Test
    public void testLogout() throws ServletException, IOException {
        // Arrange
        when(request.getPathInfo()).thenReturn("/logout");
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("account")).thenReturn("kku_1001");

        // Act
        loginControllerServlet.doPost(request, response);

        // Assert
        verify(session).invalidate();
        verify(response).sendRedirect(contains("/login.jsp"));
        String logMessage = responseWriter.toString();
        assertTrue(logMessage.contains("has logged out"));
    }
}