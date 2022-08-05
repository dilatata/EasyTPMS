package com.hanq.easytpms.repository;

import com.hanq.easytpms.vo.TestDefectFileVO;
import com.hanq.easytpms.vo.TestDefectVO;
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
public interface TestDefectFileRepository {

    // 결함 첨부파일 생성
    @Transaction
    @SqlUpdate("INSERT INTO  attach_file( execution_id, defect_id, file_order, file_name, file_size, file_loc) " +
            "VALUES( :execution_id, :defect_id, :fileOrder, :fileName, :fileSize, :fileLoc) ")
    @GetGeneratedKeys
    void insertTestDefectFile(@BindBean TestDefectFileVO testExecutionFileVO);

    // 결함 첨부파일 리스트 조회 by defectId
    @SqlQuery("SELECT * FROM attach_file WHERE defect_id = :id")
    @RegisterBeanMapper(TestDefectFileVO.class)
    @OutParameter(name = "fileId",  sqlType = java.sql.Types.BIGINT)
    @OutParameter(name = "defectId",  sqlType = java.sql.Types.BIGINT)
    @OutParameter(name = "executionId",  sqlType = java.sql.Types.BIGINT)
    List<TestDefectFileVO> getTestDefectFileList(@Bind("defect_id") Long id);


    // 결함 첨부파일 개별 삭제
    @Transaction
    @SqlUpdate("DELETE FROM attach_file WHERE file_id = :fileId")
    @OutParameter(name = "fileId",  sqlType = java.sql.Types.BIGINT)
    void deleteTestDefectFile(@Bind("file_id") Long fileId);

}
