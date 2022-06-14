package com.hanq.easytpms.service;

import com.hanq.easytpms.repository.EasyTPMSUserRepository;
import com.hanq.easytpms.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class EasyTPMSUserService {
    private final EasyTPMSUserRepository easyTPMSUserRepository;


    @Autowired
    public EasyTPMSUserService(EasyTPMSUserRepository easyTPMSUserRepository) {
        this.easyTPMSUserRepository = easyTPMSUserRepository;
    }

    // user 생성
    public void insertUser(@RequestBody UserVO userVO) {
        easyTPMSUserRepository.insertUser(userVO);
    }

    // user 수정
    public void editUser(@RequestBody UserVO userVO) {
        easyTPMSUserRepository.editUser(userVO);
    }

    // user 삭제
    public void deleteUser(@PathVariable("id") Long id) {
        easyTPMSUserRepository.deleteUser(id);
    }

    // user List 조회
    public List<UserVO> getUserInfoList(){
        return easyTPMSUserRepository.getUserInfoList();
    }

    // user 조회
    @GetMapping("/user/detail/{id}")
    public UserVO getUserInfo(@PathVariable("id") Long id){
        return easyTPMSUserRepository.getUserInfo(id);
    }

    // login

}
