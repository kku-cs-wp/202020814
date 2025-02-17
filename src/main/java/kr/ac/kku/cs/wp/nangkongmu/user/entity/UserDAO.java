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
/**
 * UserDAO
 * 
 * @author 강윤호 학번-202020814
 * @since 2024.10.16
 * @version 1.0
 */

import java.util.List;

public interface UserDAO {
    User getUserById(String userId);
    User getUser(User user);
    User updateUser(User user);
    void deleteUser(User user);
    User createUser(User user);
    List<User> getUsers(User user);
    
    // 사용자 ID와 비밀번호를 비교하는 메서드 추가
    boolean compareUserCredentials(String userId, String password);
}