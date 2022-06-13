package com.hanq.easytpms.service;

import com.hanq.easytpms.mapper.TestExecutionRowMapper;
import com.hanq.easytpms.repository.TestExecutionRepository;
import com.hanq.easytpms.repository.TestExecutionRepositoryImpl;
import com.hanq.easytpms.vo.TestExecutionVO;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class TestExecutionService {


    private final TestExecutionRepository testExecutionRepository;
    private TestExecutionVO testExecutionVO;

    @Autowired
    public TestExecutionService(TestExecutionRepository testExecutionRepository) {
        this.testExecutionRepository = testExecutionRepository;
    }


    // 단일 execution 생성
    public void insertTestExecution(TestExecutionVO request){
        testExecutionRepository.insertTestExecution(request);
    }

    // 프로젝트 전체 조회 , 조건조회 추가하기
    public List<TestExecutionVO> getTestExecutionList(String projectName){
        return testExecutionRepository.getTestExecutionInfoByProjectName(projectName);
    }

    //  execution 단독 조회
    public TestExecutionVO getTestExecutionInfo(Long executionId) {
        return testExecutionRepository.getTestExecutionInfoByExecutionId(executionId);
    }

    // execution 단독 수정 -> request mapping 해서 저장
    public void editTestExecution(TestExecutionVO request) {
        testExecutionRepository.editTestExecution(request);
    }

    // execution 단독 삭제
    public void deleteTestExecution(Long executionId) {
        testExecutionRepository.deleteTestExecution(executionId);
    }

    // execution 결과입력 -> TestExecutionVO 말고 ExecutionID 수행일 수행상태 수행결과 전달
    public void updateTestExecution(TestExecutionVO request) {
        Long executionId = request.getExecutionId();
        Date executionDate = request.getExecutionDate();
        String execStatus = request.getExecStatus();
        String execResult = request.getExecResult();
        System.out.println(executionId);
        System.out.println(executionDate);
        System.out.println(execStatus);
        System.out.println(execResult);
        testExecutionRepository.updateTestExecution(executionId, executionDate, execStatus, execResult);
    }
}
