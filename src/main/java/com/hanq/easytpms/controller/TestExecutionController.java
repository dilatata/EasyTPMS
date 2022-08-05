package com.hanq.easytpms.controller;

import com.hanq.easytpms.service.TestExecutionService;
import com.hanq.easytpms.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import com.hanq.easytpms.vo.TestExecutionVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;


@RestController
@EnableWebMvc
@Slf4j
public class TestExecutionController {

    private final TestExecutionService testExecutionService;


    @Autowired
    public TestExecutionController(TestExecutionService testExecutionService, HttpServletRequest request, HttpServletResponse response) {
        this.testExecutionService = testExecutionService;
    }

    @Autowired
    private HttpSession session;


    // execution 단독 생성
    @PostMapping("/execution/create")
    public void insertTestExecution(@RequestBody TestExecutionVO testExecutionVO, HttpServletRequest request, HttpServletResponse response){
        session = request.getSession(false);
        log.info("======= create execution list ======");

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            log.info("session get attribute true");
            log.info("sessionId={}", session.getId());
            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
//            log.info("sessionUserInfo toString = {}", session.getAttribute("userInfo").toString());
            log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
            log.info("creationTime={}", new java.util.Date(session.getCreationTime()));
            log.info("lastAccessedTime={}", new java.util.Date(session.getLastAccessedTime()));
            log.info("isNew={}", session.isNew());


            UserVO loginUserInfo = new UserVO();
            loginUserInfo = (UserVO) session.getAttribute("userInfo");
            String sessionUserName = loginUserInfo.getUserName();
            String sessionUserID = loginUserInfo.getUserId();
            log.info(String.valueOf(loginUserInfo));
            log.info("session UserName : " + sessionUserName + "session UserID : " + sessionUserID);

            testExecutionService.insertTestExecution(testExecutionVO);
        }
    }


    // 프로젝트 전체 조회 , 조건조회 추가하기
    @GetMapping("/execution/list/{projectName}")
    public List<TestExecutionVO> getTestExecutionList(@PathVariable("projectName") String projectName,
                                                      HttpServletRequest request, HttpServletResponse response) {

        session = request.getSession(false);
        log.info("session check!!! : " + session);

        if (session == null) {
            System.out.println("userId 없음!");
            return null; // 로그인 화면으로 redirect해야 함
        }

        return testExecutionService.getTestExecutionList(projectName);
    }

    // 프로젝트 전제 조회, full
    @GetMapping("/execution/list")
    public  List<TestExecutionVO> getTestExecutionList( HttpServletRequest request, HttpServletResponse response){

        session = request.getSession(false);
        log.info("======= get execution list ======");

        if (isEmpty(session)){
            log.info("session get attribute empty");
            return null;
        }
        else {
            log.info("session get attribute true");
            log.info("sessionId={}", session.getId());
            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
//            log.info("sessionUserInfo toString = {}", session.getAttribute("userInfo").toString());
            log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
            log.info("creationTime={}", new java.util.Date(session.getCreationTime()));
            log.info("lastAccessedTime={}", new java.util.Date(session.getLastAccessedTime()));
            log.info("isNew={}", session.isNew());


            UserVO loginUserInfo = new UserVO();
            loginUserInfo = (UserVO) session.getAttribute("userInfo");
            String sessionUserName = loginUserInfo.getUserName();
            String sessionUserID = loginUserInfo.getUserId();
            log.info(String.valueOf(loginUserInfo));
            log.info("session UserName : " + sessionUserName + "session UserID : " + sessionUserID);
        }
        return testExecutionService.getTestExecutionList();
    }

    // execution 상세 조회
    @GetMapping("/execution/detail/{executionId}")
    public TestExecutionVO getTestExecution(@PathVariable("executionId") Long executionId,
                                            HttpServletRequest request, HttpServletResponse response){
        return testExecutionService.getTestExecutionInfo(executionId);
    }

    // execution 단독 수정 -> 결과 입력X
    @PostMapping("/execution/update/{executionId}")
    public void editTestExecution(@RequestBody TestExecutionVO testExecutionVO,
                                  HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);
        log.info("======= edit execution  ======");

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            log.info("session get attribute true");
            log.info("sessionId={}", session.getId());
            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
