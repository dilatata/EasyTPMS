package com.hanq.easytpms.controller;

import com.hanq.easytpms.service.CommonCodeService;
import com.hanq.easytpms.vo.CommonCodeDetailVO;
import com.hanq.easytpms.vo.CommonCodeGroupVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
@EnableWebMvc
@Slf4j
public class CommonCodeController {
    private final CommonCodeService commonCodeService;

    @Autowired
    public CommonCodeController(CommonCodeService commonCodeService) {
        this.commonCodeService = commonCodeService;
    }


    // code group 생성
    @PostMapping("/common/group/create")
    public void insertCodeGroup(@RequestBody CommonCodeGroupVO commonCodeGroupVO){
        commonCodeService.insertCodeGroup(commonCodeGroupVO);
    }

    // code group 수정
    @PostMapping("/common/group/edit/{codeGroupId}")
    public void editCodeGroup(@RequestBody CommonCodeGroupVO commonCodeGroupVO) {
        commonCodeService.editCodeGroup(commonCodeGroupVO);
    }

    // code group 삭제
    @DeleteMapping("/common/group/delete/{codeGroupId}")
    public void deleteCodeGroup(@PathVariable("codeGroupId") Long codeGroupId){
        commonCodeService.deleteCodeGroup(codeGroupId);
    }

    // code group list 조회
    @GetMapping("/common/group/List")
    public List<CommonCodeGroupVO> getCodeGroupInfoList() {
        return commonCodeService.getCodeGroupInfoList();
    }

    // code group 조회
    @GetMapping("/common/group/{codeGroupId}")
    public CommonCodeGroupVO getCodeGroupInfo(@PathVariable("codeGroupId") Long codeGroupId){
        return commonCodeService.getCodeGroupInfo(codeGroupId);
    }


    // code detail 생성
    @PostMapping("/common/detail/create")
    public void insertCodeDetail(@RequestBody CommonCodeDetailVO commonCodeDetailVO){
        commonCodeService.insertCodeDetail(commonCodeDetailVO);
    }

    // code detail 수정
    @PostMapping("/common/detail/edit/{codeGroupId}")
    public void editCodeDetail(@RequestBody CommonCodeDetailVO commonCodeDetailVO) {
        commonCodeService.editCodeDetail(commonCodeDetailVO);
    }

    // code detail 삭제
    @DeleteMapping("/common/detail/delete/{codeDetailId}")
    public void deleteCodeDetail(@PathVariable("codeDetailId") Long codeDetailId){
        commonCodeService.deleteCodeDetail(codeDetailId);
    }

    // code detail list 조회
    @GetMapping("/common/detail/{codeGroupId}")
    public List<CommonCodeDetailVO> getCodeDetailInfoList(@PathVariable("codeGroupId") Long codeGroupId){
        return commonCodeService.getCodeDetailList(codeGroupId);
    }


}
