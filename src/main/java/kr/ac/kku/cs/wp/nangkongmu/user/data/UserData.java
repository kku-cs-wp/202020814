/*
 * 저작권 (C) 2024 202020814 강윤호 모든 권리 보유.
 *
 * 이 소프트웨어는 고급웹프로그래밍 중간고사 코딩 시험 제출용입니다.
 * 이 소프트웨어는 개인적, 교육적 또는 비상업적 목적으로 자유롭게 사용할 수 있습니다.
 * 상업적 사용을 위해서는 타인의 권리를 침해하지 않도록 주의해야 합니다.
 *
 * 연락처: gadinson2@naver.com
 */
package kr.ac.kku.cs.wp.nangkongmu.user.data;
/**
 * UserData
 * 
 * @author 강윤호 학번-202020814
 * @since 2024.10.17
 * @version 1.0
 */
import kr.ac.kku.cs.wp.nangkongmu.user.entity.User;
import java.util.HashMap;
import java.util.Map;

public class UserData {

    private static UserData instance;
    private Map<String, User> users;

    // 인스턴스 생성을 방지하기 위한 private 생성자
    private UserData() {
        this.users = new HashMap<>();
        initData();
    }

    // 기본 사용자 데이터를 초기화하는 메소드 (데모용으로 기본 사용자 생성)
    private void initData() {                                                         
        // 샘플 데이터 초기화 (필요한 경우 실제 데이터 가져오기 로직으로 교체)
        User sampleUser = new User();
        sampleUser.setId("kku_1001");
        sampleUser.setName("Kim Cheolsoo");
        sampleUser.setEmail("cheolsoo@kku.ac.kr");
        sampleUser.setPassword("password123");
        sampleUser.setStatus("active");
        users.put(sampleUser.getId(), sampleUser);
    }

    // 싱글톤 인스턴스를 가져오는 메소드
    public static synchronized UserData getInstance() {
        if (instance == null) {
            instance = new UserData();
        }
        return instance;
    }

    // 사용자 ID로 사용자 정보를 가져오는 메소드
    public User getUserById(String userId) {
        return users.get(userId);
    }

    // 사용자를 추가하거나 업데이트하는 메소드
    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    // 사용자를 삭제하는 메소드
    public void removeUser(String userId) {
        users.remove(userId);
    }

    // 모든 사용자 정보를 가져오는 메소드
    public Map<String, User> getAllUsers() {
        return users;
    }
}
