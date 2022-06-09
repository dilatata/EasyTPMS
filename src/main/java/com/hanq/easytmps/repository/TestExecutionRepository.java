package com.hanq.easytmps.repository;

import com.hanq.easytmps.vo.TestExecutionVO;
import jdk.incubator.vector.VectorOperators;

import java.util.List;

public interface TestExecutionRepository {

    TestExecutionVO insertTestExecution(TestExecutionVO testExecutionVO); // dao 가 insert query  담당

    List<TestExecutionVO> getTestExecutionInfoByProjectName(String projectName);

    TestExecutionVO getTestExecutionInfoByExecutionId(Long executionId);
}
