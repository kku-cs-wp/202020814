/*
 * 저작권 (C) 2024 202020814 강윤호 모든 권리 보유.
 *
 * 이 소프트웨어는 고급웹프로그래밍 중간고사 코딩 시험 제출용입니다.
 * 이 소프트웨어는 개인적, 교육적 또는 비상업적 목적으로 자유롭게 사용할 수 있습니다.
 * 상업적 사용을 위해서는 타인의 권리를 침해하지 않도록 주의해야 합니다.
 *
 * 연락처: gadinson2@naver.com
 */
package kr.ac.kku.cs.wp.nangkongmu.user.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kku.cs.wp.nangkongmu.util.ConnectionPoolUtil;

public class UserDAOJdbcImpl implements UserDAO {

    private static final Logger logger = LogManager.getLogger(UserDAOJdbcImpl.class);

    // SQL 쿼리문 필드
    private final String querySelectById = "SELECT * FROM user WHERE id = ?";
    private final String querySelectUserRole = "SELECT * FROM user_role WHERE user_id = ?";
    private final String querySelectList = "SELECT * FROM user";
    private final String queryCompareCredentials = "SELECT * FROM user WHERE id = ? AND password = ?";
    private final String queryInsertUser = "INSERT INTO user (id, name, email, password, status) VALUES (?, ?, ?, ?, ?)";
    private final String queryUpdateUser = "UPDATE user SET name = ?, email = ?, password = ?, status = ? WHERE id = ?";
    private final String queryDeleteUser = "DELETE FROM user WHERE id = ?";

    // UserDAO 인터페이스의 메서드 구현
    @Override
    public User getUserById(String userId) {
        User user = new User();
        try (Connection conn = ConnectionPoolUtil.getConnection()) {
            PreparedStatement psmt = conn.prepareStatement(querySelectById);
            PreparedStatement sPstmt = conn.prepareStatement(querySelectUserRole);
            psmt.setString(1, userId);
            ResultSet rs = psmt.executeQuery();

            if (rs.next()) {
                user.setId(rs.getString("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setStatus(rs.getString("status"));

                sPstmt.setString(1, user.getId());
                ResultSet rsur = sPstmt.executeQuery();
                List<UserRole> urList = new ArrayList<>();
                while (rsur.next()) {
                    Role role = new Role();
                    role.setId(rsur.getString("role_id"));
                    role.setRole(rsur.getString("role"));

                    UserRoleId uri = new UserRoleId();
                    uri.setRoleId(role.getId());
                    uri.setUserId(user.getId());

                    UserRole ur = new UserRole();
                    ur.setUser(user);
                    ur.setRole(role);
                    ur.setId(uri);
                    urList.add(ur);
                }
                rsur.close();
                user.setUserRoles(urList);
            }

            rs.close();
            psmt.close();
            sPstmt.close();
        } catch (SQLException e) {
            logger.error("Error fetching user by ID: " + userId, e);
        }

        return user;
    }

    @Override
    public User getUser(User user) {
        return getUserById(user.getId());
    }

    @Override
    public User updateUser(User user) {
        try (Connection conn = ConnectionPoolUtil.getConnection()) {
            PreparedStatement psmt = conn.prepareStatement(queryUpdateUser);
            psmt.setString(1, user.getName());
            psmt.setString(2, user.getEmail());
            psmt.setString(3, user.getPassword());
            psmt.setString(4, user.getStatus());
            psmt.setString(5, user.getId());
            int rowsUpdated = psmt.executeUpdate();
            psmt.close();

            if (rowsUpdated > 0) {
                logger.info("User updated successfully: " + user);
                return user;
            } else {
                logger.warn("User update failed: " + user);
                return null;
            }
        } catch (SQLException e) {
            logger.error("Error updating user: " + user, e);
            return null;
        }
    }

    @Override
    public void deleteUser(User user) {
        try (Connection conn = ConnectionPoolUtil.getConnection()) {
            PreparedStatement psmt = conn.prepareStatement(queryDeleteUser);
            psmt.setString(1, user.getId());
            int rowsDeleted = psmt.executeUpdate();
            psmt.close();

            if (rowsDeleted > 0) {
                logger.info("User deleted successfully: " + user);
            } else {
                logger.warn("User deletion failed: " + user);
            }
        } catch (SQLException e) {
            logger.error("Error deleting user: " + user, e);
        }
    }

    @Override
    public User createUser(User user) {
        try (Connection conn = ConnectionPoolUtil.getConnection()) {
            PreparedStatement psmt = conn.prepareStatement(queryInsertUser);
            psmt.setString(1, user.getId());
            psmt.setString(2, user.getName());
            psmt.setString(3, user.getEmail());
            psmt.setString(4, user.getPassword());
            psmt.setString(5, user.getStatus());
            int rowsInserted = psmt.executeUpdate();
            psmt.close();

            if (rowsInserted > 0) {
                logger.info("User created successfully: " + user);
                return user;
            } else {
                logger.warn("User creation failed: " + user);
                return null;
            }
        } catch (SQLException e) {
            logger.error("Error creating user: " + user, e);
            return null;
        }
    }

    @Override
    public List<User> getUsers(User user) {
        List<User> userList = new ArrayList<>();
        try (Connection conn = ConnectionPoolUtil.getConnection()) {
            PreparedStatement psmt = conn.prepareStatement(querySelectList);
            ResultSet rs = psmt.executeQuery();

            while (rs.next()) {
                User fetchedUser = new User();
                fetchedUser.setId(rs.getString("id"));
                fetchedUser.setName(rs.getString("name"));
                fetchedUser.setEmail(rs.getString("email"));
                fetchedUser.setPassword(rs.getString("password"));
                fetchedUser.setStatus(rs.getString("status"));
                userList.add(fetchedUser);
            }

            rs.close();
            psmt.close();
        } catch (SQLException e) {
            logger.error("Error fetching user list", e);
        }
        return userList;
    }

    @Override
    public boolean compareUserCredentials(String userId, String password) {
        boolean isValid = false;
        try (Connection conn = ConnectionPoolUtil.getConnection()) {
            PreparedStatement psmt = conn.prepareStatement(queryCompareCredentials);
            psmt.setString(1, userId);
            psmt.setString(2, password);
            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                isValid = true;
            }
            rs.close();
            psmt.close();
        } catch (SQLException e) {
            logger.error("Error comparing user credentials for user ID: " + userId, e);
        }
        return isValid;
    }
}

