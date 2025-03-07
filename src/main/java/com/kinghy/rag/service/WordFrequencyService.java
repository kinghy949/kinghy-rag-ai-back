package com.kinghy.rag.service;

import com.kinghy.rag.common.PageResult;
import com.kinghy.rag.entity.WordFrequency;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kinghy.rag.pojo.dto.WordFrequencyPageQueryDTO;

/**
* @author kinghy
* @description 针对表【word_frequency】的数据库操作Service
* @createDate 2025-03-06 15:56:07
*/
public interface WordFrequencyService extends IService<WordFrequency> {

    PageResult pageQuery(WordFrequencyPageQueryDTO queryDTO);
}
