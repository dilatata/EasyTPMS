package com.hanq.easytpms.mapper;

import org.jdbi.v3.core.mapper.RowMapper;
import com.hanq.easytpms.vo.CommonCodeGroupVO;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CommonCodeGroupMapper implements  RowMapper<CommonCodeGroupVO>{
    @Override
    public CommonCodeGroupVO map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new CommonCodeGroupVO(rs.getLong("code_group_id"),
                rs.getString("code_group_name"),
                rs.getString("user_group_desc"),
                rs.getString("user_use_yn")
        );
    }
}
