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
 * UserDAOImplTest
 * 
 * @author 강윤호 학번-202020814
 * @since 2024.10.24
 * @version 1.0
 */
import kr.ac.kku.cs.wp.nangkongmu.user.entity.UserDAO;
import kr.ac.kku.cs.wp.nangkongmu.user.entity.UserDAOJdbcImpl; // 올바른 클래스를 import합니다.
import kr.ac.kku.cs.wp.nangkongmu.user.entity.User;
import kr.ac.kku.cs.wp.nangkongmu.user.entity.UserRole;
import kr.ac.kku.cs.wp.nangkongmu.user.entity.Role;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UserDAOImplTest {

    private static final Logger logger = LogManager.getLogger(UserDAOImplTest.class);
    private UserDAO userDao;

    @BeforeEach
    public void setUp() {
        userDao = new UserDAOJdbcImpl(); // 클래스 이름을 UserDAOJdbcImpl로 수정합니다.
    }

    @Test
    public void testGetUserById() {
        String id = "kku_1001";
        User user = userDao.getUserById(id);
        

        assertNotNull(user, "User should not be null");


        List<UserRole> urList = user.getUserRoles();
        assertNotNull(urList, "User roles should not be null");

        // 각 UserRole에 대해 id, name, role 로그 남기기
        for (UserRole userRole : urList) {
            User urUser = userRole.getUser();
            Role role = userRole.getRole();
            logger.debug("user id: {}, user name: {}, role name: {}", 
                urUser.getId(), urUser.getName(), role.getRole());

            assertNotNull(urUser.getId(), "User ID should not be null");
            assertNotNull(urUser.getName(), "User name should not be null");
            assertNotNull(role.getRole(), "Role name should not be null");
        }
    }

    @Test
    public void testGetUsers() {
        // 모든 유저 리스트를 가져오기
        List<User> users = userDao.getUsers(null);
        

        assertNotNull(users, "Users list should not be null");
        assertTrue(users.size() > 0, "Users list should not be empty");


        for (User user : users) {
            List<UserRole> urList = user.getUserRoles();
            assertNotNull(urList, "User roles should not be null");

            for (UserRole userRole : urList) {
                User urUser = userRole.getUser();
                Role role = userRole.getRole();
                logger.debug("user id: {}, user name: {}, role name: {}",
                    urUser.getId(), urUser.getName(), role.getRole());


                assertNotNull(urUser.getId(), "User ID should not be null");
                assertNotNull(urUser.getName(), "User name should not be null");
                assertNotNull(role.getRole(), "Role name should not be null");
            }
        }
    }
}

