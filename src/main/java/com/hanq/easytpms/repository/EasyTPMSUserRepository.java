package com.hanq.easytpms.repository;

import com.hanq.easytpms.vo.UserVO;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.customizer.OutParameter;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.jdbi.v3.sqlobject.transaction.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EasyTPMSUserRepository {
    // user 생성
    @Transaction
    @SqlUpdate("INSERT INTO tpms_user(user_id, user_password, user_name, user_email, role_type) " +
            "VALUES(:userId, :userPassword, :userName, :userEmail, :roleType) ")
    @GetGeneratedKeys
    void insertUser(@BindBean UserVO userVO);

    @Transaction
    @SqlUpdate("UPDATE tpms_user SET " +
            "user_password = :userPassword, user_email = :userEmail, " +
            "user_name = :userName, role_type = :roleType WHERE id = :id ")
    @GetGeneratedKeys
    void editUser(@BindBean UserVO userVO);

    // user 수정

    // user 삭제
    @Transaction
    @SqlUpdate("DELETE FROM tpms_user WHERE id = :id")
    @OutParameter(name = "id",  sqlType = java.sql.Types.BIGINT)
    void deleteUser(@Bind("id") Long id);


    // user List 조회
    @SqlQuery("SELECT * FROM tpms_user")
    @RegisterBeanMapper(UserVO.class)
    List<UserVO> getUserInfoList();


    // user 조회
    @SqlQuery("SELECT * FROM tpms_user WHERE id = :id")
    @RegisterBeanMapper(UserVO.class)
    @OutParameter(name = "Id",  sqlType = java.sql.Types.BIGINT)
    UserVO getUserInfo(@Bind("id") Long id);


    // login

}
