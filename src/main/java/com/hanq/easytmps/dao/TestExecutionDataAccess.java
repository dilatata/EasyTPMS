package com.hanq.easytmps.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import com.hanq.easytmps.vo.TestExecutionVO;


import java.util.List;

public interface TestExecutionDataAccess {

    // 선언적 접근방식
    // 단일 execution 생성
    @SqlUpdate("INSERT INTO execution(execution_id,project_name,test_type,scenario_type, biz_category, biz_detail, version, team_name, scenario_category, test_scenario_id, test_scenario_name, screen_id, screen_name, test_case_id, test_case_name, exec_due_date, tester, test_target_type, test_target_name, confirm_contents, test_data, build_name, build_version, note, execution_date, exec_status, exec_result) " +
            "VALUES(:execution_id, :project_name,:test_type,:scenario_type, :biz_category, :biz_detail, :version, :team_name, :scenario_category, :test_scenario_id, :test_scenario_name, :screen_id, :screen_name, :test_case_id, :test_case_name, :exec_due_date, :tester, test_target_type, :test_target_name, :confirm_contents, :test_data, :build_name, :build_version, :note, :execution_date, :exec_status, :exec_result) ")
    @GetGeneratedKeys
    void insertExecution(@BindBean TestExecutionVO testExecutionVO);

//    // 전체(projectName 선택) 조회
    @SqlQuery("SELECT * FROM execution WHERE project_name = :p_name")
    @RegisterBeanMapper(TestExecutionVO.class)
    List<TestExecutionVO> getExecutionList(@Bind("project_name") String p_name);

    // 단독 조회
    @SqlQuery("SELECT * FROM users WHERE execution_id = :id")
    @RegisterBeanMapper(TestExecutionVO.class)
    TestExecutionVO getExecution(@Bind("execution_id") Long id);

    void close();
}
