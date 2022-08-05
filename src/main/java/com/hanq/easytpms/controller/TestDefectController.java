package com.hanq.easytpms.controller;

import com.hanq.easytpms.filter.SessionConst;
import com.hanq.easytpms.service.TestDefectService;
import com.hanq.easytpms.vo.ResponseTestDefectVO;
import com.hanq.easytpms.vo.TestDefectFileVO;
import com.hanq.easytpms.vo.TestDefectVO;
import com.hanq.easytpms.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import sun.tools.jconsole.JConsole;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.springframework.util.ObjectUtils.isEmpty;


@RestController
@EnableWebMvc
@Slf4j
public class TestDefectController {

    private final TestDefectService testDefectService;

    @Autowired
    public TestDefectController(TestDefectService testDefectService) {
        this.testDefectService = testDefectService;
    }

    @Autowired
    private HttpSession session;

    // 시나리오 결함 생성 -> defect history 생성 시작점?
    @PostMapping(value = "/defect/create")
    public void insertTestDefect(@RequestBody TestDefectVO testDefectVO, HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);

        if (isEmpty(session)){
            log.info("session get attribute empty");
            return;
        }
        else {
            // session에서 사용자 이름, id, accountId 갖고오기
            UserVO loginUserInfo = new UserVO();
            loginUserInfo = (UserVO) session.getAttribute("userInfo");
            String sessionUserName = loginUserInfo.getUserName();
            String sessionUserAccountID = loginUserInfo.getUserId();
            Long sessionUserId = loginUserInfo.getId();


            testDefectService.insertTestDefect(testDefectVO, sessionUserId, sessionUserName, sessionUserAccountID);
        }
    }

    // 시나리오 결함 개별 삭제
    @DeleteMapping("/defect/delete/{defectId}")
    public void deleteTestDefect(@PathVariable("defectId") Long defectId, HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);

        if (isEmpty(session)){
            log.info("session get attribute empty");
            return;
        }
        else {
            testDefectService.deleteTestDefect(defectId);
        }
    }

    // 연관 결함 리스트 조회
    @GetMapping(value = "/defect/list-by-execution-id/{executionId}")
    public Object findTestDefectListByExecutionId(@PathVariable("executionId") Long executionId, HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);

        if (isEmpty(session)){
            log.info("session get attribute empty");

        }
        return testDefectService.findTestDefectListByExecutionId(executionId);

    }


    // 결함 상세 조회
    @GetMapping("/defect/detail/{defectId}")
    public TestDefectVO findTestDefectInfoByDefectId(@PathVariable("defectId") Long defectId, HttpServletRequest request, HttpServletResponse response){
        session = request.getSession(false);

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        return testDefectService.findTestDefectInfoByDefectId(defectId);
    }


    // 결함 리스트 조회 // join projectName 에 해당하는 executionId 를 갖고 있는 data defect 테이블에서 찾아오기
    @GetMapping("/defect/list/{projectName}")
    public Object findAllDefectList(@PathVariable("projectName") String projectName, HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }

        return testDefectService.findDefectListByProjectName(projectName);

    }

    // 결함 리스트 전제 조회
    @GetMapping("/defect/list")
    public List<ResponseTestDefectVO> findAllDefectList(HttpServletRequest request, HttpServletResponse response) {

        session = request.getSession(false);
        if (isEmpty(session)){
            log.info("session get attribute empty");
        }else {
//            log.info("======= get execution list ======");
//            log.info("session get attribute true");
//            log.info("sessionId={}", session.getId());
//            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
//            log.info("sessionUserInfo toString = {}", session.getAttribute("userInfo").toString());
//            log.info("getMaxInactiveInterval={}", session.getMaxInactiveInterval());
//            log.info("creationTime={}", new java.util.Date(session.getCreationTime()));
//            log.info("lastAccessedTime={}", new java.util.Date(session.getLastAccessedTime()));
//            log.info("isNew={}", session.isNew());


            UserVO loginUserInfo = new UserVO();
            loginUserInfo = (UserVO) session.getAttribute("userInfo");
            String sessionUserName = loginUserInfo.getUserName();
            String sessionUserID = loginUserInfo.getUserId();
            log.info(String.valueOf(loginUserInfo));
            log.info("session UserName : " + sessionUserName + "session UserID : " + sessionUserID);

            return testDefectService.findAllDefectList();
        }

        return null;
    }

    // 결함 세부조건 작성(수정) -> history 생성
    @PostMapping("/defect/edit/detail/{defectId}")
    public void editTestDefect(@RequestBody TestDefectVO testDefectVO, HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            testDefectService.editTestDefect(testDefectVO);
        }

    }

    // 결함조치 결과 작성 -> history 생성
    @PostMapping("/defect/update/result/{defectId}")
    public void updateTestDefect(@RequestBody TestDefectVO testDefectVO, HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            testDefectService.updateTestDefect(testDefectVO);
        }
    }

    // 결함조치 확인 작성
    @PostMapping("/defect/check/{defectId}")
    public void updateTestDefectCheck(@RequestBody TestDefectVO testDefectVO, HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            testDefectService.updateTestDefectCheck(testDefectVO);
        }
    }


    // 결함 첨부파일 리스트 조회
    @GetMapping("/defect/attachFile/list/{defectId}")
    public List<TestDefectFileVO> getTestDefectFileList(@PathVariable("defectId") Long defectId, HttpServletRequest request, HttpServletResponse response){
        session = request.getSession(false);

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        return testDefectService.getTestDefectFileList(defectId);
    }


    // 결함 첨부파일 생성 -> 결함 생성에 같이 포함
    @PostMapping(value="/defect/attachFile/create")
    public void createDefectFile(MultipartFile pic,
//                                 @RequestBody
                                 TestDefectVO testDefectVO,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {

        session = request.getSession(false);
        log.info("======= attach file upload ======");

        TestDefectFileVO defectFileVO = new TestDefectFileVO();


        if (isEmpty(session)){
            log.info("session get attribute empty");
        } else {
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

        defectFileVO.setFileName(pic.getOriginalFilename());
        defectFileVO.setFileSize(pic.getSize());
        // 여기에 uploadFileName 이 들어가야 할까?
        defectFileVO.setFileLoc(String.valueOf(uploadPath));
//        defectFileVO.setDefectId();
//        defectFileVO.setExecutionId();
            System.out.println("defectFileVO check : "+defectFileVO);
            System.out.println("testDefectVO with no @RequestBOdy annotation : "+testDefectVO);
//        testDefectService.insertTestDefectFile(defectFileVO);
        }
    }



    // 결함 첨부파일 생성 -> 결함 생성에 같이 포함
    @PostMapping(value="/defect/attachFile/insert/{executionId}/{defectId}")
    public void insertDefectFile(@RequestParam("pic")MultipartFile pic,
                                 @PathVariable("defectId") Long defectId,
                                 @PathVariable("executionId") Long executionId,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {

        session = request.getSession(false);
        log.info("======= attach file upload ======");

        TestDefectFileVO defectFileVO = new TestDefectFileVO();


        if (isEmpty(session)){
            log.info("session get attribute empty");
        } else {
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

            defectFileVO.setFileName(pic.getOriginalFilename());
            defectFileVO.setFileSize(pic.getSize());
            defectFileVO.setFileLoc(String.valueOf(uploadPath));
            defectFileVO.setDefectId(defectId);
            defectFileVO.setExecutionId(executionId);
            System.out.println("defectFileVO check : "+defectFileVO);

        testDefectService.insertTestDefectFile(defectFileVO);
        }
    }

    // 결함 첨부파일 삭제
    @DeleteMapping("/defect/attachFile/delete")
    public void deleteTestDefectFile(@RequestBody TestDefectFileVO testDefectFileVO, HttpServletRequest request, HttpServletResponse response){

        session = request.getSession(false);
        TestDefectFileVO defectFileVO = new TestDefectFileVO();
        defectFileVO = testDefectFileVO;

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            Long fileId = defectFileVO.getFileId();
            testDefectService.deleteTestDefect(fileId);
        }
    }

}
