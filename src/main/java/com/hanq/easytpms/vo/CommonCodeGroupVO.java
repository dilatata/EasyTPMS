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
public class CommonCodeGroupVO {
    private Long codeGroupId;
    private String codeGroupName;
    private String codeGroupDesc;
    private String useYn;

    public CommonCodeGroupVO(@ColumnName("code_group_id") long codeGroupId,
                             @ColumnName("code_group_name") String codeGroupName,
                             @ColumnName("code_group_desc") String codeGroupDesc,
                             @ColumnName("use_yn") String useYn) {
        this.codeGroupId = codeGroupId;
        this.codeGroupName = codeGroupName;
        this.codeGroupDesc = codeGroupDesc;
        this.useYn = useYn;
    }
}
