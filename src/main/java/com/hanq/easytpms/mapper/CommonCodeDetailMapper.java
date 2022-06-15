package com.hanq.easytpms.mapper;

import com.hanq.easytpms.vo.CommonCodeDetailVO;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CommonCodeDetailMapper implements RowMapper<CommonCodeDetailVO> {
    @Override
    public CommonCodeDetailVO map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new CommonCodeDetailVO(rs.getLong("code_detail_id"),
                rs.getLong("code_group_id"),
                rs.getString("code_detail_name"),
                rs.getString("user_detail_desc"),
                rs.getLong("order_num"),
                rs.getString("user_use_yn")
        );
    }
}
