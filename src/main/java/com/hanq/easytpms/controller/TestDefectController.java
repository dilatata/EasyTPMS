package com.hanq.easytpms.controller;

import com.hanq.easytpms.service.TestDefectService;
import com.hanq.easytpms.vo.TestDefectVO;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@RestController
@EnableWebMvc
@Slf4j
public class TestDefectController {

    private final TestDefectService testDefectService;

    @Autowired
    public TestDefectController(TestDefectService testDefectService, Jdbi jdbi) {
        this.testDefectService = testDefectService;
    }


    // 시나리오 결함 생성 -> defect history 생성 시작점?
    @PostMapping(value = "/defect/create/{executionId}")
    public void insertTestDefect(@RequestBody TestDefectVO request) {
        testDefectService.insertTestDefect(request);
    }

    // 시나리오 결함 개별 삭제
    @DeleteMapping("/defect/delete/{executionId}")
    public void deleteTestDefect(@PathVariable("executionId") Long executionId) {
        testDefectService.deleteTestDefect(executionId);
    }

    // 연관 결함 리스트 조회
    @GetMapping(value = "/defect/{executionId}")
    public Object findTestDefectListByExecutionId(@PathVariable("executionId") Long executionId) {
        return testDefectService.findTestDefectListByExecutionId(executionId);
    }


    // 결함 상세 조회
    @GetMapping("/defect/detail/{defectId}")
    public TestDefectVO findTestDefectInfoByDefectId(@PathVariable("defectId") Long defectId){
        return testDefectService.findTestDefectInfoByDefectId(defectId);
    }


    // 결함 리스트 조회 // join projectName 에 해당하는 executionId 를 갖고 있는 data defect 테이블에서 찾아오기
    @GetMapping("/defect/list/{projectName}")
    public Object findAllDefectList(@PathVariable("projectName") String projectName) {
        return testDefectService.findDefectListByProjectName(projectName);
    }

    // 결함 세부조건 작성(수정) -> history 생성
    @PostMapping("/defect/edit/detail/{defectId}")
    public void editTestDefect(@RequestBody TestDefectVO request) {
        testDefectService.editTestDefect(request);

    }

    // 결함조치 결과 작성 -> history 생성
    @PostMapping("/defect/update/result/{defectId}")
    public void updateTestDefect(@RequestBody TestDefectVO request) {
        testDefectService.updateTestDefect(request);
    }

    // 결함조치 확인 작성
    @PostMapping("/defect/check/{defectId}")
    public void updateTestDefectCheck(@RequestBody TestDefectVO request) {
        testDefectService.updateTestDefectCheck(request);}

    // 결함 첨부파일 생성

}
