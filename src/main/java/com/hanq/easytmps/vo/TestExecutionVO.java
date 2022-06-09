package com.hanq.easytmps.vo;

import lombok.*;

import java.sql.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class TestExecutionVO {
    private Long executionId;
    private String projectName;
    private String testType;
    private String scenarioType;
    private String bizCategory;
    private String bizDetail;
    private String version;
    private String teamName;
    private String scenarioCategory;
    private String testScenarioId;
    private String testScenarioName;
    private String screenId;
    private String screenName;
    private String testCaseId;
    private String testCaseName;
    private Date execDueDate;
    private String tester;
    private String testTargetType;
    private String testTargetName;
    private String confirmContents;
    private String testData;
    private String buildName;
    private String buildVersion;
    private String note;
    private Date executionDate;
    private String execStatus;
    private String execResult;

    public TestExecutionVO(long execution_id, String project_name, String test_type, String scenario_type, String biz_category, String biz_detail, String version, String team_name, String scenario_category, String test_scenario_id, String test_scenario_name, String screen_id, String screen_name, String test_case_id, String test_case_id1, Date exec_due_date, String tester, String test_target_type, String test_target_name, String confirm_contents, String test_data, String build_name, String build_version, String note, Date execution_date, String exec_status, String exec_result) {
    }
}
