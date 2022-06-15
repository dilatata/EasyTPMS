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
public class CommonCodeDetailVO {
    private Long codeDetailId;
    private Long codeGroupId;
    private String codeDetailName;
    private String codeDetailDesc;
    private Long order;
    private String useYn;

    public CommonCodeDetailVO(@ColumnName("code_detail_id") long codeDetailId,
                              @ColumnName("code_group_id") long codeGroupId,
                             @ColumnName("code_detail_name") String codeDetailName,
                             @ColumnName("code_detail_desc") String codeDetailDesc,
                             @ColumnName("order") long order,
                             @ColumnName("use_yn") String useYn) {
        this.codeDetailId = codeDetailId;
        this.codeGroupId = codeGroupId;
        this.codeDetailName = codeDetailName;
        this.codeDetailDesc = codeDetailDesc;
        this.order = order;
        this.useYn = useYn;
    }
}
