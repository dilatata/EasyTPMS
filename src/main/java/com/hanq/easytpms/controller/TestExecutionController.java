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

//    TestExecutionService testExecutionService;
    private final TestExecutionService testExecutionService;

//    @Autowired
//    public TestExecutionController(TestExecutionService testExecutionService){
//        this.testExecutionService = testExecutionService;
//    }


    private Jdbi jdbi;

    @Autowired
    public TestExecutionController(Jdbi jdbi, TestExecutionService testExecutionService) {
        this.testExecutionService = testExecutionService;
        this.jdbi = jdbi;
        jdbi.useHandle(dao -> {
            dao.execute("CREATE TABLE IF NOT EXISTS execution(" +
                    "execution_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    " project_name VARCHAR(500) NULL," +
                    " test_type VARCHAR(100) NOT NULL," +
                    " scenario_type VARCHAR(100) NOT NULL," +
                    " biz_category VARCHAR(200) NULL," +
                    " biz_detail VARCHAR(500) NULL," +
                    " version VARCHAR(10) NULL," +
                    " team_name VARCHAR(100) NULL," +
                    " scenario_category VARCHAR(200) NULL," +
                    " test_scenario_id VARCHAR(100) NULL," +
                    " test_scenario_name VARCHAR(500) NULL," +
                    " screen_id VARCHAR(100) NULL," +
                    " screen_name VARCHAR(500) NULL," +
                    " test_case_id VARCHAR(100) NOT NULL," +
                    " test_case_name VARCHAR(500) NOT NULL," +
                    " exec_due_date DATE NULL," +
                    " tester VARCHAR(100) NOT NULL," +
                    " test_target_type VARCHAR(100) NULL," +
                    " test_target_name VARCHAR(1000) NULL," +
                    " confirm_contents VARCHAR(2000) NULL," +
                    " test_data VARCHAR(2000) NULL," +
                    " build_name VARCHAR(500) NULL," +
                    " build_version VARCHAR(100) NULL," +
                    " note VARCHAR(2000) NULL," +
                    " execution_date DATE NULL," +
                    " exec_status VARCHAR(100) NULL," +
                    " exec_result VARCHAR(2000) NULL) ");
        });
    }


    // execution 단독 생성
    @PostMapping("/execution/create")
    public void insertTestExecution(@RequestBody TestExecutionVO testExecutionVO){
        testExecutionService.insertTestExecution(testExecutionVO);
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
