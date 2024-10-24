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
 * ConnectionPoolUtilTest
 * 
 * @author 강윤호 학번-202020814
 * @since 2024.10.22
 * @version 1.0
 */

import org.junit.jupiter.api.Test;
import kr.ac.kku.cs.wp.nangkongmu.support.sql.ConnectionPoolUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionPoolUtilTest {

    // JUnit의 @Test 어노테이션을 사용해 테스트 메소드를 정의
    @Test
    public void testConnection() {
        // try-with-resources 구문을 사용해 Connection 객체를 자동으로 관리
        try (Connection connection = ConnectionPoolUtil.getConnection()) {
            // 커넥션 풀 상태 출력
            // 현재 커넥션 풀에서의 총 커넥션 수, 활성 커넥션 수, 유휴 커넥션 수를 출력
            ConnectionPoolUtil.printConnectionPoolStatus();

            // SQL 실행
            // 'user' 테이블에서 모든 데이터를 가져오는 쿼리를 준비
            String query = "SELECT * FROM user";
            PreparedStatement stmt = connection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // 결과 출력
            // 쿼리 결과에서 각 행의 'name' 컬럼 값을 출력
            while (rs.next()) {
                System.out.println("User: " + rs.getString("name"));
            }

            // 커넥션 풀 상태 출력
            // SQL 쿼리 실행 후 커넥션 풀의 상태를 다시 확인하여 출력
            ConnectionPoolUtil.printConnectionPoolStatus();
        } catch (SQLException e) {
            // SQL 예외가 발생한 경우 예외 메시지를 출력
            e.printStackTrace();
        } finally {
            // 커넥션 풀 상태 출력
            // try 블록이 종료되기 전이나 예외가 발생한 후에도 커넥션 풀 상태를 확인하여 출력
            ConnectionPoolUtil.printConnectionPoolStatus();
            // 애플리케이션 종료 시 커넥션 풀을 종료하여 리소스를 정리
            ConnectionPoolUtil.closeDataSource();
        }
    }
}

