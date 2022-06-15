package com.hanq.easytpms.repository;

import com.hanq.easytpms.vo.CommonCodeGroupVO;
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
public interface CommonCodeGroupRepository {
    // code group 생성
    @Transaction
    @SqlUpdate("INSERT INTO common_code_group(code_group_name, code_group_desc, use_yn) " +
            "VALUES(:codeGroupName, :codeGroupDesc, :useYn) ")
    @GetGeneratedKeys
    void insertCodeGroup(@BindBean CommonCodeGroupVO commonCodeGroupVO);

    // code group 수정
    @Transaction
    @SqlUpdate("UPDATE common_code_group SET " +
            "code_group_name = :codeGroupName, code_group_desc = :codeGroupDesc, use_yn = :useYn, " +
            "WHERE code_group_id = :codeGroupId ")
    @GetGeneratedKeys
    void editCodeGroup(@BindBean CommonCodeGroupVO commonCodeGroupVO);


    // code group 삭제
    @Transaction
    @SqlUpdate("DELETE FROM common_code_group WHERE code_group_id = codeGroupId")
    @OutParameter(name = "codeGroupId",  sqlType = java.sql.Types.BIGINT)
    void deleteCodeGroup(@Bind("codeGroupId") Long codeGroupId);


    // code group List 조회
    @SqlQuery("SELECT * FROM common_code_group")
    @RegisterBeanMapper(UserVO.class)
    List<CommonCodeGroupVO> getCodeGroupInfoList();


    // code group 조회
    @SqlQuery("SELECT * FROM common_code_group WHERE code_group_id = :codeGroupId")
    @RegisterBeanMapper(UserVO.class)
    @OutParameter(name = "codeGroupId",  sqlType = java.sql.Types.BIGINT)
    CommonCodeGroupVO getCodeGroupInfo(@Bind("codeGroupId") Long codeGroupId);

}
