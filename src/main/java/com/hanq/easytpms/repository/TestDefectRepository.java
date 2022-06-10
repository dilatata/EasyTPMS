package com.hanq.easytpms.repository;

import com.hanq.easytpms.vo.TestDefectVO;
import com.hanq.easytpms.vo.TestExecutionVO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
public interface TestDefectRepository {
    // 시나리오 결함 생성
    @Transaction
    @SqlUpdate("INSERT INTO execution(execution_id,project_name,test_type,scenario_type, biz_category, biz_detail, version, team_name, scenario_category, test_scenario_id, test_scenario_name, screen_id, screen_name, test_case_id, test_case_name, exec_due_date, tester, test_target_type, test_target_name, confirm_contents, test_data, build_name, build_version, note, execution_date, exec_status, exec_result) " +
            "VALUES(:execution_id, :project_name,:test_type,:scenario_type, :biz_category, :biz_detail, :version, :team_name, :scenario_category, :test_scenario_id, :test_scenario_name, :screen_id, :screen_name, :test_case_id, :test_case_name, :exec_due_date, :tester, test_target_type, :test_target_name, :confirm_contents, :test_data, :build_name, :build_version, :note, :execution_date, :exec_status, :exec_result) ")
    @GetGeneratedKeys
    void insertTestDefect(@BindBean TestDefectVO testExecutionVO);

    // 시나리오 결함 개별 삭제
    @Transaction
    @SqlUpdate("DELETE FROM execution WHERE executionId = :id")
    void deleteTestDefect(@Bind("execution_id") BigInteger executionId);


    // 연관 결함 리스트 조회
    @SqlQuery("SELECT * FROM execution WHERE execution_id = :id")
    @RegisterBeanMapper(TestDefectVO.class)
    List<TestDefectVO> findTestDefectListBYExecutionId(@Bind("execution_id") BigInteger id);

    // 결함 리스트 조회
    @SqlQuery("SELECT * FROM execution WHERE project_name = :p_name")
    @RegisterBeanMapper(TestDefectVO.class)
    List<TestDefectVO> findDefectListByProjectName(@Bind("project_name") String p_name);

    // 결함 세부조건 작성(수정)
    @Transaction
    @SqlUpdate("UPDATE execution SET [열] = '변경할값' WHERE executionId = :id")
    TestDefectVO editTestDefect(@BindBean TestDefectVO request);

    // 결함조치 결과 작성
    // @Bind("defect_status") String defectStatus, 조치완료료    @Transaction
    @SqlUpdate("UPDATE defect SET defect_status = :defectStatus ,defect_date = :defectDate, defect_action_yn = :defectActionYn, defect_action_contents = :defectActionContents WHERE defect_id = :id")
    void updateTestDefect(@Bind("defect_id") BigInteger id, @Bind("defect_date") Date defectDate, @Bind("defect_action_yn") String defectActionYn, @Bind("defect_action_contents") String defectActionContents);

    // 결함조치 확인 작성

    // 결함 첨부파일 생성

}
