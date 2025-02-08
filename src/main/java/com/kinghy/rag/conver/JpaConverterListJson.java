package com.kinghy.rag.conver;

import com.alibaba.fastjson2.JSON;
import jakarta.persistence.AttributeConverter;

import java.util.ArrayList;

/**
 * @Description: List对象与JSON字符串互转 用于属性与表字段的映射
 */
public class JpaConverterListJson implements AttributeConverter<Object, String> {
    @Override
    public String convertToDatabaseColumn(Object attribute) {
        if(attribute == null){
            attribute = new ArrayList<>();
        }
        return JSON.toJSONString(attribute);
    }

    @Override
    public Object convertToEntityAttribute(String dbData) {
        return JSON.parseArray(dbData);
    }
}