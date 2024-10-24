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
 * ConnnectionUtilTest
 * 
 * @author 강윤호 학번-202020814
 * @since 2024.10.23
 * @version 1.0
 */
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import kr.ac.kku.cs.wp.nangkongmu.support.sql.ConnectionUtil;


public class ConnectionUtilTest {

    @Test
    void testConnection() throws SQLException {
        try (Connection conn = ConnectionUtil.getConnection()) {
            conn.commit();
        } catch (Exception e) {
            throw e;
        }
    }
}
