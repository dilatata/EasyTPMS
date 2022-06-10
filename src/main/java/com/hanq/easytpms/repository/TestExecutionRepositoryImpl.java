package com.hanq.easytpms.repository;

import com.hanq.easytpms.vo.TestExecutionVO;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class TestExecutionRepositoryImpl implements TestExecutionRepository{

    @Override
    public void createTestExecutionTable() {
    }

    @Override
    public TestExecutionVO insertTestExecution(TestExecutionVO testExecutionVO) {
        return null;
    }

    @Override
    public List<TestExecutionVO> getTestExecutionInfoByProjectName(String p_name) {
        return null;
    }

    @Override
    public TestExecutionVO getTestExecutionInfoByExecutionId(BigInteger id) {
        return null;
    }

    @Override
    public void editTestExecution(TestExecutionVO request) {
//        return null;
    }

    @Override
    public void deleteTestExecution(BigInteger executionId) {

    }

    @Override
    public void updateTestExecution(BigInteger executionId, Date executionDate, String execStatus, String execResult) {

    }
}
