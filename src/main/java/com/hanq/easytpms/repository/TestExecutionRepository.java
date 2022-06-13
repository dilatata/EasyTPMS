package com.hanq.easytpms.repository;

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
public interface TestExecutionRepository {

    // 단일 execution 생성
    @Transaction
    @SqlUpdate("INSERT INTO execution(project_name,test_type,scenario_type, biz_category, biz_detail, version, team_name, scenario_category, test_scenario_id, test_scenario_name, screen_id, screen_name, test_case_id, test_case_name, exec_due_date, tester, test_target_type, test_target_name, confirm_contents, test_data, build_name, build_version, note, execution_date, exec_status, exec_result) " +
            "VALUES( :projectName,:testType,:scenarioType, :bizCategory, :bizDetail, :version, :teamName, :scenarioCategory, :testScenarioId, :testScenarioName, :screenId, :screenName, :testCaseId, :testCaseName, :execDueDate, :tester, :testTargetType, :testTargetName, :confirmContents, :testData, :buildName, :buildVersion, :note, :executionDate, :execStatus, :execResult) ")
    @GetGeneratedKeys
    void insertTestExecution(@BindBean TestExecutionVO testExecutionVO);

    // 전체(projectName 선택) 조회
    @SqlQuery("SELECT * FROM execution WHERE project_name = :p_name")
    @RegisterBeanMapper(TestExecutionVO.class)
    List<TestExecutionVO> getTestExecutionInfoByProjectName(@Bind("project_name") String p_name);

    // execution 단독 조회
    @SqlQuery("SELECT * FROM execution WHERE execution_id = :id")
    @RegisterBeanMapper(TestExecutionVO.class)
    TestExecutionVO getTestExecutionInfoByExecutionId(@Bind("execution_id") BigInteger id);

    // execution 단독 수정
    @Transaction
    @SqlUpdate("UPDATE execution SET [열] = '변경할값' WHERE executionId = :id")
    void editTestExecution(@BindBean TestExecutionVO request);

    // execution 단독 삭제
    @Transaction
    @SqlUpdate("DELETE FROM execution WHERE executionId = :id")
    void deleteTestExecution(@Bind("execution_id") BigInteger executionId);

    // execution 결과입력
    @Transaction
    @SqlUpdate("UPDATE execution SET execution_id = :executionId, execution_date = :executionDate, exec_status = :execStatus, exec_result = :execResult WHERE executionId = :id")
    void updateTestExecution(@Bind("execution_id") BigInteger executionId, @Bind("execution_date") Date executionDate, @Bind("exec_status") String execStatus, @Bind("exec_result") String execResult);

}
