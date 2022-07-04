package com.hanq.easytpms.vo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.sql.Date;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
public class ResponseTestDefectVO {
    private String testType;
    private String scenarioType;
    private String scenarioCategory;
    private String testCaseId;
    private String testCaseName;
    private String testScenarioId;
    private String testScenarioName;
    private String version;
    private Date executionDate;
    private String execStatus;
    private String execResult;

    private Long executionId;
    private Long defectId;
    private String defectCategory;
    private String defectContents;
    private String defectStatus;
    private String createdBy;
    private Date createAt;
    private String defectTeam;
    private String defectCharger;
    private Date defectStartDueDate;
    private Date defectEndDueDate;
    private Date defectDate;
    private String defectActionYn;
    private String defectActionContents;
    private String defectCheck;
    private Date defectCheckDate;
    private List<TestDefectHistoryVO> testDefectHistoryVOList;

    public ResponseTestDefectVO(
                                @ColumnName("execution_id") long executionId ,
                                @ColumnName("defect_id") long defectId,
                                @ColumnName("defect_category") String defectCategory,
                                @ColumnName("defect_contents") String defectContents,
                                @ColumnName("defectStatus") String defectStatus,
                                @ColumnName("created_by") String createdBy,
                                @ColumnName("create_at") Date createAt,
                                @ColumnName("defect_team") String defectTeam,
                                @ColumnName("defect_charger") String defectCharger,
                                @ColumnName("defect_start_due_date") Date defectStartDueDate,
                                @ColumnName("defect_end_due_date") Date defectEndDueDate,
                                @ColumnName("defect_date") Date defectDate,
                                @ColumnName("defect_action_yn") String defectActionYn,
                                @ColumnName("defect_action_contents") String defectActionContents,
                                @ColumnName("defect_check") String defectCheck,
                                @ColumnName("defect_check_date") Date defectCheckDate,
                                @ColumnName("test_type") String testType,
                                @ColumnName("scenario_type") String scenarioType,
                                @ColumnName("scenario_category") String scenarioCategory,
                                @ColumnName("test_case_id") String testCasId,
                                @ColumnName("test_case_name") String testCaseName,
                                @ColumnName("test_scenario_id") String testScenarioId,
                                @ColumnName("test_scenario_name") String testScenarioName,
                                @ColumnName("version") String version,
                                @ColumnName("execution_date") Date executionDate,
                                @ColumnName("exec_status") String execStatus,
                                @ColumnName("exec_result") String execResult

                        ){
        this.testType = testType;
        this.scenarioType = scenarioType;
        this.scenarioCategory = scenarioCategory;
        this.testCaseId = testCasId;
        this.testCaseName = testCaseName;
        this.testScenarioId = testScenarioId;
        this.testScenarioName = testScenarioName;
        this.version = version;
        this.executionDate= executionDate;
        this.execStatus = execStatus;
        this.execResult = execResult;


        this.executionId = executionId;
        this.defectId = defectId;
        this.defectCategory = defectCategory;
        this.defectContents = defectContents;
        this.defectStatus = defectStatus;
        this.createdBy = createdBy;
        this.createAt = createAt;
        this.defectTeam = defectTeam;
        this.defectCharger = defectCharger;
        this.defectStartDueDate = defectStartDueDate;
        this.defectEndDueDate = defectEndDueDate;
        this.defectDate = defectDate;
        this.defectActionYn = defectActionYn;
        this.defectActionContents = defectActionContents;
        this.defectCheck = defectCheck;
        this.defectCheckDate = defectCheckDate;
    }
}
