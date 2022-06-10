package com.hanq.easytpms.service;

import com.hanq.easytpms.repository.TestExecutionRepository;
import com.hanq.easytpms.vo.TestExecutionVO;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class TestExecutionService {

    TestExecutionRepository testExecutionRepository;



    // 단일 execution 생성
    public TestExecutionVO insertTestExecution(TestExecutionVO testExecutionVO){
        return testExecutionRepository.insertTestExecution(testExecutionVO);
    }

    // 프로젝트 전체 조회 , 조건조회 추가하기
    public List<TestExecutionVO> getTestExecutionList(String projectName){
        return testExecutionRepository.getTestExecutionInfoByProjectName(projectName);
    }

    //  execution 단독 조회
    public TestExecutionVO getTestExecutionInfo(BigInteger executionId) {
        return testExecutionRepository.getTestExecutionInfoByExecutionId(executionId);
    }

    // execution 단독 수정 -> request mapping 해서 저장
    public void editTestExecution(TestExecutionVO request, BigInteger executionId) {
        testExecutionRepository.editTestExecution(request);
    }

    // execution 단독 삭제
    public void deleteTestExecution(BigInteger executionId) {
        testExecutionRepository.deleteTestExecution(executionId);
    }

    // execution 결과입력 -> TestExecutionVO 말고 ExecutionID 수행일 수행상태 수행결과 전달
    public void updateTestExecution(TestExecutionVO request) {
        BigInteger executionId = BigInteger.valueOf(request.getExecutionId());
        Date executionDate = request.getExecutionDate();
        String execStatus = request.getExecStatus();
        String execResult = request.getExecResult();
        testExecutionRepository.updateTestExecution(executionId, executionDate, execStatus, execResult);
    }
}
