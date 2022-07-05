package com.hanq.easytpms.repository;

import com.hanq.easytpms.mapper.EasyTPMSUserMapper;
import com.hanq.easytpms.vo.UserVO;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EasyTPMSUserRepositoryImpl implements EasyTPMSUserRepository{

    private Jdbi jdbi;

    public EasyTPMSUserRepositoryImpl(Jdbi jdbi){
        this.jdbi = jdbi;
    }

    @Override
    public void insertUser(UserVO userVO) {
        jdbi.useHandle(dao -> dao.createUpdate("INSERT INTO tpms_user(user_id, user_password, user_name, user_email, role_type) " +
                        "VALUES(:userId, :userPassword, :userName, :userEmail, :roleType) ")
                .bindBean(userVO)
                .execute()
        );
    }

    @Override
    public void editUser(UserVO userVO) {
        jdbi.useHandle(dao->dao.createUpdate("UPDATE tpms_user SET " +
                "user_password = :userPassword, user_email = :userEmail, " +
                "user_name = :userName, role_type = :roleType WHERE id = :id ")
                .bindBean(userVO)
                .defineNamedBindings()
                .execute()
        );
    }

    @Override
    public void deleteUser(Long id) {
        jdbi.useHandle(dao->dao.createUpdate("DELETE FROM tpms_user WHERE id = :id")
                .bind("id",id)
                .execute()
        );
    }

    @Override
    public List<UserVO> getUserInfoList() {
        Handle handle = jdbi.open();
        List<UserVO> userVOList = handle.createQuery("SELECT * FROM tpms_user")
                .map(new EasyTPMSUserMapper())
                .list();
        handle.close();
        return userVOList;
    }

    @Override
    public UserVO getUserInfo(Long id) {
        Handle handle = jdbi.open();
        UserVO userVO = handle.createQuery("SELECT * FROM tpms_user WHERE id = :id")
                .bind("id",id)
                .map(new EasyTPMSUserMapper())
                .one();
        handle.close();
        return userVO;
    }

    @Override
    public UserVO login(String userId, String userPw) {
        Handle handle = jdbi.open();
        UserVO userVO = handle.createQuery("SELECT * FROM tpms_user WHERE user_id = :userId and user_password =:userPw")
                .bind("userId",userId)
                .bind("userPw",userPw)
                .map(new EasyTPMSUserMapper())
                .one();
        handle.close();
        return userVO;
    }
}
