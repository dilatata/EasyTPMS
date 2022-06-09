package com.hanq.easytpms.repository;

import com.hanq.easytpms.vo.TestExecutionVO;

import java.util.List;

public interface TestExecutionRepository {

    TestExecutionVO insertTestExecution(TestExecutionVO testExecutionVO); // dao 가 insert query  담당

    List<TestExecutionVO> getTestExecutionInfoByProjectName(String projectName);

    TestExecutionVO getTestExecutionInfoByExecutionId(Long executionId);
}
