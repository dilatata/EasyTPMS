package com.hanq.easytpms.repository;

import com.hanq.easytpms.mapper.TestDefectFileMapper;
import com.hanq.easytpms.vo.TestDefectFileVO;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDefectFileRepositoryImpl implements TestDefectFileRepository{

    private Jdbi jdbi;

    public TestDefectFileRepositoryImpl(Jdbi jdbi){
        this.jdbi = jdbi;
    }


    @Override
    public void insertTestDefectFile(TestDefectFileVO testdefectFileVO) {
        jdbi.useHandle(dao->dao.createUpdate("INSERT INTO  attach_file(execution_id, defect_id, file_order, file_name, file_size, file_loc) " +
                        "VALUES( :executionId, :defectId, :fileOrder, :fileName, :fileSize, :fileLoc) ")
                        .bindBean(testdefectFileVO)
                        .execute()
        );
    }

    @Override
    public void insertTestDefectFile2(TestDefectFileVO testdefectFileVO) {
        jdbi.useHandle(dao->dao.createUpdate("INSERT INTO  attach_file(execution_id, defect_id, file_order, file_name, file_size, file_loc) " +
                        "VALUES( :executionId, CURRVAL('seq_defect_id'), :fileOrder, :fileName, :fileSize, :fileLoc) ")
                .bindBean(testdefectFileVO)
                .execute()
        );
    }

    @Override
    public List<TestDefectFileVO> getTestDefectFileList(Long id) {
        Handle handle = jdbi.open();
        List<TestDefectFileVO> testDefectFileVOList = handle.createQuery("SELECT * FROM attach_file WHERE defect_id = :defectId")
                .bind("defectId", id)
                .map(new TestDefectFileMapper())
                .list();
        handle.close();
        return testDefectFileVOList;
    }

    @Override
    public void deleteTestDefectFile(Long fileId) {
        jdbi.useHandle(dao -> dao.createUpdate("DELETE attach_file WHERE defect_id = :defectId")
                .bind("fileId", fileId)
                .execute()
        );
    }
}
