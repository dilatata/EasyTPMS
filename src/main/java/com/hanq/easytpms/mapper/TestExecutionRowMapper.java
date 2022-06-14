package com.hanq.easytpms.mapper;


import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import com.hanq.easytpms.vo.TestExecutionVO;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TestExecutionRowMapper implements RowMapper<TestExecutionVO> {

    // 각 행에 대해 한 번씩 호출 List<TestExecutionVO> vo = handle.createQuery("").map(new TestExecutionRowMapper()).list(); -> list 생성 entityToVO
    // 매핑 유형 지정하지 않고 등록도 가능, 메핑된 유형 자동 검색  handle.registerRowMapper(new UserMapper());
    @Override
    public TestExecutionVO map(ResultSet rs, StatementContext ctx) throws SQLException {
//        TestExecutionVO execution = new TestExecutionVO();
//            execution.setExecutionId(rs.getLong("execution_id"));
//            execution.setTestType(rs.getString("test_type"));
//            execution.setScenarioType(rs.getString("scenario_type"));
//            execution.setBizCategory(rs.getString("biz_category"));
//            execution.setBizDetail(rs.getString("biz_detail"));
//            execution.setVersion(rs.getString("version"));
//            execution.setTeamName(rs.getString("team_name"));
//            execution.setScenarioCategory(rs.getString("scenario_category"));
//            execution.setTestScenarioId(rs.getString("test_scenario_id"));
//            execution.setTestScenarioName(rs.getString("test_scenario_name"));
//            execution.setScreenId(rs.getString("screen_id"));
//            execution.setScreenName(rs.getString("screen_name"));
//            execution.setTestCaseId(rs.getString("test_case_id"));
//            execution.setTestCaseName(rs.getString("test_case_name"));
//            execution.setExecDueDate(rs.getDate("exec_due_date"));
//            execution.setTester(rs.getString("tester"));
//            execution.setTestTargetType(rs.getString("test_target_type"));
//            execution.setTestTargetName(rs.getString("test_target_name"));
//            execution.setConfirmContents(rs.getString("confirm_contents"));
//            execution.setTestData(rs.getString("test_data"));
//            execution.setBuildName(rs.getString("build_name"));
//            execution.setBuildVersion(rs.getString("build_version"));
//            execution.setNote(rs.getString("note"));
//            execution.setExecutionDate(rs.getDate("execution_date"));
//            execution.setExecStatus(rs.getString("exec_status"));
//            execution.setExecResult(rs.getString("exec_result"));
//        return execution;



        return new TestExecutionVO(
                rs.getLong("execution_id"),
                rs.getString("project_name"),
                rs.getString("test_type"),
                rs.getString("scenario_type"),
                rs.getString("biz_category"),
                rs.getString("biz_detail"),
                rs.getString("version"),
                rs.getString("team_name"),
                rs.getString("scenario_category"),
                rs.getString("test_scenario_id"),
                rs.getString("test_scenario_name"),
                rs.getString("screen_id"),
                rs.getString("screen_name"),
                rs.getString("test_case_id"),
                rs.getString("test_case_name"),
                rs.getDate("exec_due_date"),
                rs.getString("tester"),
                rs.getString("test_target_type"),
                rs.getString("test_target_name"),
                rs.getString("confirm_contents"),
                rs.getString("test_data"),
                rs.getString("build_name"),
                rs.getString("build_version"),
                rs.getString("note"),
                rs.getDate("execution_date"),
                rs.getString("exec_status"),
                rs.getString("exec_result"));
    }
}
