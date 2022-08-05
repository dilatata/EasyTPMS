package com.hanq.easytpms.vo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jdbi.v3.core.mapper.reflect.ColumnName;

@Data
@Getter
@Setter
@NoArgsConstructor
public class TestDefectFileVO {
    private Long fileId;
    private Long defectId;
    private Long executionId;
    private Long fileOrder;
    private String fileName;
    private Long fileSize;
    private String fileLoc;



    public TestDefectFileVO(@ColumnName("file_id") Long fileId,
                            @ColumnName("defect_id") Long defectId,
                            @ColumnName("execution_id") Long executionId,
                            @ColumnName("file_order") Long fileOrder,
                            @ColumnName("file_name") String fileName,
                            @ColumnName("file_size") Long fileSize,
                            @ColumnName("file_loc") String fileLoc) {
        this.fileId = fileId;
        this.defectId = defectId;
        this.executionId = executionId;
        this.fileOrder = fileOrder;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileLoc = fileLoc;
    }
}
