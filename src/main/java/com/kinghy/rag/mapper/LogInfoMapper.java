package com.kinghy.rag.mapper;

import com.kinghy.rag.entity.LogInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author kinghy
* @description 针对表【log_info】的数据库操作Mapper
* @createDate 2025-03-03 21:48:26
* @Entity com.kinghy.rag.entity.LogInfo
*/

@Mapper
public interface LogInfoMapper extends BaseMapper<LogInfo> {

}




