package kr.ac.kku.cs.wp.nangkongmu.test;

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
