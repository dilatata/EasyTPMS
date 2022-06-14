package com.hanq.easytpms.mapper;

import com.hanq.easytpms.vo.UserVO;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EasyTPMSUserMapper implements RowMapper<UserVO> {
    @Override
    public UserVO map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new UserVO(rs.getLong("id"),
                rs.getString("user_id"),
                rs.getString("user_password"),
                rs.getString("user_name"),
                rs.getString("user_email"),
                rs.getString("role_type")
                );
    }
}
