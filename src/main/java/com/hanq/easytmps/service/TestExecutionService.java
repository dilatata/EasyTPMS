package com.hanq.easytmps.service;

import com.hanq.easytmps.repository.TestExecutionRepository;
import com.hanq.easytmps.vo.TestExecutionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestExecutionService {

    @Autowired
    TestExecutionRepository testExecutionRepository;

    public TestExecutionVO insertTestExecution(TestExecutionVO testExecutionVO){
        return testExecutionRepository.insertTestExecution(testExecutionVO);
    }

    public List<TestExecutionVO> getTestExecutionList(String projectName){
        return testExecutionRepository.getTestExecutionInfoByProjectName(projectName);
    }

    public TestExecutionVO getTestExecutionInfo(Long executionId) {
        return testExecutionRepository.getTestExecutionInfoByExecutionId(executionId);
    }
}
