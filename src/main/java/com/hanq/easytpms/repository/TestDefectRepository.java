package com.hanq.easytpms.repository;

import com.hanq.easytpms.vo.ResponseTestDefectVO;
import com.hanq.easytpms.vo.TestDefectVO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.OutParameter;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TestDefectRepository {
    // 시나리오 결함 생성
    @Transaction
    @SqlUpdate("INSERT INTO defect(execution_id,project_name,test_type,scenario_type, biz_category, biz_detail, version, team_name, scenario_category, test_scenario_id, test_scenario_name, screen_id, screen_name, test_case_id, test_case_name, exec_due_date, tester, test_target_type, test_target_name, confirm_contents, test_data, build_name, build_version, note, execution_date, exec_status, exec_result) " +
            "VALUES(:execution_id, :project_name,:test_type,:scenario_type, :biz_category, :biz_detail, :version, :team_name, :scenario_category, :test_scenario_id, :test_scenario_name, :screen_id, :screen_name, :test_case_id, :test_case_name, :exec_due_date, :tester, test_target_type, :test_target_name, :confirm_contents, :test_data, :build_name, :build_version, :note, :execution_date, :exec_status, :exec_result) ")
    @GetGeneratedKeys
    void insertTestDefect(@BindBean TestDefectVO testExecutionVO);

    // 시나리오 결함 개별 삭제
    @Transaction
    @SqlUpdate("DELETE FROM defect WHERE defectId = :id")
    @OutParameter(name = "defectId",  sqlType = java.sql.Types.BIGINT)
    void deleteTestDefect(@Bind("defect_id") Long defectId);


    // 연관 결함 리스트 조회
    @SqlQuery("SELECT * FROM defect WHERE execution_id = :id")
    @RegisterBeanMapper(TestDefectVO.class)
    @OutParameter(name = "defectId",  sqlType = java.sql.Types.BIGINT)
    @OutParameter(name = "executionId",  sqlType = java.sql.Types.BIGINT)
    List<TestDefectVO> findTestDefectListByExecutionId(@Bind("execution_id") Long id);

    // 결함 상세 조회
    @SqlQuery("SELECT * FROM defect WHERE defect_id = :defectId")
    @RegisterBeanMapper(TestDefectVO.class)
    @OutParameter(name = "defectId",  sqlType = java.sql.Types.BIGINT)
    @OutParameter(name = "executionId",  sqlType = java.sql.Types.BIGINT)
    TestDefectVO findTestDefectInfoByDefectId(Long id);


    // 결함 리스트 조회
    @SqlQuery("SELECT * FROM defect WHERE project_name = :projectName")
    @RegisterBeanMapper(TestDefectVO.class)
    @OutParameter(name = "defectId",  sqlType = java.sql.Types.BIGINT)
    @OutParameter(name = "executionId",  sqlType = java.sql.Types.BIGINT)
    List<TestDefectVO> findDefectListByProjectName(@Bind("project_name") String projectName);

    // 결함 전체 조회
    @SqlQuery("SELECT * FROM defect")
    @RegisterBeanMapper(TestDefectVO.class)
    List<ResponseTestDefectVO> findAllDefectList();

    // 결함 세부조건 작성(수정)
    @Transaction
    @SqlUpdate("UPDATE defect SET [열] = '변경할값' WHERE executionId = :id")
    void editTestDefect(@BindBean TestDefectVO request);

    // 결함조치 결과 작성
    @Transaction
    @SqlUpdate("UPDATE defect SET defect_status = :defectStatus ,defect_date = :defectDate, defect_action_yn = :defectActionYn, defect_action_contents = :defectActionContents WHERE defect_id = :id")
    @OutParameter(name = "defectId",  sqlType = java.sql.Types.BIGINT)
    @OutParameter(name = "executionId",  sqlType = java.sql.Types.BIGINT)
    void updateTestDefectY(@Bind("defect_id") Long id, @Bind("defect_status") String defectStatus, @Bind("defect_date") Date defectDate, @Bind("defect_action_yn") String defectActionYn, @Bind("defect_action_contents") String defectActionContents);

    // 결함조치 확인 작성
    @Transaction
    @SqlUpdate("UPDATE defect SET defect_status = :defectStatus , defect_check = :defectCheck, defect_check_date = :defectCheckDate WHERE defect_id = :id")
    @OutParameter(name = "defectId",  sqlType = java.sql.Types.BIGINT)
    @OutParameter(name = "executionId",  sqlType = java.sql.Types.BIGINT)
    void updateTestDefectCheckY(@Bind("defect_id") Long id,
                                @Bind("defect_status") String defectStatus,
                                @Bind("defect_check") String defectCheck,
                                @Bind("defect_check_date") Date defectCheckDate);

    @Transaction
    @SqlUpdate("UPDATE defect SET defect_status = '재결함', defect_date = NULL WHERE defect_id = :id")
    @OutParameter(name = "defectId",  sqlType = java.sql.Types.BIGINT)
    @OutParameter(name = "executionId",  sqlType = java.sql.Types.BIGINT)
    void updateTestDefectCheckN(@Bind("defect_id") Long id, @Bind("defectStatus") String defectStatus, @Bind("defectActionYn") String defectActionYn);


    // 결함 첨부파일 생성
}
