package com.hanq.easytpms.controller;

import com.hanq.easytpms.repository.TestExecutionRepository;
import com.hanq.easytpms.service.TestExecutionService;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.hanq.easytpms.vo.TestExecutionVO;

import java.math.BigInteger;
import java.util.List;


@RestController
@EnableWebMvc
@Slf4j
public class TestExecutionController {

    @Autowired
    TestExecutionService testExecutionService;



    // execution 단독 생성
    @PostMapping("/execution/create")
    public TestExecutionVO insertTestExecution(@RequestBody TestExecutionVO testExecutionVO){
        return testExecutionService.insertTestExecution(testExecutionVO);
    }


    // 프로젝트 전체 조회 , 조건조회 추가하기
    @GetMapping("/execution/list/{projectName}")
    public List<TestExecutionVO> getTestExecutionList(@PathVariable("projectName") String projectName){
        return testExecutionService.getTestExecutionList(projectName);
    }


    // execution 상세 조회
    @GetMapping("/execution/list/{executionId}")
    public TestExecutionVO getTestExecution(@PathVariable("executionId") Long executionId){
        return testExecutionService.getTestExecutionInfo(BigInteger.valueOf(executionId));
    }

    // execution 단독 수정 -> 결과 입력X
    @PostMapping("/execution/update/{executionId}")
    public void editTestExecution(@RequestBody TestExecutionVO request, BigInteger executionId) {
        testExecutionService.editTestExecution(request, executionId);
    }

    // execution 단독 삭제
    @DeleteMapping("/execution/delete/{executionId}")
    public void deleteTestExecution(@PathVariable("executionId") BigInteger executionId) {
        testExecutionService.deleteTestExecution(executionId);
    }

    // execution 결과입력
    @PostMapping("/execution/result/{executionId}")
    public void updateTestExecution(@RequestBody TestExecutionVO exec_result) {
        testExecutionService.updateTestExecution(exec_result);
    }


}
