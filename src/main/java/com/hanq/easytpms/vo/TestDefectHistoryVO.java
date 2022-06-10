package com.hanq.easytpms.vo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class TestDefectHistoryVO {
    private Long executionId;
    private Long defectId;
    private String defectStatus;
    private String defectTeam;
    private String defectCharge;
    private String defectActionContents;
    private Date defectActionDate;

    public TestDefectHistoryVO(@ColumnName("execution_id") Long executionId,
                               @ColumnName("defect_id") Long defectId,
                               @ColumnName("defect_status") String defectStatus,
                               @ColumnName("defect_team") String defectTeam,
                               @ColumnName("defect_charge") String defectCharge,
                               @ColumnName("defect_action_contents") String defectActionContents,
                               @ColumnName("defect_action_date") Date defectActionDate){
        this.executionId = executionId;
        this.defectId = defectId;
        this.defectStatus = defectStatus;
        this.defectTeam = defectTeam;
        this.defectCharge = defectCharge;
        this.defectActionContents = defectActionContents;
        this.defectActionDate = defectActionDate;
    }
}
