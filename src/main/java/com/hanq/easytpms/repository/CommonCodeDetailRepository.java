package com.hanq.easytpms.repository;

import com.hanq.easytpms.vo.CommonCodeDetailVO;
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
public interface CommonCodeDetailRepository {
    // CodeDetail 생성
    @Transaction
    @SqlUpdate("INSERT INTO common_code_detail (code_group_id, code_detail_name, code_detail_desc, order_num, use_yn) " +
            "VALUES(codeGroupId, :codeDetailName, :codeDetailDesc, :order_num, :useYn) ")
    @GetGeneratedKeys
    void insertCodeDetail(@BindBean CommonCodeDetailVO commonCodeDetailVO);

    // CodeDetail 수정
    @Transaction
    @SqlUpdate("UPDATE common_code_detail SET " +
            "code_group_id=:codeGroupId, code_detail_name=:codeDetailName, code_detail_desc=:codeDetailDesc,order_num=:order_num, use_yn = :useYn, " +
            "WHERE code_detail_id = :codeDetailId ")
    @GetGeneratedKeys
    void editCodeDetail(@BindBean CommonCodeDetailVO commonCodeDetailVO);


    // CodeDetail 삭제
    @Transaction
    @SqlUpdate("DELETE FROM common_code_detail WHERE code_Detail_id = codeDetailId")
    @OutParameter(name = "codeDetailId",  sqlType = java.sql.Types.BIGINT)
    void deleteCodeDetail(@Bind("codeDetailId") Long codeDetailId);


    // CodeDetail List 조회
    @SqlQuery("SELECT * FROM common_code_detail WHERE code_group_id =: codeGroupId")
    @RegisterBeanMapper(UserVO.class)
    @OutParameter(name = "codeGroupId",  sqlType = java.sql.Types.BIGINT)
    List<CommonCodeDetailVO> getCodeDetailList(@Bind("codeGroupId") Long codeGroupId);


}
