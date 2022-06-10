package com.hanq.easytpms.vo;

import lombok.*;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

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

    public TestExecutionVO(@ColumnName("execution_id") long executionId,
                           @ColumnName("project_name") String projectName,
                           @ColumnName("test_type") String testType,
                           @ColumnName("scenario_type") String scenarioType,
                           @ColumnName("biz_category") String bizCategory,
                           @ColumnName("biz_detail") String bizDetail,
                           @ColumnName("version") String version,
                           @ColumnName("team_name") String teamName,
                           @ColumnName("scenario_category") String scenarioCategory,
                           @ColumnName("test_scenario_id") String testScenarioId,
                           @ColumnName("test_scenario_name") String testScenarioName,
                           @ColumnName("screen_id") String screenId,
                           @ColumnName("screen_name") String screenName,
                           @ColumnName("test_case_id") String testCasId,
                           @ColumnName("test_case_name") String testCasename,
                           @ColumnName("exec_due_date") Date execDueDate,
                           @ColumnName("tester") String tester,
                           @ColumnName("test_target_type") String testTargetType,
                           @ColumnName("test_target_name") String testTargetName,
                           @ColumnName("confirm_contents") String confirmContents,
                           @ColumnName("test_data") String testData,
                           @ColumnName("build_name") String buildName,
                           @ColumnName("build_version") String buildVersion,
                           @ColumnName("note") String note,
                           @ColumnName("execution_date") Date executionDate,
                           @ColumnName("exec_status") String execStatus,
                           @ColumnName("exec_result") String execResult) {
        this.executionId = executionId;
        this.projectName = projectName;
        this.testType = testType;
        this.scenarioType = scenarioType;
        this.bizCategory = bizCategory;
        this.bizDetail = bizDetail;
        this.version = version;
        this.teamName = teamName;
        this.scenarioCategory = scenarioCategory;
        this.testScenarioId = testScenarioId;
        this.testScenarioName = testScenarioName;
        this.screenId = screenId;
        this.screenName = screenName;
        this.testCaseId = testCasId;
        this.testCaseName = testCasename;
        this.execDueDate = execDueDate;
        this.tester = tester;
        this.testTargetType = testTargetType;
        this.testTargetName = testTargetName;
        this.confirmContents = confirmContents;
        this.testData = testData;
        this.buildName = buildName;
        this.buildVersion = buildVersion;
        this.note = note;
        this.executionDate= executionDate;
        this.execStatus = execStatus;
        this.execResult = execResult;

    }
}
