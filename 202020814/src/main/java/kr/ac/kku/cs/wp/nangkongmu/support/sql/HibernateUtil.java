package kr.ac.kku.cs.wp.nangkongmu.support.sql;

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
