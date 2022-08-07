package com.hanq.easytpms.controller;

import com.hanq.easytpms.service.TestDefectService;
import com.hanq.easytpms.service.TestExecutionService;
import com.hanq.easytpms.vo.TestDefectFileVO;
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
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.util.ObjectUtils.isEmpty;


@RestController
@EnableWebMvc
@Slf4j
public class TestExecutionController {

    private final TestExecutionService testExecutionService;
    private final TestDefectService testDefectService;



    @Autowired
    public TestExecutionController(TestExecutionService testExecutionService, TestDefectService testDefectService, HttpServletRequest request, HttpServletResponse response) {
        this.testExecutionService = testExecutionService;
        this.testDefectService = testDefectService;
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
                                  HttpServletRequest request, HttpServletResponse response) {
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


    // defect with attach file
    @PostMapping("/execution/defect/attach-file")
    public void saveTestExecutionNewDefectWithFile(@RequestPart("key") TestExecutionVO exec_result,
                                  @RequestPart("pic") MultipartFile pic,
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


            log.info("파일 이름 : " + pic.getOriginalFilename());
            log.info("파일 타입 : " + pic.getContentType());
            log.info("파일 크기 : " + pic.getSize());
            log.info(String.valueOf(pic));


            String uploadFolder = "C:\\Users\\hanq\\Desktop\\easytpmsUI";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String str = sdf.format(date);
//        String datePath = str.replace("-", File.separator);
//        일단 한개의 폴더에 첨부파일 모두 넣기 다 넣기

            /* 폴더 생성 */
            File uploadPath = new File(uploadFolder, "imageFile");
            log.info(String.valueOf(uploadPath));
//        File uploadPath = new File(uploadFolder, datePath); // 첨부파일을 어떻게 나눌까 고민해보기
            if(uploadPath.exists() == false) {
                uploadPath.mkdirs();
            }

            /* uuid 적용 파일 이름 */
            String uuid = UUID.randomUUID().toString();

            /* 파일 이름 */
            String uploadFileName = uuid + "_" + pic.getOriginalFilename();
            /* 파일 위치, 파일 이름을 합친 File 객체 */
            File saveFile = new File(uploadPath, uploadFileName);
            /* 파일 저장 */
            try {
                pic.transferTo(saveFile);
            }catch (Exception e){
                e.printStackTrace();
            }
            TestDefectFileVO defectFileVO = new TestDefectFileVO();

            defectFileVO.setFileName(pic.getOriginalFilename());
            defectFileVO.setFileSize(pic.getSize());
            defectFileVO.setFileLoc(String.valueOf(uploadPath));
            defectFileVO.setExecutionId(exec_result.getExecutionId());

            System.out.println("defectFileVO check : "+defectFileVO);

            testDefectService.insertTestDefectFile(defectFileVO);



//            insertTestDefectFile2(TestDefectFileVO testDefectFileVO, Long defectId, Long executionId);



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

