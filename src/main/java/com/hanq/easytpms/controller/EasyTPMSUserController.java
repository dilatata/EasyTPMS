package com.hanq.easytpms.controller;


import com.hanq.easytpms.filter.LoginCheckFilter;
import com.hanq.easytpms.filter.SessionConst;
import com.hanq.easytpms.service.EasyTPMSUserService;
import com.hanq.easytpms.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;


@RestController
@EnableWebMvc
@Slf4j
public class EasyTPMSUserController {

    private final EasyTPMSUserService easyTPMSUserService;

    @Autowired
    public EasyTPMSUserController(EasyTPMSUserService easyTPMSUserService) {
        this.easyTPMSUserService = easyTPMSUserService;
    }

    @Autowired
    private HttpSession session;

    // user 생성
    @PostMapping("user/create")
    public void insertUser(@RequestBody UserVO userVO, HttpServletRequest request, HttpServletResponse response) {
        easyTPMSUserService.insertUser(userVO);
    }

    // user 수정
    @PostMapping("user/edit/{id}")
    public void editUser(@RequestBody UserVO userVO, HttpServletRequest request, HttpServletResponse response) {
        easyTPMSUserService.editUser(userVO);
    }

    // user 삭제
    @DeleteMapping("user/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response) {
        easyTPMSUserService.deleteUser(id);
    }

    // user List 조회
    @GetMapping("/user/list")
    public List<UserVO> getUserInfoList(HttpServletRequest request, HttpServletResponse response) throws Exception{
        HttpSession session = request.getSession(false);

        if (isEmpty(session)){
            log.info("session get attribute empty");
        }else {
            log.info("======= get execution list ======");
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

        return easyTPMSUserService.getUserInfoList();
    }

    // user 조회
    @GetMapping("/user/detail/{id}")
    public UserVO getUserInfo(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response){
        return easyTPMSUserService.getUserInfo(id);
    }


//    // login session 추가해야함
//    @PostMapping("/loginForm")
//    public UserVO loginForm(@RequestBody UserVO vo) {
//
//        return easyTPMSUserService.loginForm(vo);
//    }


    @PostMapping("/login")
    public Object login(@Valid @RequestBody UserVO vo,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        UserVO loginMember = easyTPMSUserService.login(vo);
        if (loginMember != null) {
            // session 가져오기 (없으면 만들기)
            session = request.getSession(true);
            session.setAttribute("userInfo", loginMember);

            // session에 사용자 id 넣기
            log.info("=======login done! ======");
            log.info("sessionId = {}", session.getId());
            log.info("sessionUserInfo = {}", session.getAttribute("userInfo"));
            log.info("getMaxInactiveInterval = {}", session.getMaxInactiveInterval());
            log.info("creationTime = {}", new Date(session.getCreationTime()));
            log.info("lastAccessedTime = {}", new Date(session.getLastAccessedTime()));
            log.info("isNew = {}", session.isNew());
        }

        return loginMember;
    }



//    logout
    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        session = request.getSession(false);
        session.setAttribute("userInfo",null);
        // session logout 처리
        session.setMaxInactiveInterval(0);
//        response.sendRedirect("login.html"); // 포트 번호 다름
//        return "login.html";
    }


}
