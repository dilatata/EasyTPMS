package com.hanq.easytpms.repository;

import com.hanq.easytpms.vo.TestExecutionVO;
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
public interface TestExecutionRepository {

    // 단일 execution 생성 O
    @Transaction
    @SqlUpdate("INSERT INTO execution(project_name,test_type,scenario_type, biz_category, biz_detail, version, team_name, scenario_category, test_scenario_id, test_scenario_name, screen_id, screen_name, test_case_id, test_case_name, exec_due_date, tester, test_target_type, test_target_name, confirm_contents, test_data, build_name, build_version, note, execution_date, exec_status, exec_result) " +
            "VALUES( :projectName,:testType,:scenarioType, :bizCategory, :bizDetail, :version, :teamName, :scenarioCategory, :testScenarioId, :testScenarioName, :screenId, :screenName, :testCaseId, :testCaseName, :execDueDate, :tester, :testTargetType, :testTargetName, :confirmContents, :testData, :buildName, :buildVersion, :note, :executionDate, :execStatus, :execResult) ")
    @GetGeneratedKeys
    void insertTestExecution(@BindBean TestExecutionVO testExecutionVO);

    // 전체(projectName 선택) 조회 O
    @SqlQuery("SELECT * FROM execution WHERE project_name = :p_name")
    @RegisterBeanMapper(TestExecutionVO.class)
    List<TestExecutionVO> getTestExecutionInfoByProjectName(@Bind("project_name") String projectName);

    @SqlQuery("SELECT * FROM execution")
    @RegisterBeanMapper(TestExecutionVO.class)
    List<TestExecutionVO> getTestExecutionList();

    // execution 단독 조회 O
    @SqlQuery("SELECT * FROM execution WHERE execution_id = :id")
    @RegisterBeanMapper(TestExecutionVO.class)
    @OutParameter(name = "executionId",  sqlType = java.sql.Types.BIGINT)
    TestExecutionVO getTestExecutionInfoByExecutionId(@Bind("execution_id") Long id);

    // execution 단독 수정 O
    @Transaction
    @SqlUpdate("UPDATE execution SET project_name = :projectName," +
            "test_type= :testType, scenario_type= :scenarioType, biz_category= :bizCategory, " +
            "biz_detail= :bizDetail, version = :version, team_name = :teamName, scenario_category = :scenarioCategory, " +
            "test_scenario_id = :testScenarioId, test_scenario_name = :testScenarioName, screen_id = :screenId, " +
            "screen_name = :screenName, test_case_id = :testCaseId, test_case_name = :testCaseName, " +
            "exec_due_date = :execDueDate, tester = :tester, test_target_type = :testTargetType, test_target_name = :testTargetName, " +
            "confirm_contents = :confirmContents, test_data = :testData, build_name = :buildName, build_version = :buildVersion, note = :note WHERE execution_id = :executionId")
    void editTestExecution(@BindBean TestExecutionVO request);

    // execution 단독 삭제 O
    @Transaction
    @SqlUpdate("DELETE FROM execution WHERE execution_id = :executionId")
    @OutParameter(name = "executionId",  sqlType = java.sql.Types.BIGINT)
    void deleteTestExecution(@Bind("execution_id") Long executionId);


    // execution 결과입력 O
    @Transaction
    @SqlUpdate("UPDATE execution SET execution_date = now(), exec_status = :execStatus, exec_result = :execResult WHERE execution_id = :executionId")
    @OutParameter(name = "executionId",  sqlType = java.sql.Types.BIGINT)
    void updateTestExecution(@Bind("execution_id") Long executionId,
                             @Bind("execution_date") Date executionDate,
                             @Bind("exec_status") String execStatus,
                             @Bind("exec_result") String execResult);

    @Transaction
    @SqlUpdate("UPDATE execution SET exec_status = :execStatus WHERE execution_id = :executionId")
    @OutParameter(name = "executionId",  sqlType = java.sql.Types.BIGINT)
    void execStatusChange(@Bind("execution_id") Long executionId, String execStatus);

}
