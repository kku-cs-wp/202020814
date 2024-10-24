/*
 * 저작권 (C) 2024 202020814 강윤호 모든 권리 보유.
 *
 * 이 소프트웨어는 고급웹프로그래밍 중간고사 코딩 시험 제출용입니다.
 * 이 소프트웨어는 개인적, 교육적 또는 비상업적 목적으로 자유롭게 사용할 수 있습니다.
 * 상업적 사용을 위해서는 타인의 권리를 침해하지 않도록 주의해야 합니다.
 *
 * 연락처: gadinson2@naver.com
 */
package kr.ac.kku.cs.wp.nangkongmu.service;


import kr.ac.kku.cs.wp.nangkongmu.user.entity.UserDAO;
import kr.ac.kku.cs.wp.nangkongmu.user.entity.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    
    private UserDAO userDAO;

    // 생성자를 통해 UserDAO를 주입받음 (의존성 주입)
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUsers() {
        // DAO를 이용해 모든 사용자 정보를 가져옴
        return userDAO.getUsers(null);
    }

    @Override
    public User getUserById(String id) {
        // DAO를 이용해 특정 사용자 ID로 사용자 정보를 가져옴
        return userDAO.getUserById(id);
    }
}
