package com.hanq.easytpms.controller;

import com.hanq.easytpms.service.CommonCodeService;
import com.hanq.easytpms.vo.CommonCodeDetailVO;
import com.hanq.easytpms.vo.CommonCodeGroupVO;
import com.hanq.easytpms.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@RestController
@EnableWebMvc
@Slf4j
public class CommonCodeController {
    private final CommonCodeService commonCodeService;

    @Autowired
    public CommonCodeController(CommonCodeService commonCodeService) {
        this.commonCodeService = commonCodeService;
    }

    @Autowired
    private HttpSession session;


    // code group 생성
    @PostMapping("/common/group/create")
    public void insertCodeGroup(@RequestBody CommonCodeGroupVO commonCodeGroupVO, HttpServletRequest request, HttpServletResponse response){

        session = request.getSession(false);
        log.info("======= common code group create ======");

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            log.info("session get attribute true");
            log.info("sessionId={}", session.getId());
            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
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

            commonCodeService.insertCodeGroup(commonCodeGroupVO);
        }

    }

    // code group 수정
    @PostMapping("/common/group/edit/{codeGroupId}")
    public void editCodeGroup(@RequestBody CommonCodeGroupVO commonCodeGroupVO, HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);
        log.info("======= common code group edit ======");

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            log.info("session get attribute true");
            log.info("sessionId={}", session.getId());
            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
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
            commonCodeService.editCodeGroup(commonCodeGroupVO);
        }
    }

    // code group 삭제
    @DeleteMapping("/common/group/delete/{codeGroupId}")
    public void deleteCodeGroup(@PathVariable("codeGroupId") Long codeGroupId, HttpServletRequest request, HttpServletResponse response){
        session = request.getSession(false);
        log.info("======= common code group delete ======");

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            log.info("session get attribute true");
            log.info("sessionId={}", session.getId());
            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
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
            commonCodeService.deleteCodeGroup(codeGroupId);
        }
    }

    // code group list 조회
    @GetMapping("/common/group/list")
    public List<CommonCodeGroupVO> getCodeGroupInfoList(HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);
        log.info("======= common code group list get ======");


        if (isEmpty(session)){
            log.info("session get attribute empty");
        }else {
            log.info("======= get common code group list ======");
            log.info("session get attribute true");
            log.info("sessionId={}", session.getId());
            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
            log.info("sessionUserInfo toString = {}", session.getAttribute("userInfo").toString());
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
        return commonCodeService.getCodeGroupInfoList();
    }

    // code group 조회
    @GetMapping("/common/group/{codeGroupId}")
    public CommonCodeGroupVO getCodeGroupInfo(@PathVariable("codeGroupId") Long codeGroupId, HttpServletRequest request, HttpServletResponse response){
        session = request.getSession(false);
        log.info("======= common code group info get ======");

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            log.info("session get attribute true");
            log.info("sessionId={}", session.getId());
            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
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
        return commonCodeService.getCodeGroupInfo(codeGroupId);
    }


    // code detail 생성
    @PostMapping("/common/detail/create")
    public void insertCodeDetail(@RequestBody CommonCodeDetailVO commonCodeDetailVO, HttpServletRequest request, HttpServletResponse response){
        session = request.getSession(false);
        log.info("======= common code detail create ======");

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            log.info("session get attribute true");
            log.info("sessionId={}", session.getId());
            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
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
            commonCodeService.insertCodeDetail(commonCodeDetailVO);
        }
    }

    // code detail 수정
    @PostMapping("/common/detail/edit/{codeDetailId}")
    public void editCodeDetail(@RequestBody CommonCodeDetailVO commonCodeDetailVO, HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);
        log.info("======= common code detail edit ======");

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            log.info("session get attribute true");
            log.info("sessionId={}", session.getId());
            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
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
            commonCodeService.editCodeDetail(commonCodeDetailVO);
        }
    }

    // code detail 삭제
    @DeleteMapping("/common/detail/delete/{codeDetailId}")
    public void deleteCodeDetail(@PathVariable("codeDetailId") Long codeDetailId, HttpServletRequest request, HttpServletResponse response){
        session = request.getSession(false);
        log.info("======= common code detail delete ======");

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            log.info("session get attribute true");
            log.info("sessionId={}", session.getId());
            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
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
            commonCodeService.deleteCodeDetail(codeDetailId);
        }
    }

    // code detail list 조회
    @GetMapping("/common/detail/{codeGroupId}")
    public List<CommonCodeDetailVO> getCodeDetailInfoList(@PathVariable("codeGroupId") Long codeGroupId, HttpServletRequest request, HttpServletResponse response){
        session = request.getSession(false);
        log.info("======= common code detail list get ======");

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }
        else {
            log.info("session get attribute true");
            log.info("sessionId={}", session.getId());
            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
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
        return commonCodeService.getCodeDetailList(codeGroupId);
    }


}
