package com.hanq.easytpms.service;

import com.hanq.easytpms.repository.TestDefectHistoryRepository;
import com.hanq.easytpms.repository.TestDefectRepository;
import com.hanq.easytpms.repository.TestExecutionRepository;
import com.hanq.easytpms.vo.TestDefectVO;
import com.hanq.easytpms.vo.TestExecutionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TestExecutionService {


    private final TestExecutionRepository testExecutionRepository;

    private final TestDefectRepository testDefectRepository;
    private final TestDefectHistoryRepository testDefectHistoryRepository;

    private TestExecutionVO testExecutionVO;
    private TestDefectVO testDefectVO;

    @Autowired
    public TestExecutionService(TestExecutionRepository testExecutionRepository, TestDefectRepository testDefectRepository,  TestDefectHistoryRepository testDefectHistoryRepository) {
        this.testExecutionRepository = testExecutionRepository;
        this.testDefectRepository = testDefectRepository;
        this.testDefectHistoryRepository = testDefectHistoryRepository;
    }


    // 단일 execution 생성
    public void insertTestExecution(TestExecutionVO request){
        testExecutionRepository.insertTestExecution(request);
    }

    // 프로젝트 전체 조회 , 조건조회 추가하기
    public List<TestExecutionVO> getTestExecutionList(String projectName){
        return testExecutionRepository.getTestExecutionInfoByProjectName(projectName);
    }

    public List<TestExecutionVO > getTestExecutionList(){
        return testExecutionRepository.getTestExecutionList();
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

        testExecutionRepository.updateTestExecution(executionId, executionDate, execStatus, execResult);
    }

    // if execution_status = 실패 execution update + defect create
    public void saveTestExecution(TestExecutionVO request) {
        Long executionId = request.getExecutionId();
        Date executionDate = request.getExecutionDate();
        String execStatus = request.getExecStatus();
        String execResult = request.getExecResult();
        List<TestDefectVO> listTestDefectVO = request.getTestDefectList();
        TestDefectVO testDefectVO = listTestDefectVO.get(0);
        System.out.println(testDefectVO);
        testExecutionRepository.updateTestExecution(executionId, executionDate, execStatus, execResult);
        testDefectRepository.insertTestDefect(testDefectVO);
    }
}
