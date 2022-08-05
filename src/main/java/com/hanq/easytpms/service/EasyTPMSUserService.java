package com.hanq.easytpms.service;

import com.hanq.easytpms.repository.EasyTPMSUserRepository;
import com.hanq.easytpms.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

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
    public UserVO getUserInfo(@PathVariable("id") Long id){
        return easyTPMSUserRepository.getUserInfo(id);
    }

    // login
    public  UserVO loginForm(UserVO userVO){
        String userId = userVO.getUserId();
        String userPw = userVO.getUserPassword();
        if (easyTPMSUserRepository.login(userId, userPw) == null){
            return null;
        } else {
        return easyTPMSUserRepository.login(userId, userPw);
        }
    }


    public  UserVO login(UserVO userVO) {
        String userId = userVO.getUserId();
        String userPw = userVO.getUserPassword();

        if (easyTPMSUserRepository.login(userId, userPw) == null) {
            return null;
        } else {
            // id pw 일치하는 값이 있다는 의미이므로 로그인 허용, 해당 data session 저장해야함
            return easyTPMSUserRepository.login(userId, userPw);
        }
    }

    // get 방식사용 시 session id 저장되는지 확인
    public  UserVO login2(String id, String pw) {
        if (easyTPMSUserRepository.login(id, pw) == null) {
            return null;
        } else {
            return easyTPMSUserRepository.login(id, pw);
        }
    }
}