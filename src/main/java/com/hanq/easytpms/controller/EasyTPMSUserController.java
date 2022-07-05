package com.hanq.easytpms.controller;

import com.hanq.easytpms.service.EasyTPMSUserService;
import com.hanq.easytpms.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@EnableWebMvc
@Slf4j
public class EasyTPMSUserController {

    private final EasyTPMSUserService easyTPMSUserService;

    @Autowired
    public EasyTPMSUserController(EasyTPMSUserService easyTPMSUserService) {
        this.easyTPMSUserService = easyTPMSUserService;
    }

    // user 생성
    @PostMapping("user/create")
    public void insertUser(@RequestBody UserVO userVO) {
        easyTPMSUserService.insertUser(userVO);
    }

    // user 수정
    @PostMapping("user/edit/{id}")
    public void editUser(@RequestBody UserVO userVO) {
        easyTPMSUserService.editUser(userVO);
    }

    // user 삭제
    @DeleteMapping("user/delete/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        easyTPMSUserService.deleteUser(id);
    }

    // user List 조회
    @GetMapping("/user/list")
    public List<UserVO> getUserInfoList(){
        return easyTPMSUserService.getUserInfoList();
    }

    // user 조회
    @GetMapping("/user/detail/{id}")
    public UserVO getUserInfo(@PathVariable("id") Long id){
        return easyTPMSUserService.getUserInfo(id);
    }


    // login session 추가해야함
    @PostMapping("/loginForm")
    public UserVO loginForm(@RequestBody UserVO vo) {
        return easyTPMSUserService.login(vo);
    }

    //logout



}
