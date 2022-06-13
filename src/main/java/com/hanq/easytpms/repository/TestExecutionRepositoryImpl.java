package com.hanq.easytpms.repository;

import com.google.common.collect.HashMultimap;
import com.hanq.easytpms.mapper.TestExecutionRowMapper;
import com.hanq.easytpms.vo.TestExecutionVO;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.JoinRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class TestExecutionRepositoryImpl implements TestExecutionRepository{

    private Jdbi jdbi;

    public TestExecutionRepositoryImpl(Jdbi jdbi){
        this.jdbi = jdbi;
    }

    @Override
    public void insertTestExecution(TestExecutionVO testExecutionVO) {
        jdbi.useHandle(dao -> dao.createUpdate("INSERT INTO execution (project_name,test_type,scenario_type, biz_category, biz_detail, version, team_name, scenario_category, test_scenario_id, test_scenario_name, screen_id, screen_name, test_case_id, test_case_name, exec_due_date, tester, test_target_type, test_target_name, confirm_contents, test_data, build_name, build_version, note, execution_date, exec_status, exec_result) " +
                        "VALUES( :projectName,:testType,:scenarioType, :bizCategory, :bizDetail, :version, :teamName, :scenarioCategory, :testScenarioId, :testScenarioName, :screenId, :screenName, :testCaseId, :testCaseName, :execDueDate, :tester, :testTargetType, :testTargetName, :confirmContents, :testData, :buildName, :buildVersion, :note, :executionDate, '미수행' , :execResult)")
                .bindBean(testExecutionVO)
                .execute()
        );
    }

    @Override
    public List<TestExecutionVO> getTestExecutionInfoByProjectName(String projectName) {
        Handle handle = jdbi.open();
        List<TestExecutionVO> testExecutionVOList = handle.createQuery("SELECT * FROM execution WHERE project_name = :projectName")
                .bind("projectName", projectName)
                .map(new TestExecutionRowMapper())
                .list();
        handle.close();
        return testExecutionVOList;
    }

    @Override
    public TestExecutionVO getTestExecutionInfoByExecutionId(Long id) {
        Handle handle = jdbi.open();
        TestExecutionVO testExecutionVO =handle.createQuery("SELECT * FROM execution WHERE execution_id = :id")
                .bind("id", id)
                .map(new TestExecutionRowMapper())
                .one();
        handle.close();
        return testExecutionVO;
    }

    @Override
    public void editTestExecution(TestExecutionVO request) {
        jdbi.useHandle(dao->dao.createUpdate("UPDATE execution SET project_name = :projectName, test_type = :testType," +
                        "scenario_type = :scenarioType, biz_category = :bizCategory, biz_detail = :bizDetail, version = :version, " +
                        "team_name = :teamName, scenario_category = :scenarioCategory, test_scenario_id = :testScenarioId, " +
                        "test_scenario_name = :testScenarioName, screen_id = :screenId, screen_name = :screenName, test_case_id = :testCaseId, " +
                        "test_case_name = :testCaseName, exec_due_date = :execDueDate, tester = :tester, test_target_type = :testTargetType, " +
                        "test_target_name = :testTargetName, confirm_contents = :confirmContents, test_data = :testData, build_name = :buildName, " +
                        "build_version = :buildVersion, note = :note WHERE execution_id = :executionId")
                .bindBean(request)
                .defineNamedBindings()
                .execute()
        );
    }

    @Override
    public void deleteTestExecution(Long executionId) {
        jdbi.useHandle(dao -> dao.createUpdate("DELETE execution WHERE execution_id = :executionId")
                .bind("executionId", executionId)
                .execute()
        );
    }


    @Override
    public void updateTestExecution(Long executionId, Date executionDate, String execStatus, String execResult) {
        jdbi.useHandle(dao->dao.createUpdate("UPDATE execution SET execution_date = :executionDate, exec_status = :execStatus, exec_result = :execResult WHERE execution_id = :executionId")
                .bind("executionId", executionId)
                .bind("executionDate", executionDate)
                .bind("execStatus",execStatus)
                .bind("execResult", execResult)
                .execute()
        );
    }
}
