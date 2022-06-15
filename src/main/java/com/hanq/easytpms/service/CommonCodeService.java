package com.hanq.easytpms.service;

import com.hanq.easytpms.repository.CommonCodeDetailRepository;
import com.hanq.easytpms.repository.CommonCodeGroupRepository;
import com.hanq.easytpms.vo.CommonCodeDetailVO;
import com.hanq.easytpms.vo.CommonCodeGroupVO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CommonCodeService {
    private CommonCodeGroupRepository commonCodeGroupRepository;
    private CommonCodeDetailRepository commonCodeDetailRepository;

    public CommonCodeService(CommonCodeGroupRepository commonCodeGroupRepository, CommonCodeDetailRepository commonCodeDetailRepository){
        this.commonCodeDetailRepository = commonCodeDetailRepository;
        this.commonCodeGroupRepository = commonCodeGroupRepository;
    }

    // code group 생성
    public void insertCodeGroup(@RequestBody CommonCodeGroupVO commonCodeGroupVO){
        commonCodeGroupRepository.insertCodeGroup(commonCodeGroupVO);
    }

    // code group 수정
    public void editCodeGroup(@RequestBody CommonCodeGroupVO commonCodeGroupVO) {
        commonCodeGroupRepository.editCodeGroup(commonCodeGroupVO);
    }

    // code group 삭제
    public void deleteCodeGroup(@PathVariable("codeGroupId") Long codeGroupId){
        commonCodeGroupRepository.deleteCodeGroup(codeGroupId);
    }

    // code group list 조회
    public List<CommonCodeGroupVO> getCodeGroupInfoList() {
        return commonCodeGroupRepository.getCodeGroupInfoList();
    }

    // code group 조회
    public CommonCodeGroupVO getCodeGroupInfo(@PathVariable("codeGroupId") Long codeGroupId){
        return commonCodeGroupRepository.getCodeGroupInfo(codeGroupId);
    }


    // code detail 생성
    public void insertCodeDetail(@RequestBody CommonCodeDetailVO commonCodeDetailVO){
        commonCodeDetailRepository.insertCodeDetail(commonCodeDetailVO);
    }

    // code detail 수정
    public void editCodeDetail(@RequestBody CommonCodeDetailVO commonCodeDetailVO) {
        commonCodeDetailRepository.editCodeDetail(commonCodeDetailVO);
    }

    // code detail 삭제
    public void deleteCodeDetail(@PathVariable("codeDetailId") Long codeDetailId){
        commonCodeDetailRepository.deleteCodeDetail(codeDetailId);
    }

    // code detail list 조회
    public List<CommonCodeDetailVO> getCodeDetailList(@PathVariable("codeGroupId") Long codeGroupId){
        return commonCodeDetailRepository.getCodeDetailList(codeGroupId);
    }

}
