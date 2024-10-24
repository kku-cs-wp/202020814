/*
 * 저작권 (C) 2024 202020814 강윤호 모든 권리 보유.
 *
 * 이 소프트웨어는 고급웹프로그래밍 중간고사 코딩 시험 제출용입니다.
 * 이 소프트웨어는 개인적, 교육적 또는 비상업적 목적으로 자유롭게 사용할 수 있습니다.
 * 상업적 사용을 위해서는 타인의 권리를 침해하지 않도록 주의해야 합니다.
 *
 * 연락처: gadinson2@naver.com
 */
package kr.ac.kku.cs.wp.nangkongmu.util;
/**
 * ConnectionPlloUtil
 * 
 * @author 강윤호 학번-202020814
 * @since 2024.10.18
 * @version 1.0
 */
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionPoolUtil {
    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/your_database_name");
        dataSource.setUsername("your_username");
        dataSource.setPassword("your_password");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setInitialSize(10); // 초기 커넥션 수
        dataSource.setMaxTotal(50);    // 최대 커넥션 수
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
