package com.hanq.easytmps.controller;

import com.hanq.easytmps.service.TestExecutionService;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.hanq.easytmps.dao.TestExecutionDataAccess;
import com.hanq.easytmps.vo.TestExecutionVO;

import java.util.List;


@RestController
@EnableWebMvc
@Slf4j
// controller에서 db 바로 연결할 수 없어야 함 - service - repository 생성하기
public class TestExecutionController {

    @Autowired
    private Jdbi jdbi;

    @Autowired
    TestExecutionService testExecutionService;


//     시나리오 개별 생성
    @PostMapping("/execution/create")
    public TestExecutionVO insertTestExecution(@RequestBody TestExecutionVO testExecutionVO){
        return testExecutionService.insertTestExecution(testExecutionVO);
    }


//     프로젝트 전체 조회 , 조건조회 추가하기
    @GetMapping("/execution/list/{projectName}")
    public List<TestExecutionVO> getTestExecutionList(@PathVariable("projectName") String projectName){
        return testExecutionService.getTestExecutionList(projectName);
    }


//     execution 단독 조회
    @GetMapping("/execution/list/{executionId}")
    public TestExecutionVO getExecution(@PathVariable("executionId") Long executionId){
        return testExecutionService.getTestExecutionInfo(executionId);
    }

    // 시나리오 개별 수정

    // 시나리오 개별 삭제

    // 시나리오 결과 입력

    // 시나리오 결함 생성

    // 연관 결함 리스트 조회

    // 결함 리스트 조회

    // 결함 세부조건 작성 (수정)

    // 조치 결과 작성

    // 조치 확인 작성성







}
