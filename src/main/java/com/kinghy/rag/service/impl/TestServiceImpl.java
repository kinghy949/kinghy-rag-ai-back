package com.kinghy.rag.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kinghy.rag.entity.Test;
import com.kinghy.rag.service.TestService;
import com.kinghy.rag.mapper.TestMapper;
import org.springframework.stereotype.Service;

/**
* @author EDY
* @description 针对表【test】的数据库操作Service实现
* @createDate 2025-02-08 16:08:44
*/
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test>
    implements TestService {

}




