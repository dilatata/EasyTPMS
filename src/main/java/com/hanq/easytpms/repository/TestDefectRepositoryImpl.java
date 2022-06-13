package com.hanq.easytpms.repository;

import com.hanq.easytpms.vo.TestDefectVO;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
public class TestDefectRepositoryImpl implements TestDefectRepository{
    @Override
    public void insertTestDefect(TestDefectVO testExecutionVO) {

    }

    @Override
    public void deleteTestDefect(BigInteger executionId) {

    }

    @Override
    public List<TestDefectVO> findTestDefectListBYExecutionId(BigInteger id) {
        return null;
    }

    @Override
    public List<TestDefectVO> findDefectListByProjectName(String p_name) {
        return null;
    }

    @Override
    public TestDefectVO editTestDefect(TestDefectVO request) {
        return null;
    }

    @Override
    public void updateTestDefect(BigInteger id, String defectStatus, Date defectDate, String defectActionYn, String defectActionContents) {

    }

    @Override
    public void updateTestDefectCheckY(BigInteger id, String defectStatus, String defectCheck, Date defectCheckDate) {

    }

    @Override
    public void updateTestDefectCheckN(BigInteger id) {

    }
}
