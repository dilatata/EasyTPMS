package com.hanq.easytpms.mapper;

import com.hanq.easytpms.vo.ResponseTestDefectVO;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResponseTestDefectRowMapper implements RowMapper<ResponseTestDefectVO> {
    @Override
    public ResponseTestDefectVO map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new ResponseTestDefectVO(
                rs.getLong("execution_id"),
                rs.getLong("defect_id"),
                rs.getString("defect_category"),
                rs.getString("defect_contents"),
                rs.getString("defect_status"),
                rs.getString("created_by"),
                rs.getDate("create_at"),
                rs.getString("defect_team"),
                rs.getString("defect_charger"),
                rs.getDate("defect_start_due_date"),
                rs.getDate("defect_end_due_date"),
                rs.getDate("defect_date"),
                rs.getString("defect_action_yn"),
                rs.getString("defect_action_contents"),
                rs.getString("defect_check"),
                rs.getDate("defect_check_date"),
                rs.getString("test_type"),
                rs.getString("scenario_type"),
                rs.getString("scenario_category"),
                rs.getString("test_case_id"),
                rs.getString("test_case_name"),
                rs.getString("test_scenario_id"),
                rs.getString("test_scenario_name"),
                rs.getString("version"),
                rs.getDate("execution_date"),
                rs.getString("exec_status"),
                rs.getString("exec_result"));
    }
}
