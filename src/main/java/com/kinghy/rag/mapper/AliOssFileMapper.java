package com.kinghy.rag.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kinghy.rag.entity.AliOssFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author kinghy
* @description 针对表【ali_oss_file】的数据库操作Mapper
* @createDate 2025-02-08 20:51:33
* @Entity com.kinghy.rag.entity.AliOssFile
*/

@Mapper
public interface AliOssFileMapper extends BaseMapper<AliOssFile> {

    IPage<AliOssFile> findByFileNameContaining(Page<AliOssFile> page, String fileName);
}




