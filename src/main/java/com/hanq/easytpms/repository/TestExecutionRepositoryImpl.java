package com.hanq.easytpms.repository;

import com.hanq.easytpms.vo.TestExecutionVO;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
public class TestExecutionRepositoryImpl implements TestExecutionRepository{

    private Jdbi jdbi;

    public TestExecutionRepositoryImpl(Jdbi jdbi){
        this.jdbi = jdbi;
    }

    @Override
    public void insertTestExecution(TestExecutionVO testExecutionVO) {
        jdbi.useHandle(dao -> dao.createUpdate("INSERT INTO execution (project_name,test_type,scenario_type, biz_category, biz_detail, version, team_name, scenario_category, test_scenario_id, test_scenario_name, screen_id, screen_name, test_case_id, test_case_name, exec_due_date, tester, test_target_type, test_target_name, confirm_contents, test_data, build_name, build_version, note, execution_date, exec_status, exec_result) " +
                        "VALUES( :projectName,:testType,:scenarioType, :bizCategory, :bizDetail, :version, :teamName, :scenarioCategory, :testScenarioId, :testScenarioName, :screenId, :screenName, :testCaseId, :testCaseName, :execDueDate, :tester, :testTargetType, :testTargetName, :confirmContents, :testData, :buildName, :buildVersion, :note, :executionDate, :execStatus, :execResult)")
                .bindBean(testExecutionVO)
                .execute()
        );
    }

    @Override
    public List<TestExecutionVO> getTestExecutionInfoByProjectName(String p_name) {
        return null;
    }

    @Override
    public TestExecutionVO getTestExecutionInfoByExecutionId(BigInteger id) {
        return null;
    }

    @Override
    public void editTestExecution(TestExecutionVO request) {
//        return null;
    }

    @Override
    public void deleteTestExecution(BigInteger executionId) {

    }

    @Override
    public void updateTestExecution(BigInteger executionId, Date executionDate, String execStatus, String execResult) {

    }
}
