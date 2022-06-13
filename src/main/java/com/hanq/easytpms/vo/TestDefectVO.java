package com.hanq.easytpms.vo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.springframework.boot.SpringApplicationExtensionsKt;

import java.math.BigInteger;
import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class TestDefectVO {
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

    public TestDefectVO(@ColumnName("execution_id") long executionId ,
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
                        @ColumnName("defect_check_date") Date defectCheckDate
                        ){
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
