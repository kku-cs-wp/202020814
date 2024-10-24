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
 * HibernateUtil
 * 
 * @author 강윤호 학번-202020814
 * @since 2024.10.19
 * @version 1.0
 */
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    // SessionFactory를 빌드하는 메서드
    private static SessionFactory buildSessionFactory() {
        try {
            // Configuration 객체를 생성하고 설정 파일을 로드하여 SessionFactory 빌드
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // 초기화 오류 발생 시 예외 처리
            throw new ExceptionInInitializerError(ex);
        }
    }

    // SessionFactory 반환 메서드
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // SessionFactory 종료 메서드
    public static void shutdown() {
        getSessionFactory().close();
    }
}