//            log.info("sessionUserInfo toString = {}", session.getAttribute("userInfo").toString());
            log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
            log.info("creationTime={}", new java.util.Date(session.getCreationTime()));
            log.info("lastAccessedTime={}", new java.util.Date(session.getLastAccessedTime()));
            log.info("isNew={}", session.isNew());


            UserVO loginUserInfo = new UserVO();
            loginUserInfo = (UserVO) session.getAttribute("userInfo");
            String sessionUserName = loginUserInfo.getUserName();
            String sessionUserID = loginUserInfo.getUserId();
            log.info(String.valueOf(loginUserInfo));
            log.info("session UserName : " + sessionUserName + "session UserID : " + sessionUserID);


        }

        testExecutionService.editTestExecution(testExecutionVO);
    }

    // execution 단독 삭제
    @DeleteMapping("/execution/delete/{executionId}")
    public void deleteTestExecution(@PathVariable("executionId") Long executionId,
                                    HttpServletRequest request, HttpServletResponse response) {

        session = request.getSession(false);
        log.info("=======delete execution ======");
//
//        if (isEmpty(session)) {
//            log.info("session get attribute empty");
//        } else {
//            log.info("session get attribute true");
//            log.info("sessionId={}", session.getId());
//            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
//            log.info("sessionUserInfo toString = {}", session.getAttribute("userInfo").toString());
//            log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
//            log.info("creationTime={}", new java.util.Date(session.getCreationTime()));
//            log.info("lastAccessedTime={}", new java.util.Date(session.getLastAccessedTime()));
//            log.info("isNew={}", session.isNew());
//
//
//            UserVO loginUserInfo = new UserVO();
//            loginUserInfo = (UserVO) session.getAttribute("userInfo");
//            String sessionUserName = loginUserInfo.getUserName();
//            String sessionUserID = loginUserInfo.getUserId();
//            log.info(String.valueOf(loginUserInfo));
//            log.info("session UserName : " + sessionUserName + "session UserID : " + sessionUserID);

        testExecutionService.deleteTestExecution(executionId);
//        }
    }

    // execution 결과입력
    @PostMapping("/execution/result/{executionId}")
    public void updateTestExecution(@RequestBody TestExecutionVO exec_result,
                                    HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);
        log.info("======= result insult execution ======");

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            log.info("session get attribute true");
            log.info("sessionId={}", session.getId());
            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
//            log.info("sessionUserInfo toString = {}", session.getAttribute("userInfo").toString());
            log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
            log.info("creationTime={}", new java.util.Date(session.getCreationTime()));
            log.info("lastAccessedTime={}", new java.util.Date(session.getLastAccessedTime()));
            log.info("isNew={}", session.isNew());


            UserVO loginUserInfo = new UserVO();
            loginUserInfo = (UserVO) session.getAttribute("userInfo");
            String sessionUserName = loginUserInfo.getUserName();
            String sessionUserID = loginUserInfo.getUserId();
            log.info(String.valueOf(loginUserInfo));
            log.info("session UserName : " + sessionUserName + "session UserID : " + sessionUserID);

            testExecutionService.updateTestExecution(exec_result);

        }
    }

    // if execution_status = 실패 execution update + defect create
    @PostMapping("/execution/defect")
    public void saveTestExecution(@RequestBody TestExecutionVO exec_result,
                                  HttpServletRequest request, HttpServletResponse response, MultipartHttpServletRequest mhsr) {
        session = request.getSession(false);
        log.info("======= result defect create execution  ======");

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            log.info("session get attribute true");
            log.info("sessionId={}", session.getId());
            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
//            log.info("sessionUserInfo toString = {}", session.getAttribute("userInfo").toString());
            log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
            log.info("creationTime={}", new java.util.Date(session.getCreationTime()));
            log.info("lastAccessedTime={}", new java.util.Date(session.getLastAccessedTime()));
            log.info("isNew={}", session.isNew());


            UserVO loginUserInfo = new UserVO();
            loginUserInfo = (UserVO) session.getAttribute("userInfo");
            String sessionUserName = loginUserInfo.getUserName();
            String sessionUserId = loginUserInfo.getUserId();
            log.info(String.valueOf(loginUserInfo));
            log.info("session UserName : " + sessionUserName + "session UserID : " + sessionUserId);

            log.info("i want to see there is a createdby value: ",String.valueOf(exec_result));
            testExecutionService.saveTestExecution(exec_result, sessionUserId);



        }
    }

    // excel upload
    @PostMapping(value="/execution/excel/upload" , consumes={MediaType.MULTIPART_FORM_DATA_VALUE})
    public void ExcelUpload(@RequestBody MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

        session = request.getSession(false);
        log.info("======= excel data upload ======");

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            testExecutionService.excelUpload(file);
        }
    }

    // excel template download
    @GetMapping("/execution/excel/download")
    public void downloadExcelTemplate(HttpServletRequest request, HttpServletResponse response) throws IOException, InvalidFormatException {
        session = request.getSession(false);
        log.info("======= excel template download ======");

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            log.info("session get attribute true");
            log.info("sessionId={}", session.getId());
            log.info("isNew={}", session.isNew());

            UserVO loginUserInfo = new UserVO();
            loginUserInfo = (UserVO) session.getAttribute("userInfo");
            String sessionUserName = loginUserInfo.getUserName();
            String sessionUserID = loginUserInfo.getUserId();
            log.info(String.valueOf(loginUserInfo));
            log.info("session UserName : " + sessionUserName + "session UserID : " + sessionUserID);

            testExecutionService.downloadExcelTemplate(request, response);
        }

    }

}

