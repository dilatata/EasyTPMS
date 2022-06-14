package com.hanq.easytpms.vo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter
@NoArgsConstructor
@Component
public class UserVO {
    private Long id;
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;
    private String roleType;

    public UserVO(@ColumnName("id") long id,
                  @ColumnName("user_id") String userId,
                  @ColumnName("user_password") String userPassword,
                  @ColumnName("user_name") String userName,
                  @ColumnName("user_email") String userEmail,
                  @ColumnName("role_type") String roleType) {
        this.id = id;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userEmail = userEmail;
        this.roleType = roleType;
    }
}
