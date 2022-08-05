package com.hanq.easytpms.mapper;

import com.hanq.easytpms.vo.TestDefectFileVO;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDefectFileMapper implements RowMapper<TestDefectFileVO> {
    @Override
    public TestDefectFileVO map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new TestDefectFileVO(
                rs.getLong("file_id"),
                rs.getLong("defect_id"),
                rs.getLong("execution_id"),
                rs.getLong("file_order"),
                rs.getString("file_name"),
                rs.getLong("file_size"),
                rs.getString("file_loc"));
    }
}
