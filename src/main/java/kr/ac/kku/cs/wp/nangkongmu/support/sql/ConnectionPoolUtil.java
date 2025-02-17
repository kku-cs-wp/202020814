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
 * ConnectionPoolUtil
 * 
 * @author 강윤호 학번-202020814
 * @since 2024.10.20
 * @version 1.0
 */
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolUtil {

    private static HikariDataSource dataSource;
    private static final Logger logger = LogManager.getLogger(ConnectionPoolUtil.class);


    static {
        try {
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3306/webapp_nangkongmu?serverTimezone=UTC");
            config.setUsername("nangkongmu");
            config.setPassword("nangkongmu");
            config.setAutoCommit(false); 
            config.setMinimumIdle(3); // 최소 3개의 유휴 커넥션 유지
            config.setMaximumPoolSize(10); // 최대 커넥션 수 설정
            config.setConnectionTimeout(30000); // 커넥션 대기 시간
            config.setIdleTimeout(600000); // 10분 후 유휴 커넥션 종료
            config.setMaxLifetime(1800000); // 30분 후 커넥션 재생성

            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get the DataSource instance
    public static HikariDataSource getDataSource() {
        return dataSource;
    }

    // 커넥션 풀에서 커넥션 얻기
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    // 커넥션 풀 상태 확인 (모니터링)
    public static void printConnectionPoolStatus() {
        logger.debug("Total connections: " + dataSource.getHikariPoolMXBean().getTotalConnections());
        logger.debug("Active connections: " + dataSource.getHikariPoolMXBean().getActiveConnections());
        logger.debug("Idle connections: " + dataSource.getHikariPoolMXBean().getIdleConnections());
    }

    // 데이터 소스 종료
    public static void closeDataSource() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}
