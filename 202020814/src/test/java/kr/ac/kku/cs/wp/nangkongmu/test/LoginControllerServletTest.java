package kr.ac.kku.cs.wp.nangkongmu.test;

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