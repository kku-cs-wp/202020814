/*
 * 저작권 (C) 2024 202020814 강윤호 모든 권리 보유.
 *
 * 이 소프트웨어는 고급웹프로그래밍 중간고사 코딩 시험 제출용입니다.
 * 이 소프트웨어는 개인적, 교육적 또는 비상업적 목적으로 자유롭게 사용할 수 있습니다.
 * 상업적 사용을 위해서는 타인의 권리를 침해하지 않도록 주의해야 합니다.
 *
 * 연락처: gadinson2@naver.com
 */
package kr.ac.kku.cs.wp.nangkongmu.support.sql;
/**
 * ConnectionUtil
 * 
 * @author 강윤호 학번-202020814
 * @since 2024.10.19
 * @version 1.0
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    // mysql 연결정보 url (database 정보 포함), 사용자, 비밀번호
    private static final String URL = "jdbc:mysql://localhost:3306/webapp_nangkongmu?serverTimezone=UTC";
    private static final String USER = "nangkongmu";
    private static final String PASSWORD = "202020814";

    // static 영역에서 JDBC 드라이버 로딩
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Connection 반환 Method
    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        connection.setAutoCommit(false);
        return connection;
    }
}
