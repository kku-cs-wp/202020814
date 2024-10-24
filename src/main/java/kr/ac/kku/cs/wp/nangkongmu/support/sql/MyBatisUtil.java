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
 * MyBatisUtil
 * 
 * @author 강윤호 학번-202020814
 * @since 2024.10.19
 * @version 1.0
 */
import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {

    // SqlSessionFactory 생성
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // MyBatis 설정 파일 읽기
            String resource = "mybatis-config.xml";
            Reader reader = Resources.getResourceAsReader(resource);

            // SqlSessionFactoryBuilder를 사용해 SqlSessionFactory 생성
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // SqlSessionFactory 반환
    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
