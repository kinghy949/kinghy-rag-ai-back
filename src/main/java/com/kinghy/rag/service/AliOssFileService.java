package com.kinghy.rag.service;

import com.kinghy.rag.common.BaseResponse;
import com.kinghy.rag.entity.AliOssFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kinghy.rag.pojo.dto.QueryFileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
* @author kinghy
* @description 针对表【ali_oss_file】的数据库操作Service
* @createDate 2025-02-08 20:51:33
*/
public interface AliOssFileService extends IService<AliOssFile> {

    BaseResponse queryPage(QueryFileDTO request);

    BaseResponse deleteFiles(List<Long> ids);
}
